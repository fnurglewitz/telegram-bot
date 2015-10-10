package it.pwned.telegram.bot.api;

import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.File;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;

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

		/*
		 * ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
		 * List<ClientHttpRequestInterceptor> ris = new
		 * ArrayList<ClientHttpRequestInterceptor>(); ris.add(ri);
		 * rest.setInterceptors(ris); rest.setRequestFactory(new
		 * BufferingClientHttpRequestFactory(new
		 * SimpleClientHttpRequestFactory()));
		 */
	}

	@Override
	public Response<User> getMe() {
		Response<User> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getMe"), HttpMethod.GET, null,
					new ParameterizedTypeReference<Response<User>>() {
					}).getBody();
		} catch (RestClientException e) {
			res = new Response<User>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Update[]> getUpdates(Integer offset, Integer limit, Integer timeout) {

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
			res = new Response<Update[]>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendMessage(int chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Integer reply_to_message_id, DummyKeyboard reply_markup) {

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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> forwardMessage(int chat_id, int from_chat_id, int message_id) {

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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendLocation(int chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {

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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public void sendChatAction(int chat_id, ChatAction action) {
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
	public Response<UserProfilePhotos> getUserProfilePhotos(int user_id, Integer offset, Integer limit) {

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
			res = new Response<UserProfilePhotos>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendPhoto(int chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendAudio(int chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendDocument(int chat_id, Resource document, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendSticker(int chat_id, Resource sticker, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendVideo(int chat_id, Resource video, Integer duration, String caption,
			Integer reply_to_message_id, DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendVoice(int chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<File> getFile(String file_id) {
		if (file_id == null)
			throw new InvalidParameterException("(getFile) Null value is not allowed for field: file_id");

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<String, Object>();

		body.add("file_id", file_id);

		HttpEntity<?> entity = new HttpEntity<Object>(body, multipart_headers);

		Response<File> res = null;

		try {
			res = rest.exchange(api_uri_template.expand("getFile"), HttpMethod.POST, entity,
					new ParameterizedTypeReference<Response<File>>() {
					}).getBody();
		} catch (RestClientException e) {
			res = new Response<File>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Resource getResourceFromTelegramFile(File file) {
		if (file == null)
			throw new InvalidParameterException(
					"(getResourceFromTelegramFile) Null value is not allowed for field: file");

		try {
			return new UrlResource(file_uri_template.expand(file.file_path));
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Override
	public Response<Message> sendMessage(String chat_id, String text, String parse_mode,
			Boolean disable_web_page_preview, Integer reply_to_message_id, DummyKeyboard reply_markup) {

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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> forwardMessage(String chat_id, String from_chat_id, int message_id) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendLocation(String chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public void sendChatAction(String chat_id, ChatAction action) {
		if (chat_id == null || action == null)
			throw new InvalidParameterException(
					"(sendChatAction) Null value is not allowed for fields: chat_id, action");

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
	public Response<Message> sendPhoto(String chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendDocument(String chat_id, Resource document, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
		if (chat_id == null || document == null)
			throw new InvalidParameterException(
					"(sendDocument) Null value is not allowed for fields: chat_id, document");

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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendSticker(String chat_id, Resource sticker, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendVideo(String chat_id, Resource video, Integer duration, String caption,
			Integer reply_to_message_id, DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

	@Override
	public Response<Message> sendVoice(String chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup) {
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
			res = new Response<Message>();
			res.ok = false;

			if (e instanceof HttpClientErrorException) {
				res.error_code = ((HttpClientErrorException) e).getStatusCode().value();
				res.description = ((HttpClientErrorException) e).getStatusText();
			}
		}

		return res;
	}

}