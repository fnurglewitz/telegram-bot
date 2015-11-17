package it.pwned.telegram.bot;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
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

	private JdbcTemplate jdbc;

	@Autowired
	private ObjectMapper mapper;

	private Set<MessageHandler> handlers;

	private final ConcurrentMap<Integer, MessageHandler> reply_handlers;
	private final ConcurrentMap<Integer, MessageHandler> chat_handlers;
	private final ConcurrentMap<String, MessageHandler> command_handlers;

	private final Map<MessageHandler, Thread> thread_ref;

	public TelegramBot(TelegramBotApi api) throws Exception {
		this.api = api;

		// api test
		Response<User> me = api.getMe();

		if (!me.ok)
			throw new Exception("Bot's getMe endpoint check failed");

		this.id = me.result.id;
		this.username = me.result.username;

		command_handlers = new ConcurrentHashMap<String, MessageHandler>();
		reply_handlers = new ConcurrentHashMap<Integer, MessageHandler>();
		chat_handlers = new ConcurrentHashMap<Integer, MessageHandler>();
		handlers = new HashSet<MessageHandler>();

		thread_ref = new HashMap<MessageHandler, Thread>();

		log.info(String.format("Bot %s(%d) initialization successful", username, id));
	}

	@Autowired
	public void setupHandlers(Set<MessageHandler> handlers) {
		StringBuilder sb = new StringBuilder();
		sb.append("Avaible commands:\n");

		for (MessageHandler h : handlers) {
			if (h == null)
				continue;

			thread_ref.put(h, new Thread(h));

			BotCommand[] commands = h.getClass().getAnnotationsByType(BotCommand.class);

			if (commands.length == 0) {
				this.handlers.add(h);
				continue;
			}

			for (BotCommand c : commands) {
				if (c.command() != null) {
					this.registerCommand(c.command(), c.description(), h);
					sb.append(String.format("%s - %s\n", c.command().substring(1), c.description()));
				}
			}
		}

		try {
			FileUtils.writeStringToFile(new File("commands_help.txt"), sb.toString());
		} catch (IOException e) {
			// well, shit happens
			log.warn("Could not save commands help file.", e);
		}
	}

	@Autowired(required = false)
	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
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
		if (go_on)
			log.info("Bot shutting down...");
		go_on = false;
	}

	public void run() throws InterruptedException {

		thread_ref.forEach((k, v) -> v.start());

		int last_update = -1;

		do {
			try {

				// log.info("Fetching updates, offset = " + last_update);

				Response<Update[]> updates = api.getUpdates(last_update + 1, null, 60);

				if (updates != null && updates.ok && updates.result.length > 0) {

					Arrays.sort(updates.result);

					for (Update u : updates.result) {

						if (u.update_id > last_update)
							last_update = u.update_id;

						if (u.message != null) {

							Message m = u.message;
							MessageHandler handler = null;

							if (jdbc != null) {

								this.submitToExecutor(() -> {
									try {
										final String strMsg = mapper.writeValueAsString(m);
										// @formatter:off
										jdbc.update(
												"insert into message (ts,message) values (current_timestamp,?::json);",
												new PreparedStatementSetter() {
													public void setValues(PreparedStatement preparedStatement) throws SQLException {
															preparedStatement.setString(1, strMsg);
													}
												}
										);
										// @formatter:on
									} catch (JsonProcessingException e) {
										log.error("Error while logging message to DB", e);
									}
								});

							}

							for (MessageHandler h : handlers)
								h.enqueueMessage(m);

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
