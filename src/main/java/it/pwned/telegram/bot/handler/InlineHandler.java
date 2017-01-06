package it.pwned.telegram.bot.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.context.annotation.Bean;

@Bean(name = "inline_handler")
@Retention(RetentionPolicy.RUNTIME)
public @interface InlineHandler {
}
