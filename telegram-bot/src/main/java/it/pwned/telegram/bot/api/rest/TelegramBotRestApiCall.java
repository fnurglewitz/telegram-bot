package it.pwned.telegram.bot.api.rest;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;

public class TelegramBotRestApiCall<T> {

	private final RestTemplate rest;

	@SuppressWarnings("rawtypes")
	private static final Map<Class, ParameterizedTypeReference> typeRefs = initializeParameterizedTypeReferences();

	@SuppressWarnings("rawtypes")
	private static Map<Class, ParameterizedTypeReference> initializeParameterizedTypeReferences() {

		Map<Class, ParameterizedTypeReference> tmp = new HashMap<Class, ParameterizedTypeReference>();

		tmp.put(User.class, new ParameterizedTypeReference<Response<User>>() {
		});

		tmp.put(Update[].class, new ParameterizedTypeReference<Response<Update[]>>() {
		});

		tmp.put(Message.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(UserProfilePhotos.class, new ParameterizedTypeReference<Response<UserProfilePhotos>>() {
		});

		tmp.put(TelegramFile.class, new ParameterizedTypeReference<Response<TelegramFile>>() {
		});

		tmp.put(Boolean.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});

		tmp.put(Chat.class, new ParameterizedTypeReference<Response<Chat>>() {
		});

		tmp.put(ChatMember.class, new ParameterizedTypeReference<Response<ChatMember>>() {
		});

		tmp.put(ChatMember[].class, new ParameterizedTypeReference<Response<ChatMember[]>>() {
		});

		tmp.put(Integer.class, new ParameterizedTypeReference<Response<Integer>>() {
		});

		// for methods without an output (ie. sendChatAction)
		tmp.put(String.class, new ParameterizedTypeReference<Response<String>>() {
		});

		return tmp;
	}

	private final ObjectMapper mapper;
	private final String method;
	private final UriTemplate uriTemplate;
	private final HttpMethod httpMethod;
	private final HttpEntity<?> entity;
	@SuppressWarnings("rawtypes")
	private final Class payloadType;

	@SuppressWarnings("rawtypes")
	private TelegramBotRestApiCall(String method, UriTemplate uriTemplate, ObjectMapper mapper, RestTemplate rest,
			HttpMethod httpMethod, HttpEntity<?> entity, Class payloadType) {
		this.method = method;
		this.uriTemplate = uriTemplate;
		this.httpMethod = httpMethod;
		this.mapper = mapper;
		this.entity = entity;
		this.payloadType = payloadType;
		this.rest = rest;
	}

	@SuppressWarnings("unchecked")
	public T call() throws TelegramBotApiException {

		Response<T> res = null;

		try {
			res = (Response<T>) rest.exchange(uriTemplate.expand(method), httpMethod, entity, typeRefs.get(payloadType))
					.getBody();
		} catch (HttpStatusCodeException he) {
			Integer statusCode = null;

			try {
				res = mapper.readValue(he.getResponseBodyAsString(), Response.class);
				statusCode = he.getStatusCode() != null ? he.getStatusCode().value() : null;
			} catch (IOException e) {
			}

			throw new TelegramBotApiException(he, statusCode);

		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description, res.errorCode);

		return res.result;
	}

	public static class Builder<T> {

		private final ObjectMapper mapper;
		private final RestTemplate rest;
		private final String method;
		private final UriTemplate uriTemplate;
		private final MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		private final HttpHeaders headers = new HttpHeaders();
		@SuppressWarnings("rawtypes")
		private final Class payloadType;

		private HttpMethod httpMethod;
		private HttpEntity<?> entity;

		@SuppressWarnings("rawtypes")
		public Builder(String method, UriTemplate uriTemplate, ObjectMapper mapper, RestTemplate rest, Class payloadType) {
			this.method = method;
			this.uriTemplate = uriTemplate;
			this.mapper = mapper;
			this.rest = rest;
			this.payloadType = payloadType;
			this.httpMethod = HttpMethod.POST;
		}

		public Builder<T> setParam(String name, Object param, boolean required) {

			if (required && param == null)
				throw new InvalidParameterException(
						String.format("(%s) Null value is not allowed for field: %s", this.method, name));

			if (param != null)
				this.body.add(name, param);

			return this;
		}

		public Builder<T> setHttpMethod(HttpMethod method) {
			this.httpMethod = method;
			return this;
		}

		public Builder<T> setContentType(MediaType type) {
			this.headers.setContentType(type);
			return this;
		}

		public TelegramBotRestApiCall<T> build() {

			if (this.body.size() > 0 || this.headers.size() > 0) {
				this.entity = new HttpEntity<Object>(body, headers);
			}

			return new TelegramBotRestApiCall<T>(method, uriTemplate, mapper, rest, httpMethod, entity, payloadType);
		}

	}

}
