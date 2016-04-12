package it.pwned.telegram.bot;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;

public class ApiUpdateCollector implements UpdateCollector, Runnable {

	private final TelegramBotApi api;
	private final BlockingQueue<Update> update_queue;

	public ApiUpdateCollector(TelegramBotApi api, BlockingQueue<Update> update_queue) {
		this.api = api;
		this.update_queue = update_queue;
	}

	@Override
	public Update next() throws InterruptedException {
		return update_queue.take();
	}

	@Override
	public void run() {

		int last_update = -1;
		Update[] updates = null;

		while (true) {

			try {
				updates = api.getUpdates(last_update + 1, null, 60);
			} catch (TelegramBotApiException ae) {
				updates = null;
			}

			if (updates != null && updates.length > 0) {
				Arrays.sort(updates);

				for (Update u : updates) {
					if (u.update_id > last_update)
						last_update = u.update_id;

					try {
						this.update_queue.put(u);
					} catch (InterruptedException e) {
					}

				}
			}

		}

	}

}
