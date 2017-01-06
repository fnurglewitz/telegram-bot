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

@ApiMethod("editMessageReplyMarkup")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class EditMessageReplyMarkup extends AbstractApiMethod<BooleanOrMessage> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("message_id")
	public final Integer messageId;

	@ApiMethodParameter("inline_message_id")
	public final String inlineMessageId;

	@ApiMethodParameter("reply_markup")
	public final InlineKeyboardMarkup replyMarkup;

	public EditMessageReplyMarkup(ChatId chatId, Integer messageId, InlineKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.messageId = validateMessageId(messageId);
		this.replyMarkup = replyMarkup;
		this.inlineMessageId = null;
	}

	public EditMessageReplyMarkup(String inlineMessageId, InlineKeyboardMarkup replyMarkup) {
		super();

		this.inlineMessageId = validateInlineMessageId(inlineMessageId);
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

}
