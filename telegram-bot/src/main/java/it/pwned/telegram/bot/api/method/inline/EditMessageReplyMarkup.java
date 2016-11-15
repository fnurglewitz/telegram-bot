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

@ApiMethod("editMessageReplyMarkup")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class EditMessageReplyMarkup extends AbstractApiMethod<BooleanOrMessage> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("message_id")
	private Integer messageId;

	@ApiMethodParameter("inline_message_id")
	private String inlineMessageId;

	@ApiMethodParameter("reply_markup")
	private InlineKeyboardMarkup replyMarkup;

	public EditMessageReplyMarkup(ChatId chatId, Integer messageId) {
		super();

		setChatIdAndMessageId(chatId, messageId);
	}

	public EditMessageReplyMarkup(String inlineMessageId) {
		super();

		setInlineMessageId(inlineMessageId);
	}

	public EditMessageReplyMarkup setChatIdAndMessageId(ChatId chatId, Integer messageId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		if (messageId == null)
			throw new InvalidParameterException("messageId cannot be null");

		this.chatId = chatId;
		this.messageId = messageId;
		this.inlineMessageId = null;
		return this;
	}

	public EditMessageReplyMarkup setInlineMessageId(String inlineMessageId) {
		if (inlineMessageId == null)
			throw new InvalidParameterException("inlineMessageId cannot be null");

		this.inlineMessageId = inlineMessageId;
		this.chatId = null;
		this.messageId = null;
		return this;
	}

	public EditMessageReplyMarkup setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
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

	public InlineKeyboardMarkup getReplyMarkup() {
		return this.replyMarkup;
	}
}
