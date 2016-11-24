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

import it.pwned.telegram.bot.api.ApiClient;
import it.pwned.telegram.bot.api.method.AnswerCallbackQuery;
import it.pwned.telegram.bot.api.method.inline.AnswerInlineQuery;
import it.pwned.telegram.bot.api.method.update.EditMessageReplyMarkup;
import it.pwned.telegram.bot.api.method.update.EditMessageText;
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
import it.pwned.telegram.samplebot.trivia.type.OpenTdbApiException;
import it.pwned.telegram.samplebot.trivia.type.Question;
import it.pwned.telegram.samplebot.trivia.type.QuestionCategory;
import it.pwned.telegram.samplebot.trivia.type.QuestionDifficulty;
import it.pwned.telegram.samplebot.trivia.type.QuestionType;

public class TriviaHandler implements UpdateHandler, Runnable {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	private final ApiClient client;
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

	public TriviaHandler(ApiClient client, OpenTdbApi trivia, BlockingQueue<Update> updateQueue, JdbcTemplate jdbc) {
		this.client = client;
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

	private void handleInlineQuery(InlineQuery query) {

		QuestionDifficulty difficulty = QuestionDifficulty.fromString(query.query);

		List<InlineQueryResult> results = new LinkedList<InlineQueryResult>();
		results.add(convertQuestionCategoryToInlineResult(query.from, QuestionCategory.ANY, difficulty));

		Collections.shuffle(categoryIndexes, rand);
		for (int i = 0; i < 3; i++) {
			log.info(String.format("Generating option: %s", categories.get(categoryIndexes.get(i))));
			results
					.add(convertQuestionCategoryToInlineResult(query.from, categories.get(categoryIndexes.get(i)), difficulty));
		}

		try {
			client.call(new AnswerInlineQuery(query.id, results, 1, null, null, null, null));
		} catch (TelegramBotApiException e) {

		}

	}

	private InlineQueryResult convertQuestionCategoryToInlineResult(User requestingUser, QuestionCategory category,
			QuestionDifficulty difficulty) {

		final String resultId = String.format("%s_%s_%s", Integer.toString(requestingUser.id), category.getCode(),
				difficulty.toString());

		InlineKeyboardMarkup.Builder kb = new InlineKeyboardMarkup.Builder();
		kb.addRow();
		kb.addButton(new InlineKeyboardButton("Loading question...", null, "ignore", null, null, null), 0);
		InlineKeyboardMarkup keyboard = kb.build();

		InlineQueryResultPhoto iqr = new InlineQueryResultPhoto(resultId, categoryImages.get(category),
				categoryImages.get(category), 104, 104, "", "", null, keyboard,
				new InputTextMessageContent(String.format("<b>Chosen category:</b> %s\n<b>Chosen difficulty:</b> %s",
						category.toString(), difficulty.toString()), ParseMode.HTML, null));

		return iqr;
	}

	private InlineKeyboardMarkup getInlineKeyboardFromQuestion(Question question) {

		InlineKeyboardMarkup.Builder kb = new InlineKeyboardMarkup.Builder();

		if (question.type == QuestionType.BOOLEAN) {
			kb.addRow();

			InlineKeyboardButton btnTrue = new InlineKeyboardButton("True", null,
					question.correctAnswer.equals("True") ? "WIN" : "FAIL", null, null, null);
			InlineKeyboardButton btnFalse = new InlineKeyboardButton("False", null,
					question.correctAnswer.equals("False") ? "WIN" : "FAIL", null, null, null);

			kb.addButton(btnTrue, 0);
			kb.addButton(btnFalse, 0);

		} else {

			ArrayList<InlineKeyboardButton> allButtons = new ArrayList<InlineKeyboardButton>();

			allButtons
					.add(new InlineKeyboardButton(HtmlUtils.htmlUnescape(question.correctAnswer), null, "WIN", null, null, null));

			for (String incorrectAnswer : question.incorrectAnswers)
				allButtons
						.add(new InlineKeyboardButton(HtmlUtils.htmlUnescape(incorrectAnswer), null, "FAIL", null, null, null));

			Collections.shuffle(allButtons);

			kb.loadKeyboardFromButtonList(allButtons, 2);

		}

		return kb.build();
	}

	private void handleChosenResult(ChosenInlineResult chosenInlineResult) {

		QuestionCategory category = QuestionCategory.ANY;
		QuestionDifficulty difficulty = QuestionDifficulty.ANY;

		final String[] chosenResultValues = chosenInlineResult.resultId.split("_");

		Integer userId = null;
		try {
			userId = Integer.parseInt(chosenResultValues[0]);
		} catch (NumberFormatException ne) {
			userId = null;
		}

		if (chosenResultValues.length >= 2) {
			category = QuestionCategory.fromCode(chosenResultValues[1]);

			if (chosenResultValues.length > 2)
				difficulty = QuestionDifficulty.fromString(chosenResultValues[2]);
		}

		try {

			log.info(String.format("Fetching question with category [%s] and difficulty [%s]", category, difficulty));

			Question question = fetchQuestion(category, difficulty);

			if (question != null) {

				InlineKeyboardMarkup keyboard = getInlineKeyboardFromQuestion(question);

				client.call(new EditMessageText(chosenInlineResult.inlineMessageId, HtmlUtils.htmlUnescape(question.question),
						ParseMode.HTML, null, null));
				client.call(new EditMessageReplyMarkup(chosenInlineResult.inlineMessageId, keyboard));

				final QuestionCategory normalizedCategory = question.category == null ? category : question.category;
				final QuestionDifficulty normalizedDifficulty = question.difficulty == null ? difficulty : question.difficulty;
				final Integer finalUserId = userId;

				jdbc.update(
						"INSERT INTO PUBLIC.QUESTION_DATA ( QUESTION_ID, CATEGORY, DIFFICULTY, TYPE, QUESTION, ANSWER, REQUESTING_USER_ID ) VALUES ( ?, ?, ?, ?, ?, ?, ? );",

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
								ps.setString(6, question.correctAnswer);

								if (finalUserId != null)
									ps.setInt(7, finalUserId);
								else
									ps.setNull(7, Types.INTEGER);

							}

						});
			} else {

				String errorMessage = String.format("Could not find any question with category [%s] and difficulty [%s]",
						category.toString(), difficulty.toString());

				log.warn(errorMessage);

				client.call(new EditMessageReplyMarkup(chosenInlineResult.inlineMessageId, null));
				client.call(new EditMessageText(chosenInlineResult.inlineMessageId, errorMessage, ParseMode.HTML, null, null));
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

			List<Question> results = trivia.getQuestions(1, category, difficulty, QuestionType.ANY, token);

			if (results.size() >= 1)
				result = results.get(0);

		} catch (OpenTdbApiException e) {

			try {
				switch (e.responseCode) {

				case INVALID_PARAMETER:
				case NO_RESULTS:
					result = null;
					break;

				case TOKEN_NOT_FOUND:
					token = trivia.requestToken();
					result = null;
					break;

				case TOKEN_EMPTY:
					trivia.resetToken(token);
					result = null;
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

	private void handleCallbackQuery(CallbackQuery callbackQuery) {

		if ("ignore".equals(callbackQuery.data))
			return;

		try {

			if ("FAIL".equals(callbackQuery.data)) {

				client.call(new AnswerCallbackQuery(callbackQuery.id, "You failed!", false, null, null));

				final Integer count = jdbc.update(
						"UPDATE PUBLIC.QUESTION_FAILER SET FAIL_COUNT = FAIL_COUNT+1 WHERE QUESTION_ID = ? AND USER_ID = ? ;",
						new Object[] { callbackQuery.inlineMessageId, callbackQuery.from.id });

				if (count == 0) {

					jdbc.update("INSERT INTO PUBLIC.QUESTION_FAILER ( QUESTION_ID, USER_ID, FAIL_COUNT ) VALUES ( ?, ?, 1 ) ;",
							new Object[] { callbackQuery.inlineMessageId, callbackQuery.from.id });
				}

			} else {

				final Integer count = jdbc.queryForObject(
						"SELECT COUNT(1) FROM PUBLIC.QUESTION_DATA WHERE QUESTION_ID = ? AND WINNING_USER_ID IS NULL ;",
						Integer.class, new Object[] { callbackQuery.inlineMessageId });

				final String user = User.Util.usernameOrName(callbackQuery.from, UserNameFormat.LINK);

				if (count > 0) {

					final String failerList = jdbc.query(
						// @formatter:off
						"SELECT " +
						"COALESCE('@' || U.USERNAME, U.FIRST_NAME || COALESCE(' ' || U.LAST_NAME,''), 'Anon') " +
						"|| ' (' || CAST(FAIL_COUNT AS VARCHAR(100)) || ')' AS FAILER " +
						"FROM PUBLIC.QUESTION_FAILER QF INNER JOIN PUBLIC.USER U ON QF.USER_ID = U.USER_ID " +
						"WHERE QF.QUESTION_ID = ? " +
						"ORDER BY FAIL_COUNT DESC " +
						"LIMIT 3"
						// @formatter:on
							, new Object[] { callbackQuery.inlineMessageId }, new ResultSetExtractor<String>() {

								@Override
								public String extractData(ResultSet rs) throws SQLException, DataAccessException {

									StringBuilder builder = new StringBuilder("<b>Top Failers:</b>\n");

									int count = 0;
									while (rs.next()) {
										builder.append(rs.getString(1));
										builder.append('\n');
										++count;
									}

									if (count == 0)
										return "";
									else
										return builder.toString();

								}

							});

					final String newText = jdbc.query(
							"SELECT QUESTION, ANSWER, CATEGORY, DIFFICULTY FROM PUBLIC.QUESTION_DATA WHERE QUESTION_ID = ? ;",
							new Object[] { callbackQuery.inlineMessageId }, new ResultSetExtractor<String>() {

								@Override
								public String extractData(ResultSet rs) throws SQLException, DataAccessException {

									if (rs.next()) {

										final String question = rs.getString(1);
										final String answer = rs.getString(2);
										final String category = rs.getString(3);
										final String difficulty = rs.getString(4);

										return String.format("%s\n<b>Answer: </b>%s\n<b>Category:</b> %s (%s)\n<b>Winner: </b>%s\n%s",
												question, answer, category, difficulty, user, failerList);
									} else
										return String.format("Ooops, something wrong happened. (And it's %s's fault)", user);
								}

							});

					client.call(new EditMessageReplyMarkup(callbackQuery.inlineMessageId, null));
					client.call(new EditMessageText(callbackQuery.inlineMessageId, HtmlUtils.htmlUnescape(newText),
							ParseMode.HTML, null, null));

					jdbc.update("UPDATE PUBLIC.QUESTION_DATA SET WINNING_USER_ID = ? WHERE QUESTION_ID = ? ;",
							new Object[] { callbackQuery.from.id, callbackQuery.inlineMessageId });

				}

			}

		} catch (TelegramBotApiException e) {

		} catch (DataAccessException de) {

		}

	}

}
