package it.pwned.telegram.bot.api;

import java.io.IOException;
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
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.api.debug.LoggingRequestInterceptor;
import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

public class TelegramBotRestApi implements TelegramBotApi {

	private final ObjectMapper mapper;

	private RestTemplate rest;
	private final UriTemplate api_uri_template;
	private final UriTemplate file_uri_template;
	private final HttpHeaders multipart_headers;

	public TelegramBotRestApi(String token, ObjectMapper mapper) {
		this.mapper = mapper;

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

	private String serializeToJsonString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	@Override
	public User getMe() throws TelegramBotApiException {
		Response<User> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getMe"), HttpMethod.GET, null,
					new ParameterizedTypeReference<Response<User>>() {
					}).getBody();
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

	@Override
	public Message sendMessage(long chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		return sendMessage(Long.toString(chat_id), text, parse_mode, disable_web_page_preview, disable_notification,
				reply_to_message_id, reply_markup);
	}

	@Override
	public Message forwardMessage(long chat_id, long from_chat_id, Boolean disable_notification, int message_id)
			throws TelegramBotApiException {

		return forwardMessage(Long.toString(chat_id), Long.toString(from_chat_id), disable_notification, message_id);
	}

	@Override
	public Message sendLocation(long chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {

		return sendLocation(Long.toString(chat_id), latitude, longitude, reply_to_message_id, reply_markup);
	}

	@Override
	public void sendChatAction(long chat_id, ChatAction action) throws TelegramBotApiException {
		sendChatAction(Long.toString(chat_id), action);
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

	@Override
	public Message sendPhoto(long chat_id, Resource photo, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		return sendPhoto(Long.toString(chat_id), photo, caption, disable_notification, reply_to_message_id, reply_markup);
	}

	@Override
	public Message sendAudio(long chat_id, Resource audio, Integer duration, String performer, String title,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		return sendAudio(Long.toString(chat_id), audio, duration, performer, title, disable_notification,
				reply_to_message_id, reply_markup);
	}

	@Override
	public Message sendDocument(long chat_id, Resource document, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		return sendDocument(Long.toString(chat_id), document, caption, disable_notification, reply_to_message_id,
				reply_markup);
	}

	@Override
	public Message sendSticker(long chat_id, Resource sticker, Boolean disable_notification, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {

		return sendSticker(Long.toString(chat_id), sticker, disable_notification, reply_to_message_id, reply_markup);
	}

	@Override
	public Message sendVideo(long chat_id, Resource video, Integer duration, Integer width, Integer height,
			String caption, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		return sendVideo(Long.toString(chat_id), video, duration, width, height, caption, disable_notification,
				reply_to_message_id, reply_markup);
	}

	@Override
	public Message sendVoice(long chat_id, Resource voice, Integer duration, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		return sendVoice(Long.toString(chat_id), voice, duration, disable_notification, reply_to_message_id, reply_markup);
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
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		if (chat_id == null || text == null)
			throw new InvalidParameterException("(sendMessage) Null value is not allowed for fields: chat_id, text");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("text", text);

		if (parse_mode != null)
			body.add("parse_mode", parse_mode);

		if (disable_web_page_preview != null)
			body.add("disable_web_page_preview", disable_web_page_preview);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendMessage"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message forwardMessage(String chat_id, String from_chat_id, Boolean disable_notification, int message_id)
			throws TelegramBotApiException {
		if (chat_id == null || from_chat_id == null)
			throw new InvalidParameterException(
					"(forwardMessage) Null value is not allowed for fields: chat_id, from_chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("from_chat_id", from_chat_id);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		body.add("message_id", message_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("forwardMessage"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendLocation"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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
	public Message sendPhoto(String chat_id, Resource photo, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || photo == null)
			throw new InvalidParameterException("(sendPhoto) Null value is not allowed for fields: chat_id, photo");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("photo", photo);

		if (caption != null)
			body.add("caption", caption);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendPhoto"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
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

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendAudio"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message sendDocument(String chat_id, Resource document, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || document == null)
			throw new InvalidParameterException("(sendDocument) Null value is not allowed for fields: chat_id, document");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("document", document);

		if (caption != null)
			body.add("caption", caption);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendDocument"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message sendSticker(String chat_id, Resource sticker, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || sticker == null)
			throw new InvalidParameterException("(sendSticker) Null value is not allowed for fields: chat_id, sticker");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("sticker", sticker);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendSticker"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message sendVideo(String chat_id, Resource video, Integer duration, Integer width, Integer height,
			String caption, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		if (chat_id == null || video == null)
			throw new InvalidParameterException("(sendVideo) Null value is not allowed for fields: chat_id, video");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("video", video);

		if (duration != null)
			body.add("duration", duration);

		if (width != null)
			body.add("width", width);

		if (height != null)
			body.add("height", height);

		if (caption != null)
			body.add("caption", caption);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendVideo"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message sendVoice(String chat_id, Resource voice, Integer duration, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		if (chat_id == null || voice == null)
			throw new InvalidParameterException("(sendVoice) Null value is not allowed for fields: chat_id, voice");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("voice", voice);

		if (duration != null)
			body.add("duration", duration);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendVoice"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Boolean answerInlineQuery(String inline_query_id, List<InlineQueryResult> results, Integer cache_time,
			Boolean is_personal, String next_offset, String switch_pm_text, String switch_pm_parameter)
					throws TelegramBotApiException {

		if (inline_query_id == null || results == null || results.size() == 0)
			throw new InvalidParameterException(
					"(answerInlineQuery) Null value is not allowed for fields: inline_query_id, results");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("inline_query_id", inline_query_id);
		body.add("results", results);

		if (cache_time != null)
			body.add("cache_time", cache_time);

		if (is_personal != null)
			body.add("is_personal", is_personal);

		if (next_offset != null)
			body.add("next_offset", next_offset);

		if (switch_pm_text != null)
			body.add("switch_pm_text", switch_pm_text);

		if (switch_pm_parameter != null)
			body.add("switch_pm_parameter", switch_pm_parameter);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Boolean> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("answerInlineQuery"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Boolean>>() {
					}).getBody();
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

	@Override
	public Message sendVenue(long chat_id, float latitude, float longitude, String title, String address,
			String foursquare_id, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		return sendVenue(Long.toString(chat_id), latitude, longitude, title, address, foursquare_id, disable_notification,
				reply_to_message_id, reply_markup);
	}

	@Override
	public Message sendVenue(String chat_id, float latitude, float longitude, String title, String address,
			String foursquare_id, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		if (chat_id == null || title == null || address == null)
			throw new InvalidParameterException("(sendVenue) Null value is not allowed for fields: chat_id, title, address");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("latitude", latitude);
		body.add("longitude", longitude);
		body.add("title", title);
		body.add("address", address);

		if (foursquare_id != null)
			body.add("foursquare_id", foursquare_id);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendVenue"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message sendContact(long chat_id, String phone_number, String first_name, String last_name,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		return sendContact(Long.toString(chat_id), phone_number, first_name, last_name, disable_notification,
				reply_to_message_id, reply_markup);
	}

	@Override
	public Message sendContact(String chat_id, String phone_number, String first_name, String last_name,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		if (chat_id == null || phone_number == null || first_name == null)
			throw new InvalidParameterException(
					"(sendContact) Null value is not allowed for fields: chat_id, phone_number, first_name");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("phone_number", phone_number);
		body.add("first_name", first_name);

		if (last_name != null)
			body.add("last_name", last_name);

		if (disable_notification != null)
			body.add("disable_notification", disable_notification);

		if (reply_to_message_id != null)
			body.add("reply_to_message_id", reply_to_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("sendContact"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Boolean kickChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		return kickChatMember(Long.toString(chat_id), user_id);
	}

	@Override
	public Boolean kickChatMember(String chat_id, int user_id) throws TelegramBotApiException {

		if (chat_id == null)
			throw new InvalidParameterException("(kickChatMember) Null value is not allowed for field: chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("user_id", user_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Boolean> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("kickChatMember"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Boolean>>() {
					}).getBody();
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

	@Override
	public Boolean unbanChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		return unbanChatMember(Long.toString(chat_id), user_id);
	}

	@Override
	public Boolean unbanChatMember(String chat_id, int user_id) throws TelegramBotApiException {

		if (chat_id == null)
			throw new InvalidParameterException("(unbanChatMember) Null value is not allowed for field: chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("user_id", user_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Boolean> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("unbanChatMember"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Boolean>>() {
					}).getBody();
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

	@Override
	public Boolean answerCallbackQuery(String callback_query_id, String text, Boolean show_alert)
			throws TelegramBotApiException {

		if (callback_query_id == null)
			throw new InvalidParameterException(
					"(answerCallbackQuery) Null value is not allowed for field: callback_query_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("callback_query_id", callback_query_id);
		body.add("text", text);
		body.add("show_alert", show_alert);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Boolean> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("answerCallbackQuery"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Boolean>>() {
					}).getBody();
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

	@Override
	public Message editMessageText(long chat_id, Integer message_id, String inline_message_id, String text,
			String parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup)
					throws TelegramBotApiException {

		return editMessageText(Long.toString(chat_id), message_id, inline_message_id, text, parse_mode,
				disable_web_page_preview, reply_markup);
	}

	@Override
	public Message editMessageText(String chat_id, Integer message_id, String inline_message_id, String text,
			String parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup)
					throws TelegramBotApiException {

		if (text == null)
			throw new InvalidParameterException("(editMessageText) Null value is not allowed for field: text");

		if (inline_message_id == null && (chat_id == null || message_id == null))
			throw new InvalidParameterException("(editMessageText) chat_id+message_id or inline_message_id are mandatory");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		if (chat_id != null)
			body.add("chat_id", chat_id);
		if (message_id != null)
			body.add("message_id", message_id);
		if (inline_message_id != null)
			body.add("inline_message_id", inline_message_id);

		body.add("text", text);

		if (parse_mode != null)
			body.add("parse_mode", parse_mode);

		if (disable_web_page_preview != null)
			body.add("disable_web_page_preview", disable_web_page_preview);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("editMessageText"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message editMessageCaption(long chat_id, Integer message_id, String inline_message_id, String caption,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {

		return editMessageCaption(Long.toString(chat_id), message_id, inline_message_id, caption, reply_markup);
	}

	@Override
	public Message editMessageCaption(String chat_id, Integer message_id, String inline_message_id, String caption,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {

		if (inline_message_id == null && (chat_id == null || message_id == null))
			throw new InvalidParameterException("(editMessageText) chat_id+message_id or inline_message_id are mandatory");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		if (chat_id != null)
			body.add("chat_id", chat_id);
		if (message_id != null)
			body.add("message_id", message_id);
		if (inline_message_id != null)
			body.add("inline_message_id", inline_message_id);

		if (caption != null)
			body.add("caption", caption);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("editMessageCaption"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Message editMessageReplyMarkup(long chat_id, Integer message_id, String inline_message_id,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {

		return editMessageReplyMarkup(Long.toString(chat_id), message_id, inline_message_id, reply_markup);
	}

	@Override
	public Message editMessageReplyMarkup(String chat_id, Integer message_id, String inline_message_id,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {

		if (inline_message_id == null && (chat_id == null || message_id == null))
			throw new InvalidParameterException("(editMessageText) chat_id+message_id or inline_message_id are mandatory");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		if (chat_id != null)
			body.add("chat_id", chat_id);
		if (message_id != null)
			body.add("message_id", message_id);
		if (inline_message_id != null)
			body.add("inline_message_id", inline_message_id);

		if (reply_markup != null)
			body.add("reply_markup", serializeToJsonString(reply_markup));

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Message> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("editMessageReplyMarkup"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Message>>() {
					}).getBody();
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

	@Override
	public Chat getChat(long chat_id) throws TelegramBotApiException {
		return getChat(Long.toString(chat_id));
	}

	@Override
	public Chat getChat(String chat_id) throws TelegramBotApiException {

		if (chat_id == null)
			throw new InvalidParameterException("(getChat) Null value is not allowed for field: chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Chat> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getChat"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Chat>>() {
					}).getBody();
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

	@Override
	public ChatMember[] getChatAdministrators(long chat_id) throws TelegramBotApiException {
		return getChatAdministrators(Long.toString(chat_id));
	}

	@Override
	public ChatMember[] getChatAdministrators(String chat_id) throws TelegramBotApiException {

		if (chat_id == null)
			throw new InvalidParameterException("(getChatAdministrators) Null value is not allowed for field: chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<ChatMember[]> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getChatAdministrators"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<ChatMember[]>>() {
					}).getBody();
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

	@Override
	public ChatMember getChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		return getChatMember(Long.toString(chat_id), user_id);
	}

	@Override
	public ChatMember getChatMember(String chat_id, int user_id) throws TelegramBotApiException {

		if (chat_id == null)
			throw new InvalidParameterException("(getChatMember) Null value is not allowed for field: chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);
		body.add("user_id", user_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<ChatMember> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getChatMember"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<ChatMember>>() {
					}).getBody();
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

	@Override
	public int getChatMembersCount(long chat_id) throws TelegramBotApiException {
		return getChatMembersCount(Long.toString(chat_id));
	}

	@Override
	public int getChatMembersCount(String chat_id) throws TelegramBotApiException {

		if (chat_id == null)
			throw new InvalidParameterException("(getChatMembersCount) Null value is not allowed for field: chat_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("chat_id", chat_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<Integer> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getChatMembersCount"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<Integer>>() {
					}).getBody();
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

}
