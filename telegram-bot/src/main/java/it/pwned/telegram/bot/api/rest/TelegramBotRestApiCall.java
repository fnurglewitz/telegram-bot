package it.pwned.telegram.bot.api.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.api.log.LoggingRequestInterceptor;
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

	private static final HttpHeaders multipart_headers = initializeHttpHeaders();
	@SuppressWarnings("rawtypes")
	private static final Map<Class, ParameterizedTypeReference> type_refs = initializeParameterizedTypeReferences();
	private static final RestTemplate rest = initializeRestTemplate();

	private static HttpHeaders initializeHttpHeaders() {
		HttpHeaders tmp = new HttpHeaders();
		tmp.setContentType(MediaType.MULTIPART_FORM_DATA);
		return tmp;
	}

	private static RestTemplate initializeRestTemplate() {
		RestTemplate rest = new RestTemplate();

		SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) rest.getRequestFactory();

		rf.setConnectTimeout(10000);
		rf.setReadTimeout(10000);

		List<HttpMessageConverter<?>> lc = new LinkedList<HttpMessageConverter<?>>();
		lc.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		lc.add(new ByteArrayHttpMessageConverter());
		lc.add(new ResourceHttpMessageConverter());
		lc.add(new MappingJackson2HttpMessageConverter());

		for (HttpMessageConverter<?> c : rest.getMessageConverters()) {
			if (c instanceof FormHttpMessageConverter) {
				FormHttpMessageConverter fc = (FormHttpMessageConverter) c;
				fc.setPartConverters(lc);
			}
		}

		ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
		List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
		ris.add(ri);
		rest.setInterceptors(ris);
		rest.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

		return rest;
	}

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
	private TelegramBotRestApiCall(String method, UriTemplate uri_template, ObjectMapper mapper, HttpMethod http_method,
			HttpEntity<?> entity, Class payload_type) {
		this.method = method;
		this.uri_template = uri_template;
		this.http_method = http_method;
		this.mapper = mapper;
		this.entity = entity;
		this.payload_type = payload_type;
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
		private final String method;
		private final UriTemplate uri_template;
		private final MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();
		@SuppressWarnings("rawtypes")
		private final Class payload_type;

		private HttpMethod http_method;
		private HttpEntity<?> entity;

		@SuppressWarnings("rawtypes")
		public Builder(String method, UriTemplate uri_template, ObjectMapper mapper, Class payload_type) {
			this.method = method;
			this.uri_template = uri_template;
			this.mapper = mapper;
			this.payload_type = payload_type;

			this.http_method = HttpMethod.POST;
			this.entity = new HttpEntity<Object>(body, multipart_headers);
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

		@SuppressWarnings("rawtypes")
		public Builder<T> setHttpEntity(HttpEntity entity) {
			this.entity = entity;
			return this;
		}

		public TelegramBotRestApiCall<T> build() {
			return new TelegramBotRestApiCall<T>(method, uri_template, mapper, http_method, entity, payload_type);
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
