package it.pwned.telegram.samplebot.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import it.pwned.telegram.bot.api.type.CallbackQuery;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.InlineKeyboardButton;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.ReplyKeyboardHide;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.inline.ChosenInlineResult;
import it.pwned.telegram.bot.api.type.inline.InlineQuery;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResultArticle;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResultPhoto;
import it.pwned.telegram.bot.api.type.inline.InputTextMessageContent;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.samplebot.Application;
import it.pwned.telegram.samplebot.trivia.api.OpenTdbApi;
import it.pwned.telegram.samplebot.trivia.api.TriviaApiException;
import it.pwned.telegram.samplebot.trivia.type.Question;
import it.pwned.telegram.samplebot.trivia.type.QuestionCategory;
import it.pwned.telegram.samplebot.trivia.type.QuestionDifficulty;
import it.pwned.telegram.samplebot.trivia.type.QuestionType;

public class TriviaHandler implements UpdateHandler, Runnable {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private final TelegramBotApi api;
	private final OpenTdbApi trivia;
	private final BlockingQueue<Update> updateQueue;
	private final JdbcTemplate jdbc;

	private volatile boolean goOn = true;

	private static final Map<QuestionCategory, String> categoryImages;
	static {
		Map<QuestionCategory, String> tmp = new HashMap<QuestionCategory, String>();

		tmp.put(QuestionCategory.ANY, "http://i.imgur.com/Gjqohjr.png");
		tmp.put(QuestionCategory.GENERAL, "http://i.imgur.com/QNQ4wN0.png");
		tmp.put(QuestionCategory.BOOKS, "http://i.imgur.com/5bEGv0K.png");
		tmp.put(QuestionCategory.FILM, "http://i.imgur.com/3YxlDAq.png");
		tmp.put(QuestionCategory.MUSIC, "http://i.imgur.com/eidzoiZ.png");
		tmp.put(QuestionCategory.THEATRE, "http://i.imgur.com/3vnUGEG.png");
		tmp.put(QuestionCategory.TV, "http://i.imgur.com/JAZi6uF.png");
		tmp.put(QuestionCategory.VIDEOGAMES, "http://i.imgur.com/5Sd7KdA.png");
		tmp.put(QuestionCategory.BOARDGAMES, "http://i.imgur.com/q680UVC.png");
		tmp.put(QuestionCategory.SCIENCE, "http://i.imgur.com/s8oIYMn.png");
		tmp.put(QuestionCategory.COMPUTERS, "http://i.imgur.com/LjBGh6x.png");
		tmp.put(QuestionCategory.MATH, "http://i.imgur.com/3Pgu2PS.png");
		tmp.put(QuestionCategory.MYTHOLOGY, "http://i.imgur.com/O5kJwBu.png");
		tmp.put(QuestionCategory.SPORTS, "http://i.imgur.com/3Tb8T2R.png");
		tmp.put(QuestionCategory.GEOGRAPHY, "http://i.imgur.com/ujnkoGz.png");
		tmp.put(QuestionCategory.HISTORY, "http://i.imgur.com/yk8Qkrb.png");
		tmp.put(QuestionCategory.POLITICS, "http://i.imgur.com/npDs1Wm.png");
		tmp.put(QuestionCategory.ART, "http://i.imgur.com/5zv2zAm.png");
		tmp.put(QuestionCategory.CELEBRITIES, "http://i.imgur.com/mXlbbl9.png");
		tmp.put(QuestionCategory.ANIMALS, "http://i.imgur.com/BKhSFmS.png");
		tmp.put(QuestionCategory.CARS, "http://i.imgur.com/O2ODxpd.png");
		tmp.put(QuestionCategory.COMICS, "http://i.imgur.com/zACTu0h.png");
		tmp.put(QuestionCategory.GADGETS, "http://i.imgur.com/edJNjTL.png");

		categoryImages = Collections.unmodifiableMap(tmp);
	}

	public TriviaHandler(TelegramBotApi api, OpenTdbApi trivia, BlockingQueue<Update> updateQueue, JdbcTemplate jdbc) {
		this.api = api;
		this.trivia = trivia;
		this.updateQueue = updateQueue;
		this.jdbc = jdbc;
	}

	@Override
	public boolean submit(Update u) {

		if (Update.Util.isInline(u))
			updateQueue.add(u);

		return false;
	}

	@Override
	public boolean requiresThread() {
		return true;
	}

	@Override
	public Runnable getRunnable() {
		return this;
	}

	@Override
	public String getName() {
		return "InlineTriviaHandler";
	}

	@Override
	public void loadState() {

	}

	@Override
	public void saveState() {

	}

	@Override
	public void run() {

		while (goOn) {

			try {
				final Update u = updateQueue.take();

				if (Update.Util.hasInlineQuery(u))
					handleInlineQuery(u.inlineQuery);

				if (Update.Util.hasInlineResult(u))
					handleChosenResult(u.chosenInlineResult);

				if (Update.Util.hasCallbackQuery(u))
					handleCallbackQuery(u.callbackQuery);

			} catch (InterruptedException e) {

			}

		}

	}

	private void handleInlineQuery(InlineQuery q) {

		List<InlineQueryResult> results = new LinkedList<InlineQueryResult>();

		try {

			QuestionDifficulty diff = QuestionDifficulty.fromString(q.query == null ? "" : q.query.toLowerCase());

			Question[] qs = trivia.getQuestions(4, QuestionCategory.ANY, diff, QuestionType.ANY);

			long timestamp = System.currentTimeMillis();

			for (int i = 0; i < qs.length; i++) {
				InlineQueryResult r = convertQuestionToInlineResult(qs[i], String.format("%d_%d", timestamp, i));
				if (r != null) {

					String normalizedAnswer = qs[i].correctAnswer;

					if ("0".equals(normalizedAnswer))
						normalizedAnswer = "false";
					if ("1".equals(normalizedAnswer))
						normalizedAnswer = "true";

					jdbc.update(
							"INSERT INTO PUBLIC.QUESTION_DATA ( RESULT_TS, RESULT_ID, QUESTION_DATA, QUESTION_ANSWER ) VALUES ( ?, ?, ?, ? );",
							new Object[] { Long.toString(timestamp), String.format("%d_%d", timestamp, i),
									qs[i].question, normalizedAnswer });

					results.add(r);
				}
			}

			api.answerInlineQuery(q.id, results, 1, null, null, null, null);

		} catch (TelegramBotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TriviaApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private InlineQueryResult convertQuestionToInlineResult(Question q, String resultId) {

		InlineKeyboardMarkup.Builder kb = new InlineKeyboardMarkup.Builder();

		if (q.type == QuestionType.BOOLEAN) {
			kb.addRow();

			InlineKeyboardButton btn1 = new InlineKeyboardButton("true", null,
					q.correctAnswer.equals("1") ? resultId : "FAIL", null);
			InlineKeyboardButton btn2 = new InlineKeyboardButton("false", null,
					q.correctAnswer.equals("0") ? resultId : "FAIL", null);

			kb.addButton(btn1, 0);
			kb.addButton(btn2, 0);

		} else {

			kb.addRow();
			kb.addRow();

			InlineKeyboardButton btn1 = new InlineKeyboardButton(q.correctAnswer, null, resultId, null);

			kb.addButton(btn1, 0);

			for (int i = 0; i < q.incorrectAnswers.length; i++) {
				InlineKeyboardButton btn = new InlineKeyboardButton(q.incorrectAnswers[i], null, "FAIL", null);
				if (i > 1)
					kb.addButton(btn, 1);
				else
					kb.addButton(btn, 0);

			}

		}

		InlineQueryResultPhoto iqr = new InlineQueryResultPhoto(resultId, categoryImages.get(q.category),
				categoryImages.get(q.category), 104, 104, "", "", null, kb.build(),
				new InputTextMessageContent(q.question, ParseMode.HTML, null));

		return iqr;
	}

	private void handleChosenResult(ChosenInlineResult chosenInlineResult) {

		log.trace(String.format("Chosen question with id: %s", chosenInlineResult.resultId));

		final String ts = chosenInlineResult.resultId.split("_")[0];

		jdbc.update("DELETE FROM PUBLIC.QUESTION_DATA WHERE RESULT_TS = ? AND RESULT_ID <> ? ;",
				new Object[] { ts, chosenInlineResult.resultId });

	}

	private void handleCallbackQuery(CallbackQuery callbackQuery) {

		try {
			final Integer x = jdbc.queryForObject("SELECT COUNT(1) FROM PUBLIC.QUESTION_DATA WHERE RESULT_ID = ? ;",
					Integer.class, new Object[] { callbackQuery.data });

			if (x > 0) {

				if (!"FAIL".equals(callbackQuery.data)) {

					final String newText = jdbc.query(
							"SELECT QUESTION_DATA, QUESTION_ANSWER FROM PUBLIC.QUESTION_DATA WHERE RESULT_ID = ? ;",
							new Object[] { callbackQuery.data }, new ResultSetExtractor<String>() {

								@Override
								public String extractData(ResultSet rs) throws SQLException, DataAccessException {

									final String name = callbackQuery.from.username == null
											|| "".equals(callbackQuery.from.username)
													? String.format("%s %s", callbackQuery.from.firstName,
															callbackQuery.from.lastName)
													: callbackQuery.from.username;

									if (rs.next()) {

										return String.format("%s\n<b>Answer: </b>%s\n<b>Winner: </b>%s",
												rs.getString(1), rs.getString(2), name);
									} else
										return String.format("Ooops, something wrong happened. (And it's %s's fault)",
												name);
								}

							});

					api.editMessageReplyMarkup(null, null, callbackQuery.inlineMessageId, null);

					api.editMessageText(null, null, callbackQuery.inlineMessageId, newText, ParseMode.HTML, null, null);

					jdbc.update("DELETE FROM PUBLIC.QUESTION_DATA WHERE RESULT_ID = ? ;",
							new Object[] { callbackQuery.data });

				}
			}

		} catch (TelegramBotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
