package it.pwned.telegram.bot.api.method.payment;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import org.springframework.http.HttpMethod;

@ApiMethod("answerPreCheckoutQuery")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class AnswerPreCheckoutQuery extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("pre_checkout_query_id")
    public final String preCheckoutQueryId;

    @ApiMethodParameter("ok")
    public final boolean ok;

    @ApiMethodParameter("error_message")
    public final String errorMessage;

    public AnswerPreCheckoutQuery(String preCheckoutQueryId, boolean ok, String errorMessage) {
        this.preCheckoutQueryId = validatePreCheckoutQueryId(preCheckoutQueryId);
        this.ok = ok;
        this.errorMessage = validateErrorMessage(errorMessage, ok);
    }

    private static String validatePreCheckoutQueryId(String preCheckoutQueryId) {
        if (preCheckoutQueryId == null || "".equals(preCheckoutQueryId))
            throw new IllegalArgumentException("preCheckoutQueryId cannot be null or empty");

        return preCheckoutQueryId;
    }

    private static String validateErrorMessage(String errorMessage, boolean ok) {
        if (!ok && (errorMessage == null || "".equals(errorMessage)))
            throw new IllegalArgumentException("errorMessage cannot be null or empty when ok==false");

        return errorMessage;
    }

}
