package it.pwned.telegram.bot.api.method.webhook;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.type.WebhookInfo;
import org.springframework.http.HttpMethod;

@ApiMethod("getWebhookInfo")
@ApiMethodHttpMethod(HttpMethod.GET)
public class GetWebhookInfo extends AbstractApiMethod<WebhookInfo> {
}
