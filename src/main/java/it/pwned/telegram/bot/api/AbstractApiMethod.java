package it.pwned.telegram.bot.api;

import it.pwned.telegram.bot.api.method.annotation.*;
import it.pwned.telegram.bot.api.method.enums.ApiMethodBaseUri;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;

public abstract class AbstractApiMethod<R> {

	public AbstractApiMethod() {
	}

	public MultiValueMap<String, Object> getParameterMap() {

		final MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		final Field[] fields = getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(ApiMethodParameter.class)) {

				String paramName = field.getAnnotation(ApiMethodParameter.class).value();
				try {
					Object fieldValue = null;

					if (field.canAccess(this))
						fieldValue = field.get(this);
					else {
						field.setAccessible(true);
						fieldValue = field.get(this);
						field.setAccessible(false);

						if (fieldValue != null)
							map.add(paramName, fieldValue);
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		}

		return map;

	}

	public String getMethod() {
		ApiMethod m = getClass().getAnnotation(ApiMethod.class);
		// TODO: throw specific exception
		if (m == null)
			throw new RuntimeException("Missing method configuration");

		return m.value();
	};

	public HttpMethod getHttpMethod() {
		ApiMethodHttpMethod m = getClass().getAnnotation(ApiMethodHttpMethod.class);
		// TODO: throw specific exception
		if (m == null)
			throw new RuntimeException("Missing http method configuration");

		return m.value();
	}

	public MediaType getContentType() {
		ApiMethodContentType m = getClass().getAnnotation(ApiMethodContentType.class);

		if (m != null)
			return m.value().get();
		else
			return null;
	}

	public ApiMethodBaseUri getBaseUriType() {
		ApiMethodURI m = getClass().getAnnotation(ApiMethodURI.class);

		if (m == null)
			return ApiMethodBaseUri.METHOD;
		else
			return m.value();
	}

	public R map(R input) {
		return input;
	}

}
