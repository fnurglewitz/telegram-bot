package it.pwned.telegram.samplebot.handler;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.ApiClient;
import it.pwned.telegram.bot.api.method.SendMessage;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.UpdateHandler;

public class GreeterHandler implements UpdateHandler {

	private final ApiClient client;
	private final ThreadPoolTaskExecutor executor;

	public GreeterHandler(ApiClient client, ThreadPoolTaskExecutor executor) {
		this.client = client;
		this.executor = executor;
	}

	public boolean submit(Update u) {

		Message m = u.message;

		if (m == null || (m.newChatMember == null && m.leftChatMember == null))
			return true;

		if (m.newChatMember != null) {

			executor.submit(() -> {
				try {
					SendMessage message = new SendMessage(new ChatId(m.chat.id),
							String.format("Welcome, %s!", m.newChatMember.firstName));

					client.call(message);
				} catch (Exception e) {
				}
			});
		} else {

			executor.submit(() -> {
				try {
					SendMessage message = new SendMessage(new ChatId(m.chat.id),
							String.format("Goodbye, %s!", m.leftChatMember.firstName));

					client.call(message);

				} catch (Exception e) {
				}
			});
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
		return "GreeterHandler";
	}

}
