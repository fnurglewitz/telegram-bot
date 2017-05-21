package it.pwned.telegram.bot.api.method.webhook;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import org.springframework.http.HttpMethod;

@ApiMethod("deleteWebhook")
@ApiMethodHttpMethod(HttpMethod.GET)
public class DeleteWebhook extends AbstractApiMethod<Boolean> {
}
