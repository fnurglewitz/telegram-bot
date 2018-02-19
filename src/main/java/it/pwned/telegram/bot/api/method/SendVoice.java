package it.pwned.telegram.bot.api.method;

import it.pwned.telegram.bot.api.type.ParseMode;
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
	public final ChatId chatId;

	@ApiMethodParameter("voice")
	public final Resource voice;

	@ApiMethodParameter("caption")
	public final String caption;

	@ApiMethodParameter("parse_mode")
	public final ParseMode parseMode;

	@ApiMethodParameter("duration")
	public final Integer duration;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendVoice(ChatId chatId, Resource voice) {
		this(chatId, voice, null, null, null, null, null, null);
	}

	public SendVoice(ChatId chatId, Resource voice, String caption) {
		this(chatId, voice, caption, null, null, null, null, null);
	}

	public SendVoice(ChatId chatId, Resource voice, String caption, ParseMode parseMode, Integer duration, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.voice = validateVoice(voice);
		this.caption = validateCaption(caption);
		this.parseMode = parseMode;
		this.duration = duration;
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static Resource validateVoice(Resource voice) {
		// TODO: check max size (50MB)
		// TODO: check file format (must be ogg encoded with OPUS)

		if (voice == null)
			throw new IllegalArgumentException("voice cannot be null");

		return voice;
	}

	private static String validateCaption(String caption) {
		if (caption != null && caption.length() > 200)
			throw new IllegalArgumentException("caption length exceeding maximum value (200)");

		return caption;
	}

	public static class Builder {

		private ChatId chatId;

		private Resource voice;

		private String caption;

		private ParseMode parseMode;

		private Integer duration;

		private Boolean disableNotification;

		private Integer replyToMessageId;

		private AbstractKeyboardMarkup replyMarkup;

		public Builder(ChatId chatId, Resource voice) {
			this.chatId = validateChatId(chatId);
			this.voice = validateVoice(voice);
		}

		public SendVoice build() {
			return new SendVoice(chatId, voice, caption, parseMode, duration, disableNotification, replyToMessageId, replyMarkup);
		}

		public Builder caption(String caption) {
			this.caption = validateCaption(caption);
			return this;
		}

		public Builder parseMode(ParseMode parseMode) {
			this.parseMode = parseMode;
			return this;
		}

		public Builder duration(Integer duration) {
			this.duration = duration;
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
