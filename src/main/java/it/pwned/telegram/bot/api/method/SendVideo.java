package it.pwned.telegram.bot.api.method;

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
	public final ChatId chatId;

	@ApiMethodParameter("video")
	public final Resource video;

	@ApiMethodParameter("duration")
	public final Integer duration;

	@ApiMethodParameter("width")
	public final Integer width;

	@ApiMethodParameter("height")
	public final Integer height;

	@ApiMethodParameter("caption")
	public final String caption;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendVideo(ChatId chatId, Resource video) {
		this(chatId, video, null, null, null, null, null, null, null);
	}

	public SendVideo(ChatId chatId, Resource video, String caption) {
		this(chatId, video, null, null, null, caption, null, null, null);
	}

	public SendVideo(ChatId chatId, Resource video, Integer duration, Integer width, Integer height, String caption,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.video = validateVideo(video);
		this.duration = duration;
		this.width = width;
		this.height = height;
		this.caption = validateCaption(caption);
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static Resource validateVideo(Resource video) {
		// TODO: check max size (50MB)
		// TODO: check file format (must be mp4)

		if (video == null)
			throw new IllegalArgumentException("video cannot be null");

		return video;
	}

	private static String validateCaption(String caption) {
		if (caption != null && caption.length() > 200)
			throw new IllegalArgumentException("caption length exceeding maximum value (200)");

		return caption;
	}

	public static class Builder {

		private ChatId chatId;

		private Resource video;

		private Integer duration;

		private Integer width;

		private Integer height;

		private String caption;

		private Boolean disableNotification;

		private Integer replyToMessageId;

		private AbstractKeyboardMarkup replyMarkup;

		public Builder(ChatId chatId, Resource video) {
			this.chatId = validateChatId(chatId);
			this.video = validateVideo(video);
		}

		public SendVideo build() {
			return new SendVideo(chatId, video, duration, width, height, caption, disableNotification, replyToMessageId,
					replyMarkup);
		}

		public Builder duration(Integer duration) {
			this.duration = duration;
			return this;
		}

		public Builder width(Integer width) {
			this.width = width;
			return this;
		}

		public Builder height(Integer height) {
			this.height = height;
			return this;
		}

		public Builder caption(String caption) {
			this.caption = validateCaption(caption);
			return this;
		}

		public Builder disableNotification(Boolean disableNotification) {
			this.disableNotification = disableNotification;
			return this;
		}

		public Builder replyToMessageId(Integer replyToMessageId) {
			this.replyToMessageId = replyToMessageId;
			return this;
		}

		public Builder setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
			this.replyMarkup = replyMarkup;
			return this;
		}
	}
}
