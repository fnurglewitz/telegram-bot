package it.pwned.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.collector.UpdateCollector;
import it.pwned.telegram.bot.handler.UpdateHandlerManager;

public final class TelegramBot {

	private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

	public Integer id;
	public String username;

	private final TelegramBotApi api;
	private final UpdateCollector collector;
	private final UpdateHandlerManager manager;

	private volatile boolean go_on = true;

	public TelegramBot(TelegramBotApi api, UpdateCollector collector, UpdateHandlerManager manager) {
		this.api = api;
		this.collector = collector;
		this.manager = manager;
	}

	public void shutdown() {
		if (go_on) {
			log.info("Bot shutting down...");
			go_on = false;
		}
	}

	public void run() throws InterruptedException, TelegramBotApiException {

		User me = api.getMe();

		this.id = me.id;
		this.username = me.username;

		log.info(String.format("Bot %s(%d) initialization successful", username, id));

		manager.init();

		Update u = null;

		while (go_on || u != null) {

			u = collector.next(go_on);

			if (u != null)
				manager.dispatch(u);

		}

		manager.shutdown();

	}

}
