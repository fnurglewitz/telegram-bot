package it.pwned.telegram.samplebot;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.ApiUpdateCollector;
import it.pwned.telegram.bot.StandardUpdateDispatcher;
import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.UpdateCollector;
import it.pwned.telegram.bot.UpdateDispatcher;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.TelegramBotRestApi;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.StandardUpdateHandlerManager;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.bot.handler.UpdateHandlerManager;
import it.pwned.telegram.samplebot.config.HandlerConfig;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@SuppressWarnings("restriction")
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public ThreadPoolTaskExecutor taskExecutor(@Value("${bot.executor.core-pool-size:#{null}}") Integer corePoolSize,
			@Value("${bot.executor.max-pool-size:#{null}}") Integer maxPoolSize) {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		if (corePoolSize != null)
			pool.setCorePoolSize(corePoolSize);
		if (maxPoolSize != null)
			pool.setMaxPoolSize(maxPoolSize);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

	@Bean
	public TelegramBotApi telegramBotApi(@Value("${bot.token}") String token) {
		return new TelegramBotRestApi(token);
	}

	@Bean
	public UpdateCollector updateCollector(TelegramBotApi api) {
		LinkedBlockingQueue<Update> update_queue = new LinkedBlockingQueue<Update>();
		ApiUpdateCollector collector = new ApiUpdateCollector(api, update_queue);
		new Thread(collector).start();
		return collector;
	}
	
	@Bean
	public UpdateHandlerManager uhManager(List<UpdateHandler> handlers, @Qualifier(value="inline_handler") UpdateHandler inline_handler) {
		return new StandardUpdateHandlerManager(handlers,inline_handler);
	}

	@Bean
	public UpdateDispatcher updateDispatcher(UpdateHandlerManager manager) {
		return new StandardUpdateDispatcher(manager);
	}

	@Bean
	public TelegramBot telegramBot(TelegramBotApi api, UpdateCollector collector, UpdateDispatcher dispatcher)
			throws Exception {
		return new TelegramBot(api, collector, dispatcher);
	}

	public static void main(String args[]) throws BeansException, Exception {
		SpringApplication app = new SpringApplication(Application.class, HandlerConfig.class);
		app.setWebEnvironment(false);
		ConfigurableApplicationContext ctx = app.run(args);

		TelegramBot bot = ctx.getBean(TelegramBot.class);

		Signal.handle(new Signal("INT"), new SignalHandler() {
			@Override
			public void handle(Signal s) {
				bot.shutdown();
			}
		});

		bot.run();

		ctx.close();

		log.info("Bye");
	}

}
