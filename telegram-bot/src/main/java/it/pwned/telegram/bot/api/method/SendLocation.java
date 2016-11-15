package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

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
	private ChatId chatId;

	@ApiMethodParameter("latitude")
	private Float latitude;

	@ApiMethodParameter("longitude")
	private Float longitude;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendLocation(ChatId chatId, float latitude, float longitude) {
		super();

		setChatId(chatId);
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public SendLocation setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendLocation setLatitude(float latitude) {
		this.latitude = latitude;
		return this;
	}

	public SendLocation setLongitude(float longitude) {
		this.longitude = longitude;
		return this;
	}

	public SendLocation setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendLocation setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendLocation setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public Boolean getDisableNotification() {
		return this.disableNotification;
	}

	public Integer getReplyToMessageId() {
		return this.replyToMessageId;
	}

	public AbstractKeyboardMarkup getReplyMarkup() {
		return this.replyMarkup;
	}

}
