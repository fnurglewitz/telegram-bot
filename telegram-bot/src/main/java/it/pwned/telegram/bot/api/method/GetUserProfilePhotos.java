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
	public final Integer userId;

	@ApiMethodParameter("offset")
	public final Integer offset;

	@ApiMethodParameter("limit")
	public final Integer limit;

	public GetUserProfilePhotos(Integer userId) {
		this(userId, null, null);
	}

	public GetUserProfilePhotos(Integer userId, Integer offset, Integer limit) {
		super();

		this.userId = validateUserId(userId);
		this.offset = offset;
		this.limit = limit;
	}

	private static Integer validateUserId(Integer userId) {
		if (userId == null)
			throw new InvalidParameterException("userId cannot be null");

		return userId;
	}

}
