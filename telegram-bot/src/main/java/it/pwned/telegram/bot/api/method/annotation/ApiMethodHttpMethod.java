package it.pwned.telegram.bot.api.method.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.http.HttpMethod;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ApiMethodHttpMethod {

	HttpMethod value();

}
