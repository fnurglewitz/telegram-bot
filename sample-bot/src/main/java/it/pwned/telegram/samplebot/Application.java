package it.pwned.telegram.samplebot;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.TelegramBotRestApi;
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
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean
	public TelegramBot telegramBot(TelegramBotApi api) throws Exception {
		return new TelegramBot(api);
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
