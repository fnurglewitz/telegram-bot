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
import it.pwned.telegram.bot.api.type.ParseMode;

@ApiMethod("sendMessage")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendMessage extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("text")
	public final String text;

	@ApiMethodParameter("parse_mode")
	public final ParseMode parseMode;

	@ApiMethodParameter("disable_web_page_preview")
	public final Boolean disableWebPagePreview;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendMessage(ChatId chatId, String text, ParseMode parseMode, Boolean disableWebPagePreview,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.text = validateText(text);
		this.parseMode = parseMode;
		this.disableWebPagePreview = disableWebPagePreview;
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static String validateText(String text) {
		if (text == null || "".equals(text))
			throw new IllegalArgumentException("text cannot be null or empty");

		return text;
	}

	public static class Builder {

		private ChatId chatId;

		private String text;

		private ParseMode parseMode;

		private Boolean disableWebPagePreview;

		private Boolean disableNotification;

		private Integer replyToMessageId;

		private AbstractKeyboardMarkup replyMarkup;

		public Builder(ChatId chatId, String text) {
			this.chatId = validateChatId(chatId);
			this.text = validateText(text);
		}

		public SendMessage build() {
			return new SendMessage(chatId, text, parseMode, disableWebPagePreview, disableNotification, replyToMessageId,
					replyMarkup);
		}

		public Builder parseMode(ParseMode parseMode) {
			this.parseMode = parseMode;
			return this;
		}

		public Builder disableWebPagePreview(Boolean disableWebPagePreview) {
			this.disableWebPagePreview = disableWebPagePreview;
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
