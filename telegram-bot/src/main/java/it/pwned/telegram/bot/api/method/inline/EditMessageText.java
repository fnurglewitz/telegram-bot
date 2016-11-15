package it.pwned.telegram.bot.api.method.inline;

import java.security.InvalidParameterException;

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
	private ChatId chatId;

	@ApiMethodParameter("message_id")
	private Integer messageId;

	@ApiMethodParameter("inline_message_id")
	private String inlineMessageId;

	@ApiMethodParameter("text")
	private String text;

	@ApiMethodParameter("parse_mode")
	private ParseMode parseMode;

	@ApiMethodParameter("disable_web_page_preview")
	private Boolean disableWebPagePreview;

	@ApiMethodParameter("reply_markup")
	private InlineKeyboardMarkup replyMarkup;

	public EditMessageText(ChatId chatId, Integer messageId, String text) {
		super();

		setChatIdAndMessageId(chatId, messageId);
		setText(text);
	}

	public EditMessageText(String inlineMessageId, String text) {
		super();

		setInlineMessageId(inlineMessageId);
		setText(text);
	}

	public EditMessageText setChatIdAndMessageId(ChatId chatId, Integer messageId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		if (messageId == null)
			throw new InvalidParameterException("messageId cannot be null");

		this.chatId = chatId;
		this.messageId = messageId;
		this.inlineMessageId = null;
		return this;
	}

	public EditMessageText setInlineMessageId(String inlineMessageId) {
		if (inlineMessageId == null)
			throw new InvalidParameterException("inlineMessageId cannot be null");

		this.inlineMessageId = inlineMessageId;
		this.chatId = null;
		this.messageId = null;
		return this;
	}

	public EditMessageText setText(String text) {
		if (text == null)
			throw new InvalidParameterException("text cannot be null");

		this.text = text;
		return this;
	}

	public EditMessageText setParseMode(ParseMode parseMode) {
		this.parseMode = parseMode;
		return this;
	}

	public EditMessageText setDisableWebPagePreview(Boolean disableWebPagePreview) {
		this.disableWebPagePreview = disableWebPagePreview;
		return this;
	}

	public EditMessageText setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public Integer getMessageId() {
		return this.messageId;
	}

	public String getInlineMessageId() {
		return this.inlineMessageId;
	}

	public String getText() {
		return this.text;
	}

	public ParseMode getParseMode() {
		return this.parseMode;
	}

	public Boolean getDisableWebPagePreview() {
		return this.disableWebPagePreview;
	}

	public InlineKeyboardMarkup getReplyMarkup() {
		return this.replyMarkup;
	}
}
