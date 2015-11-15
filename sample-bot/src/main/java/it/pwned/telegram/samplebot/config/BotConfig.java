package it.pwned.telegram.samplebot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.TelegramBotRestApi;

@Configuration
public class BotConfig {
	
	@Bean
	public TelegramBotApi telegramBotApi(@Value("${bot.token}") String token) {
		return new TelegramBotRestApi(token);
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
		JdbcTemplate jdbc = new JdbcTemplate(ds);
				
		return jdbc;
	}

	@Bean
	public TelegramBot telegramBot(TelegramBotApi api) throws Exception {
		return new TelegramBot(api);
	}
}
