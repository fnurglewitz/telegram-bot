package it.pwned.telegram.samplebot.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import it.pwned.telegram.bot.api.ApiClient;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.handler.InlineHandler;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.samplebot.handler.UserDataHandler;
import it.pwned.telegram.samplebot.trivia.TriviaHandler;
import it.pwned.telegram.samplebot.trivia.TriviaHelpHandler;
import it.pwned.telegram.samplebot.trivia.api.OpenTdbApi;
import it.pwned.telegram.samplebot.trivia.api.OpenTdbRestApi;

@Configuration
public class HandlerConfig {

	@Bean
	public OpenTdbApi tdbApi(RestTemplate restTemplate) {
		return new OpenTdbRestApi(restTemplate);
	}

	@Bean
	@Order(value = 1)
	public UpdateHandler userDataHandler(JdbcTemplate jdbc) {
		return new UserDataHandler(jdbc, new LinkedBlockingQueue<User>());
	}

	@Bean
	@Order(value = 2)
	public UpdateHandler helpHandler(ApiClient client, ThreadPoolTaskExecutor executor, ResourceLoader loader) {
		return new TriviaHelpHandler(client, executor, loader);
	}

	@InlineHandler
	public UpdateHandler trivia(ApiClient client, OpenTdbApi trivia, JdbcTemplate jdbc) {
		LinkedBlockingQueue<Update> updateQueue = new LinkedBlockingQueue<Update>();
		TriviaHandler handler = new TriviaHandler(client, trivia, updateQueue, jdbc) {
			@Override
			public String getName() {
				return "TriviaHandler";
			}
		};
		return handler;
	}

}
