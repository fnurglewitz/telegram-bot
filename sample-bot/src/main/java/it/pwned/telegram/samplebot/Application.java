package it.pwned.telegram.samplebot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.samplebot.config.BotConfig;
import it.pwned.telegram.samplebot.config.HandlerConfig;

@SpringBootApplication
public class Application {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(15);
		pool.setMaxPoolSize(15);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}

	public static void main(String args[]) throws BeansException, Exception {
		SpringApplication app = new SpringApplication(Application.class, BotConfig.class, HandlerConfig.class);
		app.setWebEnvironment(false);
		ConfigurableApplicationContext ctx = app.run(args);
		ctx.getBean(TelegramBot.class).run();
	}

}
