package it.pwned.telegram.samplebot.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Update;
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

	@Bean
	@Order(value = 3)
	public UpdateHandler imgur(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
		LinkedBlockingQueue<Message> message_queue = new LinkedBlockingQueue<Message>();
		ImgurHandler handler = new ImgurHandler(api, message_queue, executor);
		return handler;
	}

	@Bean
	@Order(value = 1)
	public UpdateHandler aoe2(TelegramBotApi api, ThreadPoolTaskExecutor executor, @Value("${aoe2.taunts}") String taunts_path) {
		LinkedBlockingQueue<Message> message_queue = new LinkedBlockingQueue<Message>();
		return new AoE2Handler(api, message_queue, executor, taunts_path);

	}
	
	@Bean(name="inline_handler")
	public UpdateHandler inlineHandler(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
	
		return new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
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
			
		};
		
	}

}
