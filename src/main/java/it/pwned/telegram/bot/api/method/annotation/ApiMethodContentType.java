package it.pwned.telegram.bot.api.method.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import it.pwned.telegram.bot.api.method.enums.MethodMediaType;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ApiMethodContentType {

	MethodMediaType value();

}
