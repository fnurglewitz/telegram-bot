package it.pwned.telegram.samplebot.handler;

import java.util.concurrent.BlockingQueue;

import it.pwned.telegram.bot.MessageHandler;
import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.type.Message;

public class GreeterHandler extends MessageHandler {

	public GreeterHandler(TelegramBot bot, BlockingQueue<Message> message_queue) {
		super(bot, message_queue);
	}

	@Override
	protected boolean processMessage(Message m) {

		if (m.new_chat_participant != null) {

			bot.submitToExecutor(() -> {
				try {
					bot.api.sendMessage(m.chat.id, String.format("Welcome, %s!", m.new_chat_participant.first_name), null, null,
							m.message_id, null);
				} catch (Exception e) {
				}
			});
		}

		if (m.left_chat_participant != null) {

			bot.submitToExecutor(() -> {
				try {
					bot.api.sendMessage(m.chat.id, String.format("Goodbye, %s!", m.left_chat_participant.first_name), null, null,
							m.message_id, null);
				} catch (Exception e) {
				}
			});
		}

		return true;
	}

}
