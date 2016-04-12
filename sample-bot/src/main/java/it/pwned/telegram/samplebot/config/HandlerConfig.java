package it.pwned.telegram.samplebot.config;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.UpdateHandler;
import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.InlineQueryResult;
import it.pwned.telegram.bot.api.type.InlineQueryResultPhoto;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.samplebot.handler.GreeterHandler;
import it.pwned.telegram.samplebot.handler.ImgurHandler;

@Configuration
public class HandlerConfig {

	@Bean(name = "tgUpdateHandlers")
	public ThreadGroup tgHandlers() {
		return new ThreadGroup("tgUpdateHandlers");
	}

	@Bean
	@Order(value = 2)
	public UpdateHandler greeter(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
		return new GreeterHandler(api, executor);
	}

	@Bean
	@Order(value = 3)
	public UpdateHandler imgur(TelegramBotApi api, ThreadPoolTaskExecutor executor,
			@Qualifier("tgUpdateHandlers") ThreadGroup tg) {
		LinkedBlockingQueue<Message> message_queue = new LinkedBlockingQueue<Message>();
		ImgurHandler handler = new ImgurHandler(api, message_queue, executor);
		new Thread(tg, handler).start();
		return handler;
	}

	@Bean
	@Order(value = 1)
	public UpdateHandler aoe2(TelegramBotApi api, ThreadPoolTaskExecutor executor) {

		return new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				if (u.message.text != null && u.message.text.equals("30"))
					executor.submit(() -> {
						try {
							api.sendMessage(u.message.chat.id, "wololo!", null, null, u.message.message_id, null);
						} catch (Exception e) {
						}
					});
				return false;
			}

		};

	}
	
	@Bean
	public UpdateHandler inlineHandler(TelegramBotApi api, ThreadPoolTaskExecutor executor) {
	
		return new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				List<InlineQueryResult> lst = new LinkedList<InlineQueryResult>();
				
				lst.add(new InlineQueryResultPhoto(
						"photo1",
						"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
						null,null,
						"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
						null,null,null,null,null,null
						));

				lst.add(new InlineQueryResultPhoto(
						"photo2",
						"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
						null,null,
						"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
						null,null,null,null,null,null
						));
				
				lst.add(new InlineQueryResultPhoto(
						"photo3",
						"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
						null,null,
						"http://www.olneymiddle.milton-keynes.sch.uk/Year6/wp-content/uploads/2014/02/number-6-md-Copy.png",
						null,null,null,null,null,null
						));
				
				try {
					api.answerInlineQuery(u.inline_query.id, lst, null, null, null);
				} catch (TelegramBotApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			}
			
		};
		
	}

}
