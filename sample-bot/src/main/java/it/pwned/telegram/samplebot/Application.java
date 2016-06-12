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

import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.rest.TelegramBotRestApi;
import it.pwned.telegram.bot.api.type.Audio;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.collector.ApiUpdateCollector;
import it.pwned.telegram.bot.collector.UpdateCollector;
import it.pwned.telegram.bot.handler.InlineHandlerQualifier;
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
	public TelegramBotApi telegramBotApi(@Value("${bot.token}") String token, ObjectMapper mapper, RestTemplate rest) {
		return new TelegramBotRestApi(token, mapper, rest);
	}

	@Bean
	public UpdateCollector updateCollector(TelegramBotApi api, @Value("${bot.api.updates.timeout:60}") Integer timeout) {
		LinkedBlockingQueue<Update> update_queue = new LinkedBlockingQueue<Update>();
		ApiUpdateCollector collector = new ApiUpdateCollector(api, update_queue, timeout);
		return collector;
	}

	@Bean
	public UpdateHandlerManager uhManager(List<UpdateHandler> handlers,
			@InlineHandlerQualifier UpdateHandler inline_handler) {
		return new StandardUpdateHandlerManager(handlers, inline_handler);
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
		TelegramBotApi api = ctx.getBean(TelegramBotApi.class);
		ObjectMapper m = ctx.getBean(ObjectMapper.class);

		Signal.handle(new Signal("INT"), new SignalHandler() {
			@Override
			public void handle(Signal s) {
				bot.shutdown();
			}
		});
		
		Audio a = new Audio("xxkx", 10, "pupo", "gelato al cioccolato", "audio/mp3", 100);
		
		String sa = m.writeValueAsString(a);
		
		Audio a2 = m.readValue("{\"duration\":10,\"performer\":\"pupo\",\"title\":\"gelato al cioccolato\",\"file_size\":100,\"fileID\":\"xxkx\",\"mimeType\":\"audio/mp3\"}", Audio.class);
		Audio a3 = m.readValue("{\"duration\":10,\"performer\":\"pupo\",\"title\":\"gelato al cioccolato\",\"file_size\":100,\"file_id\":\"xxkx\",\"mime_type\":\"audio/mp3\"}", Audio.class);
		
		//bot.run();

		ctx.close();

		log.info("Bye");
	}

}
