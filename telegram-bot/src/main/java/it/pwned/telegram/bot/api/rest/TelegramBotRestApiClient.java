package it.pwned.telegram.bot.api.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import it.pwned.telegram.bot.api.method.AnswerCallbackQuery;
import it.pwned.telegram.bot.api.method.inline.AnswerInlineQuery;
import it.pwned.telegram.bot.api.method.update.EditMessageCaption;
import it.pwned.telegram.bot.api.method.update.EditMessageReplyMarkup;
import it.pwned.telegram.bot.api.method.update.EditMessageText;
import it.pwned.telegram.bot.api.method.ForwardMessage;
import it.pwned.telegram.bot.api.method.GetChat;
import it.pwned.telegram.bot.api.method.GetChatAdministrators;
import it.pwned.telegram.bot.api.method.GetChatMember;
import it.pwned.telegram.bot.api.method.GetChatMembersCount;
import it.pwned.telegram.bot.api.method.GetFile;
import it.pwned.telegram.bot.api.method.GetMe;
import it.pwned.telegram.bot.api.method.GetUpdates;
import it.pwned.telegram.bot.api.method.GetUserProfilePhotos;
import it.pwned.telegram.bot.api.method.KickChatMember;
import it.pwned.telegram.bot.api.method.LeaveChat;
import it.pwned.telegram.bot.api.method.SendAudio;
import it.pwned.telegram.bot.api.method.SendChatAction;
import it.pwned.telegram.bot.api.method.SendContact;
import it.pwned.telegram.bot.api.method.SendDocument;
import it.pwned.telegram.bot.api.method.SendLocation;
import it.pwned.telegram.bot.api.method.SendMessage;
import it.pwned.telegram.bot.api.method.SendPhoto;
import it.pwned.telegram.bot.api.method.SendSticker;
import it.pwned.telegram.bot.api.method.SendVenue;
import it.pwned.telegram.bot.api.method.SendVideo;
import it.pwned.telegram.bot.api.method.SendVoice;
import it.pwned.telegram.bot.api.method.UnbanChatMember;
import it.pwned.telegram.bot.api.method.enums.ApiMethodBaseUri;
import it.pwned.telegram.bot.api.method.game.GetGameHighScores;
import it.pwned.telegram.bot.api.method.game.SendGame;
import it.pwned.telegram.bot.api.method.game.SetGameScore;
import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.game.GameHighScore;

public class TelegramBotRestApiClient implements ApiClient {

	private final ObjectMapper mapper;
	private final RestTemplate restTemplate;

	private final UriTemplate apiUriTemplate;
	private final UriTemplate fileUriTemplate;

	/*
	 * For anyone wondering why this map exists:
	 * http://stackoverflow.com/questions/21987295/using-spring-resttemplate-in-
	 * generic-method-with-generic-parameter
	 * 
	 * ParameterizedTypeReference uses Class#getGenericSuperclass which states:
	 * Returns the Type representing the direct superclass of the entity (class,
	 * interface, primitive type or void) represented by this Class. If the
	 * superclass is a parameterized type, the Type object returned must
	 * accurately reflect the actual type parameters used in the source code.
	 */
	@SuppressWarnings("rawtypes")
	private static final Map<Class, ParameterizedTypeReference> typeRefs = initializeParameterizedTypeReferences();

	@SuppressWarnings("rawtypes")
	private static Map<Class, ParameterizedTypeReference> initializeParameterizedTypeReferences() {

		Map<Class<?>, ParameterizedTypeReference> tmp = new HashMap<Class<?>, ParameterizedTypeReference>();
		
		tmp.put(GetUpdates.class, new ParameterizedTypeReference<Response<List<Update>>>() {
		});

		tmp.put(GetMe.class, new ParameterizedTypeReference<Response<User>>() {
		});

		tmp.put(SendMessage.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(ForwardMessage.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendPhoto.class, new ParameterizedTypeReference<Response<Message>>() {
		});

		tmp.put(SendAudio.class, new ParameterizedTypeReference<Response<Message>>() {
		});
		
		tmp.put(SendDocument.class, new ParameterizedTypeReference<Response<Message>>() {
		});
				
		tmp.put(SendSticker.class, new ParameterizedTypeReference<Response<Message>>() {
		});		
		
		tmp.put(SendVideo.class, new ParameterizedTypeReference<Response<Message>>() {
		});		
		
		tmp.put(SendVoice.class, new ParameterizedTypeReference<Response<Message>>() {
		});				
		
		tmp.put(SendLocation.class, new ParameterizedTypeReference<Response<Message>>() {
		});		
		
		tmp.put(SendVenue.class, new ParameterizedTypeReference<Response<Message>>() {
		});				
		
		tmp.put(SendContact.class, new ParameterizedTypeReference<Response<Message>>() {
		});		
		
		tmp.put(SendChatAction.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});		
		
		tmp.put(GetUserProfilePhotos.class, new ParameterizedTypeReference<Response<UserProfilePhotos>>() {
		});		
		
		tmp.put(GetFile.class, new ParameterizedTypeReference<Response<TelegramFile>>() {
		});		
				
		tmp.put(KickChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});
		
		tmp.put(LeaveChat.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});		
		
		tmp.put(UnbanChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});		
		
		tmp.put(GetChat.class, new ParameterizedTypeReference<Response<Chat>>() {
		});				
		
		tmp.put(GetChatAdministrators.class, new ParameterizedTypeReference<Response<List<ChatMember>>>() {
		});
		
		tmp.put(GetChatMembersCount.class, new ParameterizedTypeReference<Response<Integer>>() {
		});		
		
		tmp.put(GetChatMember.class, new ParameterizedTypeReference<Response<ChatMember>>() {
		});		
		
		tmp.put(AnswerCallbackQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});		
		
		tmp.put(EditMessageText.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});			
		
		tmp.put(EditMessageCaption.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});			
		
		tmp.put(EditMessageReplyMarkup.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});			
		
		tmp.put(AnswerInlineQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
		});			
		
		tmp.put(SendGame.class, new ParameterizedTypeReference<Response<Message>>() {
		});			
		
		tmp.put(SetGameScore.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
		});			
			
		tmp.put(GetGameHighScores.class, new ParameterizedTypeReference<Response<List<GameHighScore>>>() {
		});

		return Collections.unmodifiableMap(tmp);
	}

	public TelegramBotRestApiClient(String token, RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.mapper = getObjectMapperFromRestTemplate(restTemplate);

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

		final ParameterizedTypeReference<Response<T>> typeRef = typeRefs.get(m.getClass());
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
