package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.type.User;

@ApiMethod("getMe")
@ApiMethodHttpMethod(HttpMethod.GET)
public final class GetMe extends AbstractApiMethod<User> {
	
}
