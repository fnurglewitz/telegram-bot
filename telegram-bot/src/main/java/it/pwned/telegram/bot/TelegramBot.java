package it.pwned.telegram.bot;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.InlineQueryResult;
import it.pwned.telegram.bot.api.type.InlineQueryResultPhoto;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.command.BotCommand;

public final class TelegramBot {

	private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

	public final Integer id;
	public final String username;

	public final TelegramBotApi api;

	private volatile boolean go_on = true;

	@Autowired
	private ThreadPoolTaskExecutor executor;

	private List<MessageHandler> handlers;

	private final ConcurrentMap<Integer, MessageHandler> reply_handlers;
	private final ConcurrentMap<Integer, MessageHandler> chat_handlers;
	private final ConcurrentMap<String, MessageHandler> command_handlers;

	private final Map<MessageHandler, Thread> thread_ref;
	
	private String avaible_commands;

	public TelegramBot(TelegramBotApi api) throws TelegramBotApiException {
		this.api = api;

		// api test
		User me = api.getMe();

		this.id = me.id;
		this.username = me.username;

		command_handlers = new ConcurrentHashMap<String, MessageHandler>();
		reply_handlers = new ConcurrentHashMap<Integer, MessageHandler>();
		chat_handlers = new ConcurrentHashMap<Integer, MessageHandler>();
		// handlers = new LinkedList<MessageHandler>();

		thread_ref = new HashMap<MessageHandler, Thread>();

		log.info(String.format("Bot %s(%d) initialization successful", username, id));
	}

	@Autowired
	public void setupHandlers(List<MessageHandler> handlers) {
		StringBuilder sb = new StringBuilder();
		sb.append("Avaible commands:\n");

		Collections.sort(handlers, AnnotationAwareOrderComparator.INSTANCE);
		
		LinkedList<MessageHandler> generic_handlers = new LinkedList<MessageHandler>(); 

		for (MessageHandler h : handlers) {
			if (h == null)
				continue;

			thread_ref.put(h, new Thread(h, h.getClass().getSimpleName()));

			BotCommand[] commands = h.getClass().getAnnotationsByType(BotCommand.class);

			if (commands.length == 0) {
				generic_handlers.add(h);
				continue;
			}

			for (BotCommand c : commands) {
				if (c.command() != null) {
					this.registerCommand(c.command(), c.description(), h);
					sb.append(String.format("%s - %s\n", c.command().substring(1), c.description()));
				}
			}
		}

		this.handlers = generic_handlers;
		
		avaible_commands = sb.toString();
	}
	
	public String getAvaibleCommands() {
		return this.avaible_commands;
	}

	public void registerCommand(String command, String description, MessageHandler handler) {
		command_handlers.put(command, handler);
	}

	public void unregisterCommand(String command) {
		command_handlers.remove(command);
	}

	public void reserveReply(Integer message_id, MessageHandler handler) {
		reply_handlers.put(message_id, handler);
	}

	public void releaseReservedReply(Integer message_id) {
		reply_handlers.remove(message_id);
	}

	public void reserveChat(Integer chat_id, MessageHandler handler) {
		chat_handlers.put(chat_id, handler);
	}

	public void releaseReservedChat(Integer chat_id) {
		chat_handlers.remove(chat_id);
	}

	public Future<?> submitToExecutor(Runnable task) {
		return executor.submit(task);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Future<?> submitToExecutor(Callable task) {
		return executor.submit(task);
	}

	public void shutdown() {
		if (go_on) {
			log.info("Bot shutting down...");
			go_on = false;
		}
	}

	public void run() throws InterruptedException {

		thread_ref.forEach((k, v) -> v.start());

		int last_update = -1;

		do {
			try {

				// log.info("Fetching updates, offset = " + last_update);

				Update[] updates = null;

				try {
					updates = api.getUpdates(last_update + 1, null, 60);
				} catch (TelegramBotApiException ae) {
				}

				if (updates != null && updates.length > 0) {

					Arrays.sort(updates);

					for (Update u : updates) {

						if (u.update_id > last_update)
							last_update = u.update_id;
						
						// TODO: Create injectable handler, this shit is just for test
						if(u.inline_query != null) {
							
							List<InlineQueryResult> lst = new LinkedList<InlineQueryResult>();
							
							lst.add(new InlineQueryResultPhoto(
									"photo1",
									"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
									null,null,
									"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
									null,null,null,null,null,null
									));

							lst.add(new InlineQueryResultPhoto(
									"photo2",
									"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
									null,null,
									"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
									null,null,null,null,null,null
									));
							
							lst.add(new InlineQueryResultPhoto(
									"photo3",
									"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
									null,null,
									"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
									null,null,null,null,null,null
									));
							
							try {
								api.answerInlineQuery(u.inline_query.id, lst, null, null, null);
							} catch (TelegramBotApiException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						if (u.message != null) {

							Message m = u.message;
							MessageHandler handler = null;

							for (MessageHandler h : handlers)
							{
								if(!h.enqueueMessage(m)){
									break;
								};
							}

							if (m.is_reply) {

								handler = reply_handlers.get(m.reply_to_message.message_id);

								if (handler != null) {
									handler.enqueueMessage(m);
									continue;
								}

								handler = null;
							}

							handler = chat_handlers.get(m.chat.id);
							if (handler != null) {
								handler.enqueueMessage(m);
								continue;
							}
							handler = null;

							if (m.is_command) {

								if (m.command_recipient != null && !m.command_recipient.equals(this.username))
									continue;

								handler = command_handlers.get(m.command);
								if (handler != null) {
									handler.enqueueMessage(m);
									continue;
								}
							}
						}

					}
				}

			} catch (InterruptedException ie) {
				log.error("Main thread interrupted, terminating", ie);
				go_on = false;
			}
		} while (go_on);

		log.info("Sending interrupt to handler threads...");
		thread_ref.forEach((k, v) -> v.interrupt());
		log.info("Waiting for threads to stop...");
		thread_ref.forEach((k, v) -> joinThreadAndIgnoreInterrupt(v));
		log.info("Done");
	}

	private static void joinThreadAndIgnoreInterrupt(Thread t) {
		// @formatter:off
		try { t.join(); } catch (InterruptedException e) { }
		// @formatter:on
	}

}
