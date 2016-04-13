package it.pwned.telegram.bot.api;

import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import it.pwned.telegram.bot.api.debug.LoggingRequestInterceptor;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

public class TelegramBotRestApi implements TelegramBotApi {

	private RestTemplate rest;
	private final UriTemplate api_uri_template;
	private final UriTemplate file_uri_template;
	private final HttpHeaders multipart_headers;

	public TelegramBotRestApi(String token) {
		this.rest = new RestTemplate();
		this.api_uri_template = new UriTemplate("https://api.telegram.org/bot" + token + "/{method}");
		this.file_uri_template = new UriTemplate("https://api.telegram.org/file/bot" + token + "/{file_path}");
		this.multipart_headers = new HttpHeaders();
		this.multipart_headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) rest.getRequestFactory();

		rf.setConnectTimeout(0);
		rf.setReadTimeout(0);

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

	}

	@Override
	public User getMe() throws TelegramBotApiException {
		Response<User> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getMe"), HttpMethod.GET, null,
					new ParameterizedTypeReference<Response<User>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Update[] getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException {

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		if (offset != null)
			body.add("offset", offset);

		if (limit != null)
			body.add("limit", limit);

		if (timeout != null)
			body.add("timeout", timeout);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Update[]> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getUpdates"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Update[]>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendMessage(long chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		if (text == null)
			throw new InvalidParameterException("(sendMessage) Null value is not allowed for field: text");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("text", text);

		if (parse_mode != null)
			body.add("parse_mode", parse_mode);

		if (disable_web_page_preview != null)
			body.add("disable_web_page_preview", disable_web_page_preview);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendMessage"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message forwardMessage(long chat_id, long from_chat_id, int message_id) throws TelegramBotApiException {

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("from_chat_id", from_chat_id);
		body.add("message_id", message_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("forwardMessage"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendLocation(long chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("latitude", latitude);
		body.add("longitude", longitude);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendLocation"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public void sendChatAction(long chat_id, ChatAction action) throws TelegramBotApiException {
		if (action == null)
			throw new InvalidParameterException("(sendChatAction) Null value is not allowed for field: action");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("action", action.toString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		try {
			rest.exchange(api_uri_template.expand("sendChatAction"), HttpMethod.POST, entity, String.class);
		} catch (RestClientException e) {
			// well, shit happens
		}
	}

	@Override
	public UserProfilePhotos getUserProfilePhotos(int user_id, Integer offset, Integer limit)
			throws TelegramBotApiException {

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("user_id", user_id);

		if (offset != null)
			body.add("offset", offset);

		if (limit != null)
			body.add("limit", limit);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<UserProfilePhotos> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getUserProfilePhotos"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<UserProfilePhotos>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendPhoto(long chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (photo == null)
			throw new InvalidParameterException("(sendPhoto) Null value is not allowed for field: photo");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("photo", photo);

		if (caption != null)
			body.add("caption", caption);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendPhoto"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendAudio(long chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (audio == null)
			throw new InvalidParameterException("(sendAudio) Null value is not allowed for field: audio");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("audio", audio);

		if (duration != null)
			body.add("duration", duration);

		if (performer != null)
			body.add("performer", performer);

		if (title != null)
			body.add("title", title);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendAudio"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendDocument(long chat_id, Resource document, Integer reply_to_message_id, DummyKeyboard reply_markup)
			throws TelegramBotApiException {
		if (document == null)
			throw new InvalidParameterException("(sendDocument) Null value is not allowed for field: document");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("document", document);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendDocument"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendSticker(long chat_id, Resource sticker, Integer reply_to_message_id, DummyKeyboard reply_markup)
			throws TelegramBotApiException {
		if (sticker == null)
			throw new InvalidParameterException("(sendSticker) Null value is not allowed for field: sticker");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("sticker", sticker);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendSticker"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendVideo(long chat_id, Resource video, Integer duration, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (video == null)
			throw new InvalidParameterException("(sendVideo) Null value is not allowed for field: video");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("video", video);

		if (duration != null)
			body.add("duration", duration);

		if (caption != null)
			body.add("caption", caption);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendVideo"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendVoice(long chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (voice == null)
			throw new InvalidParameterException("(sendVoice) Null value is not allowed for field: voice");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("voice", voice);

		if (reply_to_message_id != null)
			body.add("duration", duration);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendVoice"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public TelegramFile getFile(String file_id) throws TelegramBotApiException {
		if (file_id == null)
			throw new InvalidParameterException("(getFile) Null value is not allowed for field: file_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("file_id", file_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<TelegramFile> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getFile"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<TelegramFile>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Resource getResourceFromTelegramFile(TelegramFile file) {
		if (file == null)
			throw new InvalidParameterException("(getResourceFromTelegramFile) Null value is not allowed for field: file");

		try {
			return new UrlResource(file_uri_template.expand(file.file_path));
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Override
	public Message sendMessage(String chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		if (chat_id == null || text == null)
			throw new InvalidParameterException("(sendMessage) Null value is not allowed for fields: chat_id, text");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("text", text);

		if (parse_mode != null)
			body.add("parse_mode", parse_mode);

		if (disable_web_page_preview != null)
			body.add("disable_web_page_preview", disable_web_page_preview);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendMessage"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message forwardMessage(String chat_id, String from_chat_id, int message_id) throws TelegramBotApiException {
		if (chat_id == null || from_chat_id == null)
			throw new InvalidParameterException(
					"(forwardMessage) Null value is not allowed for fields: chat_id, from_chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("from_chat_id", from_chat_id);
		body.add("message_id", message_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("forwardMessage"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendLocation(String chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null)
			throw new InvalidParameterException("(sendLocation) Null value is not allowed for field: chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("latitude", latitude);
		body.add("longitude", longitude);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendLocation"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public void sendChatAction(String chat_id, ChatAction action) {
		if (chat_id == null || action == null)
			throw new InvalidParameterException("(sendChatAction) Null value is not allowed for fields: chat_id, action");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("action", action.toString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		try {
			rest.exchange(api_uri_template.expand("sendChatAction"), HttpMethod.POST, entity, String.class);
		} catch (RestClientException e) {
			// well, shit happens
		}
	}

	@Override
	public Message sendPhoto(String chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || photo == null)
			throw new InvalidParameterException("(sendPhoto) Null value is not allowed for fields: chat_id, photo");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("photo", photo);

		if (caption != null)
			body.add("caption", caption);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendPhoto"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || audio == null)
			throw new InvalidParameterException("(sendAudio) Null value is not allowed for fields: chat_id, audio");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("audio", audio);

		if (duration != null)
			body.add("duration", duration);

		if (performer != null)
			body.add("performer", performer);

		if (title != null)
			body.add("title", title);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendAudio"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendDocument(String chat_id, Resource document, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || document == null)
			throw new InvalidParameterException("(sendDocument) Null value is not allowed for fields: chat_id, document");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("document", document);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendDocument"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendSticker(String chat_id, Resource sticker, Integer reply_to_message_id, DummyKeyboard reply_markup)
			throws TelegramBotApiException {
		if (chat_id == null || sticker == null)
			throw new InvalidParameterException("(sendSticker) Null value is not allowed for fields: chat_id, sticker");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("sticker", sticker);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendSticker"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendVideo(String chat_id, Resource video, Integer duration, String caption,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || video == null)
			throw new InvalidParameterException("(sendVideo) Null value is not allowed for fields: chat_id, video");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("video", video);

		if (duration != null)
			body.add("duration", duration);

		if (caption != null)
			body.add("caption", caption);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendVideo"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Message sendVoice(String chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || voice == null)
			throw new InvalidParameterException("(sendVoice) Null value is not allowed for fields: chat_id, voice");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("voice", voice);

		if (reply_to_message_id != null)
			body.add("duration", duration);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", reply_markup.toJsonString());

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendVoice"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;
	}

	@Override
	public Boolean answerInlineQuery(String inline_query_id, List<InlineQueryResult> results, Integer cache_time,
			Boolean is_personal, String next_offset) throws TelegramBotApiException {

		if (inline_query_id == null || results == null || results.size() == 0)
			throw new InvalidParameterException("(sendVoice) Null value is not allowed for fields: inline_query_id, results");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("inline_query_id", inline_query_id);
		body.add("results", results);

		if (cache_time != null)
			body.add("cache_time", cache_time);

		if (is_personal != null)
			body.add("is_personal", is_personal);

		if (next_offset != null)
			body.add("next_offset", next_offset);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Boolean> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("answerInlineQuery"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Boolean>>() {
					}).getBody();
		} catch (RestClientException e) {
			throw new TelegramBotApiException(e);
		}

		if (!res.ok)
			throw new TelegramBotApiException(res.description);

		return res.result;

	}

}
