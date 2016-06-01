package it.pwned.telegram.bot.api.rest;

import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

public class TelegramBotRestApi implements TelegramBotApi {

	private final ObjectMapper mapper;
	private final RestTemplate rest;

	private final UriTemplate api_uri_template;
	private final UriTemplate file_uri_template;

	public TelegramBotRestApi(String token, ObjectMapper mapper, RestTemplate rest) {
		this.mapper = mapper;
		this.rest = rest;
		this.api_uri_template = new UriTemplate("https://api.telegram.org/bot" + token + "/{method}");
		this.file_uri_template = new UriTemplate("https://api.telegram.org/file/bot" + token + "/{file_path}");
	}

	@Override
	public User getMe() throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<User> builder = new TelegramBotRestApiCall.Builder<User>("getMe", api_uri_template,
				mapper, rest, User.class);

		builder.setHttpMethod(HttpMethod.GET);

		return builder.build().call();

	}

	@Override
	public Update[] getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Update[]> builder = new TelegramBotRestApiCall.Builder<Update[]>("getUpdates",
				api_uri_template, mapper, rest, Update[].class);

		builder.setParam("offset", offset, false, false).setParam("limit", limit, false, false).setParam("timeout", timeout,
				false, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
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

		TelegramBotRestApiCall.Builder<UserProfilePhotos> builder = new TelegramBotRestApiCall.Builder<UserProfilePhotos>(
				"getUserProfilePhotos", api_uri_template, mapper, rest, UserProfilePhotos.class);

		builder.setParam("user_id", user_id, true, false).setParam("offset", offset, false, false).setParam("limit", limit,
				false, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
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

		TelegramBotRestApiCall.Builder<TelegramFile> builder = new TelegramBotRestApiCall.Builder<TelegramFile>("getFile",
				api_uri_template, mapper, rest, TelegramFile.class);

		builder.setParam("file_id", file_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();

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

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendMessage",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("text", text, true, false)
				.setParam("parse_mode", parse_mode, false, false)
				.setParam("disable_web_page_preview", disable_web_page_preview, false, false)
				.setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message forwardMessage(String chat_id, String from_chat_id, Boolean disable_notification, int message_id)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("forwardMessage",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("from_chat_id", from_chat_id, true, false)
				.setParam("disable_notification", disable_notification, false, false)
				.setParam("message_id", message_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendLocation(String chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendLocation",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("latitude", latitude, true, false)
				.setParam("longitude", longitude, true, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public void sendChatAction(String chat_id, ChatAction action) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<String> builder = new TelegramBotRestApiCall.Builder<String>("sendChatAction",
				api_uri_template, mapper, rest, String.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("action", action.toString(), true, false);

		builder.build().call();
	}

	@Override
	public Message sendPhoto(String chat_id, Resource photo, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendPhoto",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("photo", photo, true, false)
				.setParam("caption", caption, false, false).setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendAudio",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("audio", audio, true, false)
				.setParam("duration", duration, false, false).setParam("performer", performer, false, false)
				.setParam("title", title, false, false).setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendDocument(String chat_id, Resource document, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendDocument",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("document", document, true, false)
				.setParam("caption", caption, false, false).setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendSticker(String chat_id, Resource sticker, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendSticker",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("sticker", sticker, true, false)
				.setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendVideo(String chat_id, Resource video, Integer duration, Integer width, Integer height,
			String caption, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendVideo",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("video", video, true, false)
				.setParam("duration", duration, false, false).setParam("width", width, false, false)
				.setParam("height", height, false, false).setParam("caption", caption, false, false)
				.setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendVoice(String chat_id, Resource voice, Integer duration, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendVoice",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("voice", voice, true, false)
				.setParam("duration", duration, false, false)
				.setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean answerInlineQuery(String inline_query_id, List<InlineQueryResult> results, Integer cache_time,
			Boolean is_personal, String next_offset, String switch_pm_text, String switch_pm_parameter)
					throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("answerInlineQuery",
				api_uri_template, mapper, rest, Boolean.class);

		builder.setParam("inline_query_id", inline_query_id, true, false).setParam("results", results, true, false)
				.setParam("cache_time", cache_time, false, false).setParam("is_personal", is_personal, false, false)
				.setParam("next_offset", next_offset, false, false).setParam("switch_pm_text", switch_pm_text, false, false)
				.setParam("switch_pm_parameter", switch_pm_parameter, false, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
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

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendVenue",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("latitude", latitude, true, false)
				.setParam("longitude", longitude, true, false).setParam("title", title, true, false)
				.setParam("address", address, true, false).setParam("foursquare_id", foursquare_id, false, false)
				.setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
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

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendContact",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("phone_number", phone_number, true, false)
				.setParam("first_name", first_name, true, false).setParam("last_name", last_name, false, false)
				.setParam("disable_notification", disable_notification, false, false)
				.setParam("reply_to_message_id", reply_to_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean kickChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		return kickChatMember(Long.toString(chat_id), user_id);
	}

	@Override
	public Boolean kickChatMember(String chat_id, int user_id) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("kickChatMember",
				api_uri_template, mapper, rest, Boolean.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("user_id", user_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean unbanChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		return unbanChatMember(Long.toString(chat_id), user_id);
	}

	@Override
	public Boolean unbanChatMember(String chat_id, int user_id) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("unbanChatMember",
				api_uri_template, mapper, rest, Boolean.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("user_id", user_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean answerCallbackQuery(String callback_query_id, String text, Boolean show_alert)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("answerCallbackQuery",
				api_uri_template, mapper, rest, Boolean.class);

		builder.setParam("callback_query_id", callback_query_id, true, false).setParam("text", text, false, false)
				.setParam("show_alert", show_alert, false, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
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

		if (inline_message_id == null && (chat_id == null || message_id == null))
			throw new InvalidParameterException("(editMessageText) chat_id+message_id or inline_message_id are mandatory");

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("editMessageText",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, false, false).setParam("message_id", message_id, false, false)
				.setParam("inline_message_id", inline_message_id, false, false).setParam("text", text, true, false)
				.setParam("parse_mode", parse_mode, false, false)
				.setParam("disable_web_page_preview", disable_web_page_preview, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
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

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("editMessageCaption",
				api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, false, false).setParam("message_id", message_id, false, false)
				.setParam("inline_message_id", inline_message_id, false, false).setParam("caption", caption, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
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

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>(
				"editMessageReplyMarkup", api_uri_template, mapper, rest, Message.class);

		builder.setParam("chat_id", chat_id, false, false).setParam("message_id", message_id, false, false)
				.setParam("inline_message_id", inline_message_id, false, false)
				.setParam("reply_markup", reply_markup, false, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Chat getChat(long chat_id) throws TelegramBotApiException {
		return getChat(Long.toString(chat_id));
	}

	@Override
	public Chat getChat(String chat_id) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Chat> builder = new TelegramBotRestApiCall.Builder<Chat>("getChat", api_uri_template,
				mapper, rest, Chat.class);

		builder.setParam("chat_id", chat_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public ChatMember[] getChatAdministrators(long chat_id) throws TelegramBotApiException {
		return getChatAdministrators(Long.toString(chat_id));
	}

	@Override
	public ChatMember[] getChatAdministrators(String chat_id) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<ChatMember[]> builder = new TelegramBotRestApiCall.Builder<ChatMember[]>(
				"getChatAdministrators", api_uri_template, mapper, rest, ChatMember[].class);

		builder.setParam("chat_id", chat_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public ChatMember getChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		return getChatMember(Long.toString(chat_id), user_id);
	}

	@Override
	public ChatMember getChatMember(String chat_id, int user_id) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<ChatMember> builder = new TelegramBotRestApiCall.Builder<ChatMember>("getChatMember",
				api_uri_template, mapper, rest, ChatMember.class);

		builder.setParam("chat_id", chat_id, true, false).setParam("user_id", user_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public int getChatMembersCount(long chat_id) throws TelegramBotApiException {
		return getChatMembersCount(Long.toString(chat_id));
	}

	@Override
	public int getChatMembersCount(String chat_id) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Integer> builder = new TelegramBotRestApiCall.Builder<Integer>("getChatMember",
				api_uri_template, mapper, rest, Integer.class);

		builder.setParam("chat_id", chat_id, true, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

}
