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

@ApiMethod("sendAudio")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendAudio extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("audio")
	private Resource audio;

	@ApiMethodParameter("caption")
	private String caption;

	@ApiMethodParameter("duration")
	private Integer duration;

	@ApiMethodParameter("performer")
	private String performer;

	@ApiMethodParameter("title")
	private String title;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendAudio(ChatId chatId, Resource audio) {
		super();

		setChatId(chatId);
		setAudio(audio);
	}

	public SendAudio setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendAudio setAudio(Resource audio) {
		// TODO: check max size (50MB)
		// TODO: check file format (must be mp3)

		if (audio == null)
			throw new InvalidParameterException("audio cannot be null");

		this.audio = audio;
		return this;
	}

	public SendAudio setCaption(String caption) {
		if (caption != null && caption.length() > 200)
			throw new InvalidParameterException("caption length exceeding maximum value (200)");

		this.caption = caption;
		return this;
	}

	public SendAudio setDuration(Integer duration) {
		this.duration = duration;
		return this;
	}

	public SendAudio setPerformer(String performer) {
		this.performer = performer;
		return this;
	}

	public SendAudio setTitle(String title) {
		this.title = title;
		return this;
	}

	public SendAudio setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendAudio setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendAudio setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public Resource getAudio() {
		return this.audio;
	}

	public String getCaption() {
		return this.caption;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public String getPerformer() {
		return this.performer;
	}

	public String getTitle() {
		return this.title;
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
