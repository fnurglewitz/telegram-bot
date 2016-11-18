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

@ApiMethod("sendAudio")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendAudio extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("audio")
	public final Resource audio;

	@ApiMethodParameter("caption")
	public final String caption;

	@ApiMethodParameter("duration")
	public final Integer duration;

	@ApiMethodParameter("performer")
	public final String performer;

	@ApiMethodParameter("title")
	public final String title;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendAudio(ChatId chatId, Resource audio) {
		this(chatId, audio, null, null, null, null, null, null, null);
	}

	public SendAudio(ChatId chatId, Resource audio, String caption, Integer duration, String performer, String title,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.audio = validateAudio(audio);
		this.caption = validateCaption(caption);
		this.duration = duration;
		this.performer = performer;
		this.title = title;
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static Resource validateAudio(Resource audio) {
		// TODO: check max size (50MB)
		// TODO: check file format (must be mp3)

		if (audio == null)
			throw new IllegalArgumentException("audio cannot be null");

		return audio;
	}

	private static String validateCaption(String caption) {
		if (caption != null && caption.length() > 200)
			throw new IllegalArgumentException("caption length exceeding maximum value (200)");

		return caption;
	}

	public static class Builder {

		private ChatId chatId;

		private Resource audio;

		private String caption;

		private Integer duration;

		private String performer;

		private String title;

		private Boolean disableNotification;

		private Integer replyToMessageId;

		private AbstractKeyboardMarkup replyMarkup;

		public Builder(ChatId chatId, Resource audio) {
			this.chatId = validateChatId(chatId);
			this.audio = validateAudio(audio);
		}

		public SendAudio build() {
			return new SendAudio(chatId, audio, caption, duration, performer, title, disableNotification,
					replyToMessageId, replyMarkup);
		}

		public Builder caption(String caption) {
			this.caption = validateCaption(caption);
			return this;
		}

		public Builder duration(Integer duration) {
			this.duration = duration;
			return this;
		}

		public Builder performer(String performer) {
			this.performer = performer;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
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

		public Builder replyMarkup(AbstractKeyboardMarkup replyMarkup) {
			this.replyMarkup = replyMarkup;
			return this;
		}

	}
}
