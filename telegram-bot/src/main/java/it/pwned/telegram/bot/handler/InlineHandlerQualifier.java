package it.pwned.telegram.bot.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(value = "inline_handler")
@Retention(RetentionPolicy.RUNTIME)
public @interface InlineHandlerQualifier {

}
