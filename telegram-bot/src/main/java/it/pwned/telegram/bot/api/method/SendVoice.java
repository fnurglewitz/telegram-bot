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

@ApiMethod("sendVoice")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendVoice extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("voice")
	private Resource voice;

	@ApiMethodParameter("caption")
	private String caption;

	@ApiMethodParameter("duration")
	private Integer duration;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendVoice(ChatId chatId, Resource voice) {
		super();

		setChatId(chatId);
		setVoice(voice);
	}

	public SendVoice setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendVoice setVoice(Resource voice) {
		// TODO: check max size (50MB)
		// TODO: check file format (must be ogg encoded with OPUS)

		if (voice == null)
			throw new InvalidParameterException("voice cannot be null");

		this.voice = voice;
		return this;
	}

	public SendVoice setCaption(String caption) {
		if (caption != null && caption.length() > 200)
			throw new InvalidParameterException("caption length exceeding maximum value (200)");

		this.caption = caption;
		return this;
	}

	public SendVoice setDuration(Integer duration) {
		this.duration = duration;
		return this;
	}

	public SendVoice setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendVoice setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendVoice setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public Resource getVoice() {
		return this.voice;
	}

	public String getCaption() {
		return this.caption;
	}

	public Integer getDuration() {
		return this.duration;
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
