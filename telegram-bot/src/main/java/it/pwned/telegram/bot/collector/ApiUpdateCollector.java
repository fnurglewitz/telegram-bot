package it.pwned.telegram.bot.collector;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;

public class ApiUpdateCollector implements UpdateCollector {

	private static final Logger log = LoggerFactory.getLogger(ApiUpdateCollector.class);

	private final TelegramBotApi api;
	private final BlockingQueue<Update> update_queue;
	private final Integer timeout;

	private int last_update = -1;

	public ApiUpdateCollector(TelegramBotApi api, BlockingQueue<Update> update_queue, Integer timeout) {
		this.api = api;
		this.update_queue = update_queue;
		this.timeout = timeout;
	}

	@Override
	public Update next(boolean go_on) throws InterruptedException {
		if (update_queue.isEmpty() && go_on)
			fetch();

		return update_queue.poll();
	}

	private void fetch() {

		Update[] updates = null;

		try {
			updates = api.getUpdates(last_update + 1, null, timeout);
		} catch (TelegramBotApiException ae) {
			log.error("Could not fetch updates", ae);
			updates = null;
		}

		if (updates != null && updates.length > 0) {
			log.trace(String.format("Fetched %d updates", updates.length));
			Arrays.sort(updates);

			for (Update u : updates) {
				if (u.updateId > last_update)
					last_update = u.updateId;

				try {
					this.update_queue.put(u);
				} catch (InterruptedException e) {
					// shit happens
				}

			}
		}
	}
}
