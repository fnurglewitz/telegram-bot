package it.pwned.telegram.bot.collector;

import java.util.List;
import java.util.Optional;
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
	private final Integer timeout;

	private Integer lastOffset;

	public ApiUpdateCollector(ApiClient client, BlockingQueue<Update> updateQueue, Integer timeout) {
		this.client = client;
		this.updateQueue = updateQueue;

		lastOffset = -1;
		this.timeout = timeout;
	}

	@Override
	public Optional<Update> next(boolean goOn) throws InterruptedException {
		if (updateQueue.isEmpty() && goOn)
			fetch();

		return Optional.ofNullable(updateQueue.poll());
	}

	private void fetch() {

		List<Update> updates = null;

		try {
			++lastOffset;
			log.trace(String.format("Fetching updates, starting from offset %d", lastOffset));
			updates = client.call(new GetUpdates(lastOffset, null, timeout, null));
		} catch (TelegramBotApiException ae) {
			log.error("Could not fetch updates", ae);
			updates = null;
		}

		if (updates != null && updates.size() > 0) {
			log.trace(String.format("Fetched %d updates", updates.size()));

			for (Update u : updates) {

				if (u.updateId < lastOffset) {
					log.trace(String.format("Ignoring update [%d] because it's id is lesser than lastOffset", u.updateId));
					continue;
				}

				if (u.updateId > lastOffset)
					lastOffset = u.updateId;

				try {
					this.updateQueue.put(u);
				} catch (InterruptedException e) {
					// shit happens
				}

			}
		}
	}
}
