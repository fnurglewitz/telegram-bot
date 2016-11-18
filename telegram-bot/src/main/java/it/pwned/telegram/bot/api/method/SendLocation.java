package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;

@ApiMethod("sendLocation")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendLocation extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("latitude")
	public final Float latitude;

	@ApiMethodParameter("longitude")
	public final Float longitude;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendLocation(ChatId chatId, float latitude, float longitude) {
		this(chatId, latitude, longitude, null, null, null);
	}

	public SendLocation(ChatId chatId, float latitude, float longitude, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.latitude = latitude;
		this.longitude = longitude;
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

}
