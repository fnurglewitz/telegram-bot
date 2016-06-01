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

import com.fasterxml.jackson.core.JsonProcessingException;
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
	private static final Map<Class, ParameterizedTypeReference> type_refs = initializeParameterizedTypeReferences();

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
		tmp.put(String.class, new ParameterizedTypeReference<String>() {
		});

		return tmp;
	}

	private final ObjectMapper mapper;
	private final String method;
	private final UriTemplate uri_template;
	private final HttpMethod http_method;
	private final HttpEntity<?> entity;
	@SuppressWarnings("rawtypes")
	private final Class payload_type;

	@SuppressWarnings("rawtypes")
	private TelegramBotRestApiCall(String method, UriTemplate uri_template, ObjectMapper mapper, RestTemplate rest,
			HttpMethod http_method, HttpEntity<?> entity, Class payload_type) {
		this.method = method;
		this.uri_template = uri_template;
		this.http_method = http_method;
		this.mapper = mapper;
		this.entity = entity;
		this.payload_type = payload_type;
		this.rest = rest;
	}

	@SuppressWarnings("unchecked")
	public T call() throws TelegramBotApiException {

		Response<T> res = null;

		try {
			res = (Response<T>) rest.exchange(uri_template.expand(method), http_method, entity, type_refs.get(payload_type))
					.getBody();
		} catch (HttpStatusCodeException he) {
			try {
				res = mapper.readValue(he.getResponseBodyAsString(), Response.class);
			} catch (IOException e) {
				throw new TelegramBotApiException(he);
			}
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	public static class Builder<T> {

		private final ObjectMapper mapper;
		private final RestTemplate rest;
		private final String method;
		private final UriTemplate uri_template;
		private final MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		private final HttpHeaders headers = new HttpHeaders();
		@SuppressWarnings("rawtypes")
		private final Class payload_type;

		private HttpMethod http_method;
		private HttpEntity<?> entity;

		@SuppressWarnings("rawtypes")
		public Builder(String method, UriTemplate uri_template, ObjectMapper mapper, RestTemplate rest,
				Class payload_type) {
			this.method = method;
			this.uri_template = uri_template;
			this.mapper = mapper;
			this.rest = rest;
			this.payload_type = payload_type;
			this.http_method = HttpMethod.POST;
		}

		public Builder<T> setParam(String name, Object param, boolean required, boolean serialize_to_json) {

			if (required && param == null)
				throw new InvalidParameterException(
						String.format("(%s) Null value is not allowed for field: %s", this.method, name));

			if (serialize_to_json) {
				if (param != null)
					body.add(name, serializeToJsonString(param));
			} else {
				if (param != null)
					this.body.add(name, param);
			}

			return this;
		}

		public Builder<T> setHttpMethod(HttpMethod method) {
			this.http_method = method;
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

			return new TelegramBotRestApiCall<T>(method, uri_template, mapper, rest, http_method, entity, payload_type);
		}

		private String serializeToJsonString(Object obj) {
			try {
				return mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				return null;
			}
		}

	}

}
