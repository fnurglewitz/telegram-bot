package it.pwned.telegram.samplebot.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.handler.InlineHandler;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.samplebot.handler.UserDataHandler;
import it.pwned.telegram.samplebot.trivia.TriviaHandler;
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

	@InlineHandler
	public UpdateHandler trivia(TelegramBotApi api, OpenTdbApi trivia, JdbcTemplate jdbc) {
		LinkedBlockingQueue<Update> updateQueue = new LinkedBlockingQueue<Update>();
		TriviaHandler handler = new TriviaHandler(api, trivia, updateQueue, jdbc) {
			@Override
			public String getName() {
				return "TriviaHandler";
			}
		};
		return handler;
	}

}
