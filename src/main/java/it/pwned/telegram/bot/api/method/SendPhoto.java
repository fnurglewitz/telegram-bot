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

@ApiMethod("sendPhoto")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendPhoto extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("photo")
	public final Resource photo;

	@ApiMethodParameter("caption")
	public final String caption;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendPhoto(ChatId chatId, Resource photo) {
		this(chatId, photo, null, null, null, null);
	}

	public SendPhoto(ChatId chatId, Resource photo, String caption) {
		this(chatId, photo, caption, null, null, null);
	}

	public SendPhoto(ChatId chatId, Resource photo, String caption, Boolean disableNotification, Integer replyToMessageId,
			AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.photo = validatePhoto(photo);
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

	private static Resource validatePhoto(Resource photo) {
		if (photo == null)
			throw new IllegalArgumentException("photo cannot be null");

		return photo;
	}

	private static String validateCaption(String caption) {
		if (caption != null && caption.length() > 200)
			throw new IllegalArgumentException("caption length exceeding maximum value (200)");

		return caption;
	}

	public static class Builder {

		private ChatId chatId;

		private Resource photo;

		private String caption;

		private Boolean disableNotification;

		private Integer replyToMessageId;

		private AbstractKeyboardMarkup replyMarkup;

		public Builder(ChatId chatId, Resource photo) {
			this.chatId = validateChatId(chatId);
			this.photo = validatePhoto(photo);
		}

		public SendPhoto build() {
			return new SendPhoto(chatId, photo, caption, disableNotification, replyToMessageId, replyMarkup);
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

		public Builder replyMarkup(AbstractKeyboardMarkup replyMarkup) {
			this.replyMarkup = replyMarkup;
			return this;
		}

	}

}
