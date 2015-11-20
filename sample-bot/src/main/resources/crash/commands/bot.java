import org.crsh.command.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.pwned.telegram.bot.TelegramBot;

import org.crsh.cli.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class bot extends BaseCommand {
	
	private static TelegramBot getBot(InvocationContext ctx) {
  	BeanFactory bf = (BeanFactory)(ctx.getAttributes().get("spring.beanfactory"));
  	return bf.getBean(TelegramBot.class);
	}
		 
  @Usage("shutdown the bot")
  @Command
  public void shutdown(InvocationContext ctx) {
  	getBot(ctx).shutdown();
  }
  
  
}