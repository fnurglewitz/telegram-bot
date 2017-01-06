package it.pwned.telegram.bot.api.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.ApiClient;
import it.pwned.telegram.bot.api.ApiMethodReturnTypes;
import it.pwned.telegram.bot.api.method.enums.ApiMethodBaseUri;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.config.DefaultConfig;

public class TelegramBotRestApiClient implements ApiClient {

	private final ObjectMapper mapper;
	private final RestTemplate restTemplate;

	private final UriTemplate apiUriTemplate;
	private final UriTemplate fileUriTemplate;

	public TelegramBotRestApiClient(String token) {
		this(token, null);
	}

	public TelegramBotRestApiClient(String token, RestTemplate restTemplate) {
		if(restTemplate == null)
			this.restTemplate = DefaultConfig.defaultRestTemplate();
		else
			this.restTemplate = restTemplate;
		
		this.mapper = getObjectMapperFromRestTemplate(this.restTemplate);

		this.apiUriTemplate = new UriTemplate("https://api.telegram.org/bot" + token + "/{method}");
		this.fileUriTemplate = new UriTemplate("https://api.telegram.org/file/bot" + token + "/{file_path}");
	}

	private static ObjectMapper getObjectMapperFromRestTemplate(RestTemplate rest) {

		Optional<HttpMessageConverter<?>> maybeConverter = rest.getMessageConverters().stream().filter((c) -> {
			return c.getClass() == MappingJackson2HttpMessageConverter.class;
		}).findFirst();

		if (maybeConverter.isPresent())
			return ((MappingJackson2HttpMessageConverter) maybeConverter.get()).getObjectMapper();
		else
			return new ObjectMapper(); // this is bad

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HttpEntity<?> buildHttpEntity(AbstractApiMethod m) {
		MultiValueMap<String, Object> body = m.getParameterMap();
		HttpHeaders headers = new HttpHeaders();

		MediaType contentType = m.getContentType();
		if (contentType != null)
			headers.setContentType(contentType);

		if (body.size() > 0 || headers.size() > 0) {
			return new HttpEntity<Object>(body, headers);
		} else
			return null;
	}

	private UriTemplate getUriTemplateFromEnum(ApiMethodBaseUri u) {
		switch (u) {
		case METHOD:
			return apiUriTemplate;
		case FILE:
			return fileUriTemplate;
		default:
			return apiUriTemplate;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T call(AbstractApiMethod<T> m) throws TelegramBotApiException {

		Response<T> res = null;

		final ParameterizedTypeReference<Response<T>> typeRef = ApiMethodReturnTypes.typeRefs.get(m.getClass());
		final URI uri = getUriTemplateFromEnum(m.getBaseUriType()).expand(m.getMethod());

		if (typeRef == null)
			throw new UnsupportedOperationException("Missing payload type reference in TelegramBotRestApiCall.typeRefs map.");

		try {
			res = (Response<T>) restTemplate.exchange(uri, m.getHttpMethod(), buildHttpEntity(m), typeRef).getBody();
		} catch (HttpStatusCodeException he) {
			Integer statusCode = null;

			try {

				// check if the api sent a valid Response object
				res = mapper.readValue(he.getResponseBodyAsString(), Response.class);

			} catch (IOException e) {
				// the response is not deserializable as a telegram bot api Response
				// object
				statusCode = he.getStatusCode() != null ? he.getStatusCode().value() : null;
				throw new TelegramBotApiException(he, statusCode);
			}

		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description, res.errorCode);

		return m.map(res.result);
	}

	@Override
	public Resource getResourceFromTelegramFile(TelegramFile file) {
		if (file == null)
			throw new InvalidParameterException("(getResourceFromTelegramFile) Null value is not allowed for field: file");

		try {
			return new UrlResource(fileUriTemplate.expand(file.filePath));
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
