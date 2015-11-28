package it.pwned.telegram.bot;

import java.util.concurrent.BlockingQueue;

import it.pwned.telegram.bot.api.type.Message;

public abstract class MessageHandler implements Runnable {

	protected final TelegramBot bot;
	private final BlockingQueue<Message> message_queue;

	public MessageHandler(TelegramBot bot, BlockingQueue<Message> message_queue) {
		this.bot = bot;
		this.message_queue = message_queue;
	}

	/**
	 * 
	 * @param m message to dispatch
	 * @return if true the message is forwarded to other generic handlers
	 * @throws InterruptedException
	 */
	public boolean enqueueMessage(Message m) throws InterruptedException {
		if (m != null)
			message_queue.put(m);
		return true;
	}

	protected abstract boolean processMessage(Message m);

	@Override
	public void run() {
		boolean go_on = true;
		while (go_on) { // || !message_queue.isEmpty()) {
			try {
				Message m = message_queue.take();

				go_on = processMessage(m);

				if (Thread.currentThread().isInterrupted())
					throw new InterruptedException();

			} catch (InterruptedException e) {
				go_on = false;
			}
		}
	}

}
