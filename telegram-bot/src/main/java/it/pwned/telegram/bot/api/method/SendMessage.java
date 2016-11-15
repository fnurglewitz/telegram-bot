package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

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
	private ChatId chatId;

	@ApiMethodParameter("text")
	private String text;

	@ApiMethodParameter("parse_mode")
	private ParseMode parseMode;

	@ApiMethodParameter("disable_web_page_preview")
	private Boolean disableWebPagePreview;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendMessage(ChatId chatId, String text) {
		super();

		setChatId(chatId);
		setText(text);
	}

	public SendMessage setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendMessage setText(String text) {
		if (text == null)
			throw new InvalidParameterException("text cannot be null");

		this.text = text;
		return this;
	}

	public SendMessage setParseMode(ParseMode parseMode) {
		this.parseMode = parseMode;
		return this;
	}

	public SendMessage setDisableWebPagePreview(Boolean disableWebPagePreview) {
		this.disableWebPagePreview = disableWebPagePreview;
		return this;
	}

	public SendMessage setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendMessage setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendMessage setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
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
