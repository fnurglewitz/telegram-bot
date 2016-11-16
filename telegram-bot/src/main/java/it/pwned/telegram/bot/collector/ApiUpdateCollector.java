package it.pwned.telegram.bot.collector;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.pwned.telegram.bot.api.ApiClient;
import it.pwned.telegram.bot.api.method.GetUpdates;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;

public class ApiUpdateCollector implements UpdateCollector {

	private static final Logger log = LoggerFactory.getLogger(ApiUpdateCollector.class);

	private final ApiClient client;
	private final BlockingQueue<Update> updateQueue;
	private final GetUpdates request;

	public ApiUpdateCollector(ApiClient client, BlockingQueue<Update> updateQueue, Integer timeout) {
		this.client = client;
		this.updateQueue = updateQueue;

		this.request = new GetUpdates();

		request.setOffset(-1);
		request.setTimeout(timeout);
	}

	@Override
	public Update next(boolean goOn) throws InterruptedException {
		if (updateQueue.isEmpty() && goOn)
			fetch();

		return updateQueue.poll();
	}

	private void fetch() {

		List<Update> updates = null;

		try {
			request.incrementOffset();
			updates = client.call(request);
		} catch (TelegramBotApiException ae) {
			log.error("Could not fetch updates", ae);
			updates = null;
		}

		if (updates != null && updates.size() > 0) {
			log.trace(String.format("Fetched %d updates", updates.size()));

			for (Update u : updates) {
				if (u.updateId > request.getOffset())
					request.setOffset(u.updateId);

				try {
					this.updateQueue.put(u);
				} catch (InterruptedException e) {
					// shit happens
				}

			}
		}
	}
}
