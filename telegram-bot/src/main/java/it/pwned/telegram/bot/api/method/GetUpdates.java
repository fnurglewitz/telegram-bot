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
	private Integer offset;

	@ApiMethodParameter("limit")
	private Integer limit;

	@ApiMethodParameter("timeout")
	private Integer timeout;

	public GetUpdates() {
		super();
	}

	public GetUpdates setOffset(Integer offset) {
		this.offset = offset;
		return this;
	}

	public GetUpdates setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public GetUpdates setTimeout(Integer timeout) {
		this.timeout = timeout;
		return this;
	}

	public Integer getOffset() {
		return this.offset;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public Integer getTimeout() {
		return this.timeout;
	}

	public void incrementOffset() {
		if (offset != null)
			offset += 1;
	}

	public List<Update> map(List<Update> input) {
		Collections.sort(input);
		return Collections.unmodifiableList(input);
	}

}
