package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;

@ApiMethod("getUserProfilePhotos")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class GetUserProfilePhotos extends AbstractApiMethod<UserProfilePhotos> {

	@ApiMethodParameter("user_id")
	private Integer userId;

	@ApiMethodParameter("offset")
	private Integer offset;

	@ApiMethodParameter("limit")
	private Integer limit;

	public GetUserProfilePhotos(Integer userId) {
		super();

		setUserId(userId);
	}

	public GetUserProfilePhotos setUserId(Integer userId) {
		if (userId == null)
			throw new InvalidParameterException("userId cannot be null");

		this.userId = userId;
		return this;
	}

	public GetUserProfilePhotos setOffset(Integer offset) {
		this.offset = offset;
		return this;
	}

	public GetUserProfilePhotos setLimit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public Integer getOffset() {
		return this.offset;
	}

	public Integer getLimit() {
		return this.limit;
	}
}
