package it.pwned.telegram.samplebot.trivia;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.ApiClient;
import it.pwned.telegram.bot.api.method.SendMessage;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.StatefulUpdateHandler;
import it.pwned.telegram.bot.handler.UpdateHandler;

public class TriviaHelpHandler implements UpdateHandler, StatefulUpdateHandler {

	private final ApiClient client;
	private final ThreadPoolTaskExecutor executor;
	private final String helpText;

	public TriviaHelpHandler(ApiClient client, ThreadPoolTaskExecutor executor, ResourceLoader loader) {
		this.client = client;
		this.executor = executor;

		Resource helpFile = loader.getResource("classpath:help.txt");

		String tmpHelpText;
		try {
			tmpHelpText = IOUtils.toString(helpFile.getInputStream(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			tmpHelpText = "Could not load help text";
		}
		this.helpText = tmpHelpText;
	}

	@Override
	public boolean submit(Update u) {

		if (Update.Util.isMessage(u) && Message.Util.isCommand(u.message)) {

			String command = Message.Util.parseCommand(u.message).command;

			if ("/help".equals(command) || "/start".equals(command)) {
				executor.submit(() -> {

					try {
						SendMessage helpMessage = new SendMessage(new ChatId(u.message.chat.id), helpText);
						helpMessage.setParseMode(ParseMode.MARKDOWN);
						helpMessage.setReplyToMessageId(u.message.messageId);

						client.call(helpMessage);
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
