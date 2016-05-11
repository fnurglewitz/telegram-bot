package it.pwned.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.collector.UpdateCollector;
import it.pwned.telegram.bot.dispatcher.UpdateDispatcher;
import it.pwned.telegram.bot.handler.UpdateHandlerManager;

public final class TelegramBot {

	private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

	public final Integer id;
	public final String username;

	private final TelegramBotApi api;
	private final UpdateCollector collector;
	private final UpdateDispatcher dispatcher;
	private final UpdateHandlerManager manager;

	private volatile boolean go_on = true;

	public TelegramBot(TelegramBotApi api, UpdateCollector collector, UpdateDispatcher dispatcher,
			UpdateHandlerManager manager) throws TelegramBotApiException {
		this.api = api;
		this.collector = collector;
		this.dispatcher = dispatcher;
		this.manager = manager;

		// api test
		User me = api.getMe();

		this.id = me.id;
		this.username = me.username;

		log.info(String.format("Bot %s(%d) initialization successful", username, id));
	}

	public void shutdown() {
		if (go_on) {
			log.info("Bot shutting down...");
			go_on = false;
		}
	}

	public void run() throws InterruptedException {

		manager.init();

		while (go_on) {

			Update u = collector.next();

			if (u != null)
				dispatcher.dispatch(u);

		}

		manager.shutdown();

	}

}
