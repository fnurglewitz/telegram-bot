package it.pwned.telegram.samplebot.config;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.pwned.telegram.bot.MessageHandler;
import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.samplebot.handler.GreeterHandler;
import it.pwned.telegram.samplebot.handler.ImgurHandler;
import it.pwned.telegram.samplebot.handler.WeatherHandler;

@Configuration
public class HandlerConfig {
	
	@Bean
	public MessageHandler greeter(TelegramBot bot) {
		LinkedBlockingQueue<Message> message_queue = new LinkedBlockingQueue<Message>();
		return new GreeterHandler(bot, message_queue);
	}
	
	@Bean
	public MessageHandler imgur(TelegramBot bot) {
		LinkedBlockingQueue<Message> message_queue = new LinkedBlockingQueue<Message>();
		return new ImgurHandler(bot, message_queue);		
	}
	
	@Bean
	public MessageHandler weather(TelegramBot bot, @Value("${openweather.api-key}") String api_key) {
		LinkedBlockingQueue<Message> message_queue = new LinkedBlockingQueue<Message>();
		return new WeatherHandler(bot, message_queue);		
	}
	
}