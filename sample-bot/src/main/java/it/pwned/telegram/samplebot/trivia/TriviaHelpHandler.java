package it.pwned.telegram.samplebot.trivia;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.StatefulUpdateHandler;
import it.pwned.telegram.bot.handler.UpdateHandler;

public class TriviaHelpHandler implements UpdateHandler, StatefulUpdateHandler {

	private final TelegramBotApi api;
	private final ThreadPoolTaskExecutor executor;

	public TriviaHelpHandler(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
		this.api = api;
		this.executor = executor;
	}

	@Override
	public boolean submit(Update u) {

		if (Update.Util.isMessage(u) && Message.Util.isCommand(u.message)) {

			String command = Message.Util.parseCommand(u.message).command;

			if ("/help".equals(command) || "/start".equals(command)) {
				executor.submit(() -> {

					try {
						api.sendMessage(new ChatId(u.message.chat.id), "help text here", ParseMode.MARKDOWN, null, null, u.message.messageId,
								null);
					} catch (TelegramBotApiException e) {
					}

				});
			}
		}

		return true;
	}

	@Override
	public boolean requiresThread() {
		return false;
	}

	@Override
	public Runnable getRunnable() {
		return null;
	}

	@Override
	public String getName() {
		return "TriviaHelpHandler";
	}

	@Override
	public void loadState() {
		// load help text from a resource here

	}

	@Override
	public void saveState() {

	}

}
