package it.pwned.telegram.samplebot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.TelegramBotRestApi;
import it.pwned.telegram.bot.authorization.Authorization;
import it.pwned.telegram.bot.authorization.NullAuthorization;

@Configuration
public class BotConfig {
	
	@Bean
	public TelegramBotApi telegramBotApi(@Value("${bot.token}") String token) {
		return new TelegramBotRestApi(token);
	}

	@Bean
	public Authorization botAuthorization() {
		return new NullAuthorization();
	}

	@Bean
	public TelegramBot telegramBot(TelegramBotApi api, Authorization auth) throws Exception {
		return new TelegramBot(api, auth);
	}
}
