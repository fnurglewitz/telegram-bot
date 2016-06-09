package it.pwned.telegram.samplebot.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.InlineHandler;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.samplebot.handler.AoE2Handler;
import it.pwned.telegram.samplebot.handler.GreeterHandler;
import it.pwned.telegram.samplebot.handler.ImgurHandler;

@Configuration
public class HandlerConfig {

	@Bean
	@Order(value = 2)
	public UpdateHandler greeter(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
		return new GreeterHandler(api, executor);
	}

	@InlineHandler
	public UpdateHandler imgur(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
		LinkedBlockingQueue<Update> update_queue = new LinkedBlockingQueue<Update>();
		ImgurHandler handler = new ImgurHandler(api, update_queue, executor) {
			@Override
			public String getName() {
				return "ImgurHandlerName";
			}
		};
		return handler;
	}

	@Bean
	@Order(value = 1)
	public UpdateHandler aoe2(TelegramBotApi api, ThreadPoolTaskExecutor executor,
			@Value("${aoe2.taunts}") String taunts_path, JdbcTemplate jdbc) {
		LinkedBlockingQueue<Update> update_queue = new LinkedBlockingQueue<Update>();
		return new AoE2Handler(api, update_queue, executor, taunts_path, jdbc);

	}

	@Bean
	@Order(value = 4)
	public UpdateHandler onTheFly(TelegramBotApi api) {
		return new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				Message m = u.message;
				if (m != null && m.text != null) {

					if (m.text.toLowerCase().contains("patriarcato")) {
						try {
							api.sendMessage(m.chat.id, "*GLORIA AL PATRIARCATO DI AQUILEIA*", "Markdown", true, false, m.message_id,
									null);
						} catch (TelegramBotApiException e) {
							// forna sabotaged us
						}
					}
				}
				return true;
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public void loadState() {
			}

			@Override
			public void saveState() {
			}

			@Override
			public String getName() {
				return "PatriarchyHandler";
			}

		};
	}
}
