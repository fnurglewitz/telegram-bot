package it.pwned.telegram.samplebot.trivia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.util.HtmlUtils;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.CallbackQuery;
import it.pwned.telegram.bot.api.type.InlineKeyboardButton;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.User.Util.UserNameFormat;
import it.pwned.telegram.bot.api.type.inline.ChosenInlineResult;
import it.pwned.telegram.bot.api.type.inline.InlineQuery;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResultPhoto;
import it.pwned.telegram.bot.api.type.inline.InputTextMessageContent;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.samplebot.Application;
import it.pwned.telegram.samplebot.trivia.api.OpenTdbApi;
import it.pwned.telegram.samplebot.trivia.api.OpenTdbApiException;
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

	private volatile String token = null;
	private volatile boolean goOn = true;

	private static final Random rand = new Random();
	private static final List<QuestionCategory> categories;
	private static final List<Integer> categoryIndexes;
	private static final Map<QuestionCategory, String> categoryImages;
	static {
		Map<QuestionCategory, String> catImg = new HashMap<QuestionCategory, String>();

		catImg.put(QuestionCategory.ANY, "http://i.imgur.com/Gjqohjr.png");
		catImg.put(QuestionCategory.GENERAL, "http://i.imgur.com/QNQ4wN0.png");
		catImg.put(QuestionCategory.BOOKS, "http://i.imgur.com/5bEGv0K.png");
		catImg.put(QuestionCategory.FILM, "http://i.imgur.com/3YxlDAq.png");
		catImg.put(QuestionCategory.MUSIC, "http://i.imgur.com/eidzoiZ.png");
		catImg.put(QuestionCategory.THEATRE, "http://i.imgur.com/3vnUGEG.png");
		catImg.put(QuestionCategory.TV, "http://i.imgur.com/JAZi6uF.png");
		catImg.put(QuestionCategory.VIDEOGAMES, "http://i.imgur.com/5Sd7KdA.png");
		catImg.put(QuestionCategory.BOARDGAMES, "http://i.imgur.com/q680UVC.png");
		catImg.put(QuestionCategory.SCIENCE, "http://i.imgur.com/s8oIYMn.png");
		catImg.put(QuestionCategory.COMPUTERS, "http://i.imgur.com/LjBGh6x.png");
		catImg.put(QuestionCategory.MATH, "http://i.imgur.com/3Pgu2PS.png");
		catImg.put(QuestionCategory.MYTHOLOGY, "http://i.imgur.com/O5kJwBu.png");
		catImg.put(QuestionCategory.SPORTS, "http://i.imgur.com/3Tb8T2R.png");
		catImg.put(QuestionCategory.GEOGRAPHY, "http://i.imgur.com/ujnkoGz.png");
		catImg.put(QuestionCategory.HISTORY, "http://i.imgur.com/yk8Qkrb.png");
		catImg.put(QuestionCategory.POLITICS, "http://i.imgur.com/npDs1Wm.png");
		catImg.put(QuestionCategory.ART, "http://i.imgur.com/5zv2zAm.png");
		catImg.put(QuestionCategory.CELEBRITIES, "http://i.imgur.com/mXlbbl9.png");
		catImg.put(QuestionCategory.ANIMALS, "http://i.imgur.com/BKhSFmS.png");
		catImg.put(QuestionCategory.CARS, "http://i.imgur.com/O2ODxpd.png");
		catImg.put(QuestionCategory.COMICS, "http://i.imgur.com/zACTu0h.png");
		catImg.put(QuestionCategory.GADGETS, "http://i.imgur.com/edJNjTL.png");

		categoryImages = Collections.unmodifiableMap(catImg);

		ArrayList<QuestionCategory> cat = new ArrayList<QuestionCategory>(Arrays.asList(QuestionCategory.values()));
		cat.remove(QuestionCategory.ANY);

		categories = Collections.unmodifiableList(cat);
		categoryIndexes = Arrays.asList(IntStream.range(1, categories.size() - 1).boxed().toArray(Integer[]::new));

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

				if (Thread.currentThread().isInterrupted())
					throw new InterruptedException();

			} catch (InterruptedException e) {
				goOn = false;
			}

		}

	}

	private void handleInlineQuery(InlineQuery q) {

		QuestionDifficulty difficulty = QuestionDifficulty.fromString(q.query);

		List<InlineQueryResult> results = new LinkedList<InlineQueryResult>();
		results.add(convertQuestionCategoryToInlineResult(QuestionCategory.ANY, difficulty));

		Collections.shuffle(categoryIndexes, rand);
		for (int i = 0; i < 3; i++) {
			results.add(convertQuestionCategoryToInlineResult(categories.get(categoryIndexes.get(i)), difficulty));
		}

		try {

			api.answerInlineQuery(q.id, results, 1, null, null, null, null);

		} catch (TelegramBotApiException e) {

		}

	}

	private InlineQueryResult convertQuestionCategoryToInlineResult(QuestionCategory category,
			QuestionDifficulty difficulty) {

		final String resultId = String.format("%s_%s", category.getCode(), difficulty.toString());

		InlineKeyboardMarkup.Builder kb = new InlineKeyboardMarkup.Builder();
		kb.addRow();
		kb.addButton(new InlineKeyboardButton("Loading question...", null, "ignore", null), 0);
		InlineKeyboardMarkup keyboard = kb.build();

		InlineQueryResultPhoto iqr = new InlineQueryResultPhoto(resultId, categoryImages.get(category),
				categoryImages.get(category), 104, 104, "", "", null, keyboard,
				new InputTextMessageContent(String.format("<b>Chosen category:</b> %s\n<b>Chosen difficulty:</b> %s",
						category.toString(), difficulty.toString()), ParseMode.HTML, null));

		return iqr;
	}

	private InlineKeyboardMarkup getInlineKeyboardFromQuestion(Question q) {

		InlineKeyboardMarkup.Builder kb = new InlineKeyboardMarkup.Builder();

		if (q.type == QuestionType.BOOLEAN) {
			kb.addRow();

			InlineKeyboardButton btnTrue = new InlineKeyboardButton("true", null,
					q.correctAnswer.equals("1") ? "WIN" : "FAIL", null);
			InlineKeyboardButton btnFalse = new InlineKeyboardButton("false", null,
					q.correctAnswer.equals("0") ? "WIN" : "FAIL", null);

			kb.addButton(btnTrue, 0);
			kb.addButton(btnFalse, 0);

		} else {

			ArrayList<InlineKeyboardButton> allButtons = new ArrayList<InlineKeyboardButton>();

			allButtons.add(new InlineKeyboardButton(HtmlUtils.htmlUnescape(q.correctAnswer), null, "WIN", null));

			for (int i = 0; i < q.incorrectAnswers.length; i++) {
				allButtons.add(new InlineKeyboardButton(HtmlUtils.htmlUnescape(q.incorrectAnswers[i]), null, "FAIL", null));
			}

			Collections.shuffle(allButtons);

			kb.loadButtonsFromList(allButtons, 2);

		}

		return kb.build();
	}

	private void handleChosenResult(ChosenInlineResult chosenInlineResult) {

		QuestionCategory category = QuestionCategory.ANY;
		QuestionDifficulty difficulty = QuestionDifficulty.ANY;

		final String[] chosenResultValues = chosenInlineResult.resultId.split("_");
		if (chosenResultValues.length >= 1) {
			category = QuestionCategory.fromCode(chosenResultValues[0]);

			if (chosenResultValues.length > 1)
				difficulty = QuestionDifficulty.fromString(chosenResultValues[1]);
		}

		try {

			Question question = fetchQuestion(category, difficulty);

			if (question != null) {

				InlineKeyboardMarkup keyboard = getInlineKeyboardFromQuestion(question);

				api.editMessageText(null, null, chosenInlineResult.inlineMessageId, HtmlUtils.htmlUnescape(question.question),
						ParseMode.HTML, null, null);

				api.editMessageReplyMarkup(null, null, chosenInlineResult.inlineMessageId, keyboard);

				final String normalizedAnswer = normalizeAnswer(question.correctAnswer);
				final QuestionCategory normalizedCategory = question.category == null ? category : question.category;
				final QuestionDifficulty normalizedDifficulty = question.difficulty == null ? difficulty : question.difficulty;

				jdbc.update(
						"INSERT INTO PUBLIC.QUESTION_DATA ( QUESTION_ID, CATEGORY, DIFFICULTY, TYPE, QUESTION, ANSWER ) VALUES ( ?, ?, ?, ?, ?, ? );",

						new PreparedStatementSetter() {

							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setString(1, chosenInlineResult.inlineMessageId);

								if (normalizedCategory != null)
									ps.setString(2, normalizedCategory.toString());
								else
									ps.setNull(2, Types.VARCHAR);

								if (normalizedDifficulty != null)
									ps.setString(3, normalizedDifficulty.toString());
								else
									ps.setNull(3, Types.VARCHAR);

								if (question.type != null)
									ps.setString(4, question.type.toString());
								else
									ps.setNull(4, Types.VARCHAR);

								ps.setString(5, question.question);
								ps.setString(6, normalizedAnswer);
							}

						});
			} else {

				String errorMessage = String.format("Could not find any question with category [%s] and difficulty [%s]",
						category.toString(), difficulty.toString());

				log.warn(errorMessage);

				api.editMessageReplyMarkup(null, null, chosenInlineResult.inlineMessageId, null);

				api.editMessageText(null, null, chosenInlineResult.inlineMessageId, errorMessage, ParseMode.HTML, null, null);
			}

		} catch (TelegramBotApiException te) {

		} catch (DataAccessException de) {

		}

	}

	private Question fetchQuestion(QuestionCategory category, QuestionDifficulty difficulty) {

		// TODO : fix this shit

		Question result = null;

		try {
			if (token == null) {
				token = trivia.requestToken();
			}

			Question[] results = trivia.getQuestions(1, category, difficulty, QuestionType.ANY, token);

			if (results.length >= 1)
				result = results[0];

		} catch (OpenTdbApiException e) {

			try {
				switch (e.responseCode) {

				case INVALID_PARAMETER:
				case NO_RESULTS:
					result = null;
					break;

				case TOKEN_NOT_FOUND:
					token = trivia.requestToken();
					break;

				case TOKEN_EMPTY:
					trivia.resetToken(token);
					break;

				default:
					result = null;

				}
			} catch (OpenTdbApiException oe) {
				result = null;
			}

		}

		return result;
	}

	private String normalizeAnswer(String answer) {

		if ("0".equals(answer))
			answer = "false";

		if ("1".equals(answer))
			answer = "true";

		return answer;
	}

	private void handleCallbackQuery(CallbackQuery callbackQuery) {

		if ("ignore".equals(callbackQuery.data))
			return;

		try {

			if ("FAIL".equals(callbackQuery.data)) {
				api.answerCallbackQuery(callbackQuery.id, "You failed!", false);
			} else {

				final Integer count = jdbc.queryForObject(
						"SELECT COUNT(1) FROM PUBLIC.QUESTION_DATA WHERE QUESTION_ID = ? AND WINNING_USER_ID IS NULL ;",
						Integer.class, new Object[] { callbackQuery.inlineMessageId });

				final String user = User.Util.usernameOrName(callbackQuery.from, UserNameFormat.LINK);

				if (count > 0) {

					final String newText = jdbc.query("SELECT QUESTION, ANSWER FROM PUBLIC.QUESTION_DATA WHERE QUESTION_ID = ? ;",
							new Object[] { callbackQuery.inlineMessageId }, new ResultSetExtractor<String>() {

								@Override
								public String extractData(ResultSet rs) throws SQLException, DataAccessException {

									if (rs.next()) {

										return String.format("%s\n<b>Answer: </b>%s\n<b>Winner: </b>%s", rs.getString(1), rs.getString(2),
												user);
									} else
										return String.format("Ooops, something wrong happened. (And it's %s's fault)", user);
								}

							});

					api.editMessageReplyMarkup(null, null, callbackQuery.inlineMessageId, null);

					api.editMessageText(null, null, callbackQuery.inlineMessageId, HtmlUtils.htmlUnescape(newText),
							ParseMode.HTML, null, null);

					jdbc.update("UPDATE PUBLIC.QUESTION_DATA SET WINNING_USER_ID = ? WHERE QUESTION_ID = ? ;",
							new Object[] { callbackQuery.from.id, callbackQuery.inlineMessageId });

				}

			}

		} catch (TelegramBotApiException e) {

		} catch (DataAccessException de) {

		}

	}

}
