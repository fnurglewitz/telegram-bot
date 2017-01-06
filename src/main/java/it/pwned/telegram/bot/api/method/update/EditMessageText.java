package it.pwned.telegram.bot.api.method.update;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ParseMode;

@ApiMethod("editMessageText")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class EditMessageText extends AbstractApiMethod<BooleanOrMessage> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("message_id")
	public final Integer messageId;

	@ApiMethodParameter("inline_message_id")
	public final String inlineMessageId;

	@ApiMethodParameter("text")
	public final String text;

	@ApiMethodParameter("parse_mode")
	public final ParseMode parseMode;

	@ApiMethodParameter("disable_web_page_preview")
	public final Boolean disableWebPagePreview;

	@ApiMethodParameter("reply_markup")
	public final InlineKeyboardMarkup replyMarkup;

	public EditMessageText(ChatId chatId, Integer messageId, String text, ParseMode parseMode,
			Boolean disableWebPagePreview, InlineKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.messageId = validateMessageId(messageId);
		this.text = validateText(text);
		this.parseMode = parseMode;
		this.disableWebPagePreview = disableWebPagePreview;
		this.replyMarkup = replyMarkup;
		this.inlineMessageId = null;
	}

	public EditMessageText(String inlineMessageId, String text, ParseMode parseMode, Boolean disableWebPagePreview,
			InlineKeyboardMarkup replyMarkup) {
		super();

		this.inlineMessageId = validateInlineMessageId(inlineMessageId);
		this.text = validateText(text);
		this.parseMode = parseMode;
		this.disableWebPagePreview = disableWebPagePreview;
		this.replyMarkup = replyMarkup;
		this.chatId = null;
		this.messageId = null;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static Integer validateMessageId(Integer messageId) {
		if (messageId == null)
			throw new IllegalArgumentException("messageId cannot be null");

		return messageId;
	}

	private static String validateInlineMessageId(String inlineMessageId) {
		if (inlineMessageId == null)
			throw new IllegalArgumentException("inlineMessageId cannot be null");

		return inlineMessageId;
	}

	private static String validateText(String text) {
		if (text == null || "".equals(text))
			throw new IllegalArgumentException("text cannot be null or empty");

		return text;
	}

}
