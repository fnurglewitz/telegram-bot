package it.pwned.telegram.samplebot.handler;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.InlineKeyboardButton;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.inline.InlineQuery;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResultArticle;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResultPhoto;
import it.pwned.telegram.bot.api.type.inline.InputTextMessageContent;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.samplebot.trivia.api.OpenTdbApi;
import it.pwned.telegram.samplebot.trivia.api.TriviaApiException;
import it.pwned.telegram.samplebot.trivia.type.Question;
import it.pwned.telegram.samplebot.trivia.type.QuestionCategory;
import it.pwned.telegram.samplebot.trivia.type.QuestionDifficulty;
import it.pwned.telegram.samplebot.trivia.type.QuestionType;

public class TriviaHandler implements UpdateHandler, Runnable {

	private final TelegramBotApi api;
	private final OpenTdbApi trivia;
	private final BlockingQueue<Update> updateQueue;
	private final ThreadPoolTaskExecutor executor;

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

	public TriviaHandler(TelegramBotApi api, OpenTdbApi trivia, BlockingQueue<Update> updateQueue,
			ThreadPoolTaskExecutor executor) {
		this.api = api;
		this.trivia = trivia;
		this.updateQueue = updateQueue;
		this.executor = executor;
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

			} catch (InterruptedException e) {

			}

		}

	}

	private void handleInlineQuery(InlineQuery q) {

		List<InlineQueryResult> results = new LinkedList<InlineQueryResult>();

		try {

			QuestionDifficulty diff = QuestionDifficulty.fromString(q.query == null ? "" : q.query.toLowerCase());

			Question[] qs = trivia.getQuestions(4, QuestionCategory.ANY, diff, QuestionType.ANY);

			for (int i = 0; i < qs.length; i++) {
				InlineQueryResult r = convertQuestionToInlineResult(qs[i], i);
				if (r != null)
					results.add(r);
			}

			api.answerInlineQuery(q.id, results, null, null, null, null, null);

		} catch (TelegramBotApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TriviaApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private InlineQueryResult convertQuestionToInlineResult(Question q, int idx) {

		InlineKeyboardMarkup.Builder kb = new InlineKeyboardMarkup.Builder();

		if (q.type == QuestionType.BOOLEAN) {
			kb.addRow();

			InlineKeyboardButton btn1 = new InlineKeyboardButton("true", null,
					q.correctAnswer.equals("1") ? "win" : "lose", null);
			InlineKeyboardButton btn2 = new InlineKeyboardButton("false", null,
					q.correctAnswer.equals("0") ? "win" : "lose", null);

			kb.addButton(btn1, 0);
			kb.addButton(btn2, 0);

		} else {

			kb.addRow();
			kb.addRow();

			InlineKeyboardButton btn1 = new InlineKeyboardButton(q.correctAnswer, null, "win", null);

			kb.addButton(btn1, 0);

			for (int i = 0; i < q.incorrectAnswers.length; i++) {
				InlineKeyboardButton btn = new InlineKeyboardButton(q.incorrectAnswers[i], null, "lose", null);
				if(i>1)
					kb.addButton(btn, 1);
				else
					kb.addButton(btn, 0);
				
			}

		}

		InlineQueryResultPhoto iqr = new InlineQueryResultPhoto(Integer.toString(idx), categoryImages.get(q.category),
				categoryImages.get(q.category), 104, 104, "", "", null, kb.build(),
				new InputTextMessageContent(q.question, ParseMode.HTML, null));

		return iqr;
	}

}
