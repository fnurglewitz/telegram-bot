package it.pwned.telegram.samplebot;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.rest.TelegramBotRestApi;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.collector.ApiUpdateCollector;
import it.pwned.telegram.bot.collector.UpdateCollector;
import it.pwned.telegram.bot.handler.StandardUpdateHandlerManager;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.bot.handler.UpdateHandlerManager;
import it.pwned.telegram.samplebot.config.HandlerConfig;
import it.pwned.telegram.samplebot.config.RestConfig;
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
	public TelegramBotApi telegramBotApi(@Value("${bot.token}") String token, RestTemplate rest) {
		return new TelegramBotRestApi(token, rest);
	}

	@Bean
	public UpdateCollector updateCollector(TelegramBotApi api, @Value("${bot.api.updates.timeout:60}") Integer timeout) {
		LinkedBlockingQueue<Update> updateQueue = new LinkedBlockingQueue<Update>();
		ApiUpdateCollector collector = new ApiUpdateCollector(api, updateQueue, timeout);
		return collector;
	}

	@Bean
	public UpdateHandlerManager uhManager(List<UpdateHandler> handlers) {
		return new StandardUpdateHandlerManager(handlers);
	}

	@Bean
	public TelegramBot telegramBot(TelegramBotApi api, UpdateCollector collector, UpdateHandlerManager manager)
			throws Exception {
		return new TelegramBot(api, collector, manager);
	}

	public static void main(String args[]) throws BeansException, Exception {
		SpringApplication app = new SpringApplication(RestConfig.class, Application.class, HandlerConfig.class);
		app.setWebEnvironment(false);
		ConfigurableApplicationContext ctx = app.run(args);

		TelegramBot bot = ctx.getBean(TelegramBot.class);
		// TelegramBotApi api = ctx.getBean(TelegramBotApi.class);

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
