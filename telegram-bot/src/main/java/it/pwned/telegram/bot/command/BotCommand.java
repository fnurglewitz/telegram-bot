package it.pwned.telegram.bot.command;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(value = BotCommands.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface BotCommand {

	String command();
	String description();
	
}
