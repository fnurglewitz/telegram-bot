package it.pwned.telegram.bot.api.method.webhook;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import org.springframework.http.HttpMethod;

import javax.annotation.Resource;
import java.util.List;

@ApiMethod("setWebhook")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class SetWebhook extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("url")
    public final String url;

    @ApiMethodParameter("certificate")
    public final Resource certificate;

    @ApiMethodParameter("max_connections")
    public final Integer maxConnections;

    @ApiMethodParameter("allowed_updates")
    public final List<String> allowedUpdates;

    public SetWebhook(String url, Resource certificate, Integer maxConnections, List<String> allowedUpdates) {
        this.url = validateUrl(url);
        this.certificate = certificate;
        this.maxConnections = maxConnections;
        this.allowedUpdates = allowedUpdates;
    }

    private static String validateUrl(String url) {
        if (url == null)
            throw new IllegalArgumentException("url cannot be null");

        return url;
    }
}
