package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

import org.springframework.core.io.Resource;
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

@ApiMethod("sendSticker")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendSticker extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("sticker")
	private Resource sticker;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendSticker(ChatId chatId, Resource sticker) {
		super();

		setChatId(chatId);
		setSticker(sticker);
	}

	public SendSticker setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendSticker setSticker(Resource sticker) {
		if (sticker == null)
			throw new InvalidParameterException("sticker cannot be null");

		this.sticker = sticker;
		return this;
	}

	public SendSticker setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendSticker setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendSticker setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public Resource getSticker() {
		return this.sticker;
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
