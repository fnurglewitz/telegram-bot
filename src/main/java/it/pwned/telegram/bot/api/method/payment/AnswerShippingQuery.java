package it.pwned.telegram.bot.api.method.payment;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.payment.ShippingOption;
import org.springframework.http.HttpMethod;

import java.util.List;

@ApiMethod("answerShippingQuery")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class AnswerShippingQuery extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("shipping_query_id")
    public final String shippingQueryId;

    @ApiMethodParameter("ok")
    public final boolean ok;

    @ApiMethodParameter("shipping_options")
    public final List<ShippingOption> shippingOptions;

    @ApiMethodParameter("error_message")
    public final String errorMessage;

    public AnswerShippingQuery(String shippingQueryId, boolean ok, List<ShippingOption> shippingOptions, String errorMessage) {
        this.shippingQueryId = validateShippingQueryId(shippingQueryId);
        this.ok = ok;
        this.shippingOptions = validateShippingOptions(shippingOptions, ok);
        this.errorMessage = validateErrorMessage(errorMessage, ok);
    }

    private static String validateShippingQueryId(String shippingQueryId) {
        if (shippingQueryId == null || "".equals(shippingQueryId))
            throw new IllegalArgumentException("shippingQueryId cannot be null or empty");

        return shippingQueryId;
    }

    private static List<ShippingOption> validateShippingOptions(List<ShippingOption> shippingOptions, boolean ok) {
        if (ok && (shippingOptions == null || shippingOptions.size() == 0))
            throw new IllegalArgumentException("shippingOptions cannot be null or empty when ok==true");

        return shippingOptions;
    }

    private static String validateErrorMessage(String errorMessage, boolean ok) {
        if (!ok && (errorMessage == null || "".equals(errorMessage)))
            throw new IllegalArgumentException("errorMessage cannot be null or empty when ok==false");

        return errorMessage;
    }

}
