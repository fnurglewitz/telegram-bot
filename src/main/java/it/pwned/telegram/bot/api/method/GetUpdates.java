package it.pwned.telegram.bot.api.method;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.Update;

@ApiMethod("getUpdates")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class GetUpdates extends AbstractApiMethod<List<Update>> {

    @ApiMethodParameter("offset")
    public final Integer offset;

    @ApiMethodParameter("limit")
    public final Integer limit;

    @ApiMethodParameter("timeout")
    public final Integer timeout;

    @ApiMethodParameter("allowed_updates")
    public final List<String> allowedUpdates;

    public GetUpdates() {
        this(null, null, null, null);
    }

    public GetUpdates(Integer offset, Integer limit, Integer timeout, List<String> allowedUpdates) {
        super();

        this.offset = offset;
        this.limit = limit;
        this.timeout = timeout;
        this.allowedUpdates = allowedUpdates;
    }

    @Override
    public List<Update> map(List<Update> input) {
        Collections.sort(input);
        return Collections.unmodifiableList(input);
    }

}
