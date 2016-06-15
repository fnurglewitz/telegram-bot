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
	private final BlockingQueue<Update> updateQueue;
	private final Integer timeout;

	private int lastUpdate = -1;

	public ApiUpdateCollector(TelegramBotApi api, BlockingQueue<Update> updateQueue, Integer timeout) {
		this.api = api;
		this.updateQueue = updateQueue;
		this.timeout = timeout;
	}

	@Override
	public Update next(boolean goOn) throws InterruptedException {
		if (updateQueue.isEmpty() && goOn)
			fetch();

		return updateQueue.poll();
	}

	private void fetch() {

		Update[] updates = null;

		try {
			updates = api.getUpdates(lastUpdate + 1, null, timeout);
		} catch (TelegramBotApiException ae) {
			log.error("Could not fetch updates", ae);
			updates = null;
		}

		if (updates != null && updates.length > 0) {
			log.trace(String.format("Fetched %d updates", updates.length));
			Arrays.sort(updates);

			for (Update u : updates) {
				if (u.updateId > lastUpdate)
					lastUpdate = u.updateId;

				try {
					this.updateQueue.put(u);
				} catch (InterruptedException e) {
					// shit happens
				}

			}
		}
	}
}
