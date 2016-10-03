package it.pwned.telegram.bot.api.rest;

import java.net.MalformedURLException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.game.GameHighScore;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

public class TelegramBotRestApi implements TelegramBotApi {

	private final ObjectMapper mapper;
	private final RestTemplate restTemplate;

	private final UriTemplate apiUriTemplate;
	private final UriTemplate fileUriTemplate;

	public TelegramBotRestApi(String token, RestTemplate restTemplate) {
		this.mapper = getObjectMapperFromRestTemplate(restTemplate);
		this.restTemplate = restTemplate;
		this.apiUriTemplate = new UriTemplate("https://api.telegram.org/bot" + token + "/{method}");
		this.fileUriTemplate = new UriTemplate("https://api.telegram.org/file/bot" + token + "/{file_path}");
	}

	private ObjectMapper getObjectMapperFromRestTemplate(RestTemplate rest) {

		Optional<HttpMessageConverter<?>> maybeConverter = rest.getMessageConverters().stream().filter((c) -> {
			return c.getClass() == MappingJackson2HttpMessageConverter.class;
		}).findFirst();

		if (maybeConverter.isPresent())
			return ((MappingJackson2HttpMessageConverter) maybeConverter.get()).getObjectMapper();
		else
			return new ObjectMapper(); // this is bad

	}

	@Override
	public User getMe() throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<User> builder = new TelegramBotRestApiCall.Builder<User>("getMe", apiUriTemplate,
				mapper, restTemplate, User.class);

		builder.setHttpMethod(HttpMethod.GET);

		return builder.build().call();

	}

	@Override
	public List<Update> getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Update[]> builder = new TelegramBotRestApiCall.Builder<Update[]>("getUpdates",
				apiUriTemplate, mapper, restTemplate, Update[].class);

		builder.setParam("offset", offset, false).setParam("limit", limit, false).setParam("timeout", timeout, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);

		Update[] result = builder.build().call();
		Arrays.sort(result);

		return Collections.unmodifiableList(Arrays.asList(result));
	}

	@Override
	public UserProfilePhotos getUserProfilePhotos(int userId, Integer offset, Integer limit)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<UserProfilePhotos> builder = new TelegramBotRestApiCall.Builder<UserProfilePhotos>(
				"getUserProfilePhotos", apiUriTemplate, mapper, restTemplate, UserProfilePhotos.class);

		builder.setParam("user_id", userId, true).setParam("offset", offset, false).setParam("limit", limit, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public TelegramFile getFile(String fileId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<TelegramFile> builder = new TelegramBotRestApiCall.Builder<TelegramFile>("getFile",
				apiUriTemplate, mapper, restTemplate, TelegramFile.class);

		builder.setParam("file_id", fileId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();

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

	@Override
	public Message sendMessage(ChatId chatId, String text, ParseMode parseMode, Boolean disableWebPagePreview,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendMessage",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("text", text, true).setParam("parse_mode", parseMode, false)
				.setParam("disable_web_page_preview", disableWebPagePreview, false)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message forwardMessage(ChatId chatId, ChatId fromChatId, Boolean disableNotification, int messageId)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("forwardMessage",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("from_chat_id", fromChatId, true)
				.setParam("disable_notification", disableNotification, false).setParam("message_id", messageId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendLocation(ChatId chatId, float latitude, float longitude, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendLocation",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("latitude", latitude, true)
				.setParam("longitude", longitude, true).setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public void sendChatAction(ChatId chatId, ChatAction action) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<String> builder = new TelegramBotRestApiCall.Builder<String>("sendChatAction",
				apiUriTemplate, mapper, restTemplate, String.class);

		builder.setParam("chat_id", chatId, true).setParam("action", action, true);

		builder.build().call();
	}

	@Override
	public Message sendPhoto(ChatId chatId, Resource photo, String caption, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendPhoto",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("photo", photo, true).setParam("caption", caption, false)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendAudio(ChatId chatId, Resource audio, String caption, Integer duration, String performer,
			String title, Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendAudio",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("audio", audio, true).setParam("caption", caption, false)
				.setParam("duration", duration, false).setParam("performer", performer, false).setParam("title", title, false)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendDocument(ChatId chatId, Resource document, String caption, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendDocument",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("document", document, true).setParam("caption", caption, false)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendSticker(ChatId chatId, Resource sticker, Boolean disableNotification, Integer replyToMessageId,
			AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendSticker",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("sticker", sticker, true)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendVideo(ChatId chatId, Resource video, Integer duration, Integer width, Integer height,
			String caption, Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendVideo",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("video", video, true).setParam("duration", duration, false)
				.setParam("width", width, false).setParam("height", height, false).setParam("caption", caption, false)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendVoice(ChatId chatId, Resource voice, String caption, Integer duration, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendVoice",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("voice", voice, true).setParam("caption", caption, false)
				.setParam("duration", duration, false).setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cacheTime,
			Boolean isPersonal, String nextOffset, String switchPmText, String switchPmParameter)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("answerInlineQuery",
				apiUriTemplate, mapper, restTemplate, Boolean.class);

		builder.setParam("inline_query_id", inlineQueryId, true).setParam("results", results, true)
				.setParam("cache_time", cacheTime, false).setParam("is_personal", isPersonal, false)
				.setParam("next_offset", nextOffset, false).setParam("switch_pm_text", switchPmText, false)
				.setParam("switch_pm_parameter", switchPmParameter, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendVenue(ChatId chatId, float latitude, float longitude, String title, String address,
			String foursquareId, Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendVenue",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("latitude", latitude, true)
				.setParam("longitude", longitude, true).setParam("title", title, true).setParam("address", address, true)
				.setParam("foursquare_id", foursquareId, false).setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendContact(ChatId chatId, String phoneNumber, String firstName, String lastName,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendContact",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("phone_number", phoneNumber, true)
				.setParam("first_name", firstName, true).setParam("last_name", lastName, false)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean kickChatMember(ChatId chatId, int userId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("kickChatMember",
				apiUriTemplate, mapper, restTemplate, Boolean.class);

		builder.setParam("chat_id", chatId, true).setParam("user_id", userId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean unbanChatMember(ChatId chatId, int userId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("unbanChatMember",
				apiUriTemplate, mapper, restTemplate, Boolean.class);

		builder.setParam("chat_id", chatId, true).setParam("user_id", userId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean answerCallbackQuery(String callbackQueryId, String text, Boolean showAlert, String url)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("answerCallbackQuery",
				apiUriTemplate, mapper, restTemplate, Boolean.class);

		builder.setParam("callback_query_id", callbackQueryId, true).setParam("text", text, false)
				.setParam("show_alert", showAlert, false).setParam("url", url, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public BooleanOrMessage editMessageText(ChatId chatId, Integer messageId, String inlineMessageId, String text,
			ParseMode parseMode, Boolean disableWebPagePreview, InlineKeyboardMarkup replyMarkup)
			throws TelegramBotApiException {

		if (inlineMessageId == null && (chatId == null || messageId == null))
			throw new InvalidParameterException("(editMessageText) chat_id+message_id or inline_message_id are mandatory");

		TelegramBotRestApiCall.Builder<BooleanOrMessage> builder = new TelegramBotRestApiCall.Builder<BooleanOrMessage>(
				"editMessageText", apiUriTemplate, mapper, restTemplate, BooleanOrMessage.class);

		builder.setParam("chat_id", chatId, false).setParam("message_id", messageId, false)
				.setParam("inline_message_id", inlineMessageId, false).setParam("text", text, true)
				.setParam("parse_mode", parseMode, false).setParam("disable_web_page_preview", disableWebPagePreview, false)
				.setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public BooleanOrMessage editMessageCaption(ChatId chatId, Integer messageId, String inlineMessageId, String caption,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		if (inlineMessageId == null && (chatId == null || messageId == null))
			throw new InvalidParameterException("(editMessageText) chat_id+message_id or inline_message_id are mandatory");

		TelegramBotRestApiCall.Builder<BooleanOrMessage> builder = new TelegramBotRestApiCall.Builder<BooleanOrMessage>(
				"editMessageCaption", apiUriTemplate, mapper, restTemplate, BooleanOrMessage.class);

		builder.setParam("chat_id", chatId, false).setParam("message_id", messageId, false)
				.setParam("inline_message_id", inlineMessageId, false).setParam("caption", caption, false)
				.setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public BooleanOrMessage editMessageReplyMarkup(ChatId chatId, Integer messageId, String inlineMessageId,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		if (inlineMessageId == null && (chatId == null || messageId == null))
			throw new InvalidParameterException("(editMessageText) chat_id+message_id or inline_message_id are mandatory");

		TelegramBotRestApiCall.Builder<BooleanOrMessage> builder = new TelegramBotRestApiCall.Builder<BooleanOrMessage>(
				"editMessageReplyMarkup", apiUriTemplate, mapper, restTemplate, BooleanOrMessage.class);

		builder.setParam("chat_id", chatId, false).setParam("message_id", messageId, false)
				.setParam("inline_message_id", inlineMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Chat getChat(ChatId chatId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Chat> builder = new TelegramBotRestApiCall.Builder<Chat>("getChat", apiUriTemplate,
				mapper, restTemplate, Chat.class);

		builder.setParam("chat_id", chatId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public List<ChatMember> getChatAdministrators(ChatId chatId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<ChatMember[]> builder = new TelegramBotRestApiCall.Builder<ChatMember[]>(
				"getChatAdministrators", apiUriTemplate, mapper, restTemplate, ChatMember[].class);

		builder.setParam("chat_id", chatId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		ChatMember[] result = builder.build().call();

		return Collections.unmodifiableList(Arrays.asList(result));
	}

	@Override
	public ChatMember getChatMember(ChatId chatId, int userId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<ChatMember> builder = new TelegramBotRestApiCall.Builder<ChatMember>("getChatMember",
				apiUriTemplate, mapper, restTemplate, ChatMember.class);

		builder.setParam("chat_id", chatId, true).setParam("user_id", userId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public int getChatMembersCount(ChatId chatId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Integer> builder = new TelegramBotRestApiCall.Builder<Integer>("getChatMembersCount",
				apiUriTemplate, mapper, restTemplate, Integer.class);

		builder.setParam("chat_id", chatId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Boolean leaveChat(ChatId chatId) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Boolean> builder = new TelegramBotRestApiCall.Builder<Boolean>("leaveChat",
				apiUriTemplate, mapper, restTemplate, Boolean.class);

		builder.setParam("chat_id", chatId, true);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public Message sendGame(ChatId chatId, String gameShortName, Boolean disableNotification, Integer replyToMessageId,
			AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<Message> builder = new TelegramBotRestApiCall.Builder<Message>("sendGame",
				apiUriTemplate, mapper, restTemplate, Message.class);

		builder.setParam("chat_id", chatId, true).setParam("game_short_name", gameShortName, true)
				.setParam("disable_notification", disableNotification, false)
				.setParam("reply_to_message_id", replyToMessageId, false).setParam("reply_markup", replyMarkup, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public BooleanOrMessage setGameScore(int userId, int score, ChatId chatId, Integer messageId, String inlineMessageId,
			Boolean editMessage) throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<BooleanOrMessage> builder = new TelegramBotRestApiCall.Builder<BooleanOrMessage>(
				"setGameScore", apiUriTemplate, mapper, restTemplate, BooleanOrMessage.class);

		builder.setParam("user_id", userId, true).setParam("score", score, true).setParam("chat_id", chatId, false)
				.setParam("message_id", messageId, false).setParam("inline_message_id", inlineMessageId, false)
				.setParam("edit_message", editMessage, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		return builder.build().call();
	}

	@Override
	public List<GameHighScore> getGameHighScores(int userId, ChatId chatId, Integer messageId, String inlineMessageId)
			throws TelegramBotApiException {

		TelegramBotRestApiCall.Builder<GameHighScore[]> builder = new TelegramBotRestApiCall.Builder<GameHighScore[]>(
				"getGameHighScores", apiUriTemplate, mapper, restTemplate, GameHighScore[].class);

		builder.setParam("user_id", userId, true).setParam("chat_id", chatId, false)
				.setParam("message_id", messageId, false).setParam("inline_message_id", inlineMessageId, false);

		builder.setContentType(MediaType.MULTIPART_FORM_DATA);
		GameHighScore[] result = builder.build().call();

		return Collections.unmodifiableList(Arrays.asList(result));

	}

}
