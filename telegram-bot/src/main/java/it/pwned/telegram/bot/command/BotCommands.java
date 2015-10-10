package it.pwned.telegram.bot.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BotCommands {
	BotCommand[] value();
}
