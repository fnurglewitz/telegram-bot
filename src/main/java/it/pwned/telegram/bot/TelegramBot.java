package it.pwned.telegram.bot;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.pwned.telegram.bot.api.ApiClient;
import it.pwned.telegram.bot.api.method.GetMe;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.collector.UpdateCollector;
import it.pwned.telegram.bot.handler.UpdateHandlerManager;

public final class TelegramBot {

	private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

	public Integer id;
	public String username;

	private final ApiClient client;
	private final UpdateCollector collector;
	private final UpdateHandlerManager manager;

	private volatile boolean goOn = true;

	public TelegramBot(ApiClient client, UpdateCollector collector, UpdateHandlerManager manager) {
		this.client = client;
		this.collector = collector;
		this.manager = manager;
	}

	public void shutdown() {
		if (goOn) {
			log.info("Bot shutting down...");
			goOn = false;
		}
	}

	public void run() throws InterruptedException, TelegramBotApiException {

		User me = client.call(new GetMe());

		this.id = me.id;
		this.username = me.username;

		log.info(String.format("Bot %s(%d) initialization successful", username, id));

		log.info("Initializing down update handlers");
		manager.initHandlers();

		Optional<Update> u = null;

		while (goOn || u.isPresent()) {

			u = collector.next(goOn);

			if (u.isPresent())
				manager.dispatch(u.get());

		}
		
		log.info("Shutting down update handlers");
		manager.shutdownHandlers();

	}

}
