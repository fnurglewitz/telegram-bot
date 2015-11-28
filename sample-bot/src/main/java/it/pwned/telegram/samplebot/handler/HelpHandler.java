package it.pwned.telegram.samplebot.handler;

import java.util.concurrent.BlockingQueue;

import it.pwned.telegram.bot.MessageHandler;
import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.command.BotCommand;

@BotCommand(command = "/start", description = "Prints avaible commands")
@BotCommand(command = "/help", description = "Prints avaible commands")
public class HelpHandler extends MessageHandler {

	public HelpHandler(TelegramBot bot, BlockingQueue<Message> message_queue) {
		super(bot, message_queue);
	}

	@Override
	protected boolean processMessage(Message m) {

		bot.submitToExecutor(() -> {
			try {
				bot.api.sendMessage(m.chat.id, bot.getAvaibleCommands(), null, null, null, null);
			} catch (Exception e) {
			}
		});

		return true;
	}

}
