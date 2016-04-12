package it.pwned.telegram.samplebot.handler;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.UpdateHandler;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Update;

public class GreeterHandler implements UpdateHandler {

	private final TelegramBotApi api;
	private final ThreadPoolTaskExecutor executor;

	public GreeterHandler(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
		this.api = api;
		this.executor = executor;
	}

	public boolean submit(Update u) {

		Message m = u.message;

		if (m == null || (m.new_chat_participant == null && m.left_chat_participant == null))
			return true;

		if (m.new_chat_participant != null) {

			executor.submit(() -> {
				try {
					api.sendMessage(m.chat.id, String.format("Welcome, %s!", m.new_chat_participant.first_name), null, null,
							m.message_id, null);
				} catch (Exception e) {
				}
			});
		} else {

			executor.submit(() -> {
				try {
					api.sendMessage(m.chat.id, String.format("Goodbye, %s!", m.left_chat_participant.first_name), null, null,
							m.message_id, null);
				} catch (Exception e) {
				}
			});
		}

		return true;
	}

}
