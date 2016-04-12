package it.pwned.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;

public final class TelegramBot {

	private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

	public final Integer id;
	public final String username;

	// public final TelegramBotApi api;
	public final UpdateCollector collector;
	public final UpdateDispatcher dispatcher;

	private volatile boolean go_on = true;

	public TelegramBot(TelegramBotApi api, UpdateCollector collector, UpdateDispatcher dispatcher)
			throws TelegramBotApiException {
		// this.api = api;
		this.collector = collector;
		this.dispatcher = dispatcher;

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

		while (go_on) {

			Update u = collector.next();

			if (u != null)
				dispatcher.dispatch(u);

		}

	}

}
