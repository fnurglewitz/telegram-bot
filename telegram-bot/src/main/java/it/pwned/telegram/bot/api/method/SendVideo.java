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

@ApiMethod("sendVideo")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendVideo extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("video")
	private Resource video;

	@ApiMethodParameter("duration")
	private Integer duration;

	@ApiMethodParameter("width")
	private Integer width;

	@ApiMethodParameter("height")
	private Integer height;

	@ApiMethodParameter("caption")
	private String caption;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendVideo(ChatId chatId, Resource video) {
		super();

		setChatId(chatId);
		setVideo(video);
	}

	public SendVideo setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendVideo setVideo(Resource video) {
		// TODO: check max size (50MB)
		// TODO: check file format (must be mp4)

		if (video == null)
			throw new InvalidParameterException("video cannot be null");

		this.video = video;
		return this;
	}

	public SendVideo setDuration(Integer duration) {
		this.duration = duration;
		return this;
	}

	public SendVideo setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public SendVideo setHeight(Integer height) {
		this.height = height;
		return this;
	}

	public SendVideo setCaption(String caption) {
		if (caption != null && caption.length() > 200)
			throw new InvalidParameterException("caption length exceeding maximum value (200)");

		this.caption = caption;
		return this;
	}

	public SendVideo setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendVideo setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendVideo setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public Resource getVideo() {
		return this.video;
	}

	public Integer getWidth() {
		return this.width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public String getCaption() {
		return this.caption;
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
