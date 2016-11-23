package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;

@ApiMethod("forwardMessage")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class ForwardMessage extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("from_chat_id")
	public final ChatId fromChatId;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("message_id")
	public final Integer messageId;

	public ForwardMessage(ChatId chatId, ChatId fromChatId, Integer messageId) {
		this(chatId, fromChatId, messageId, null);
	}

	public ForwardMessage(ChatId chatId, ChatId fromChatId, Integer messageId, Boolean disableNotification) {
		super();

		this.chatId = validateChatId(chatId);
		this.fromChatId = validateFromChatId(fromChatId);
		this.messageId = validateMessageId(messageId);
		this.disableNotification = disableNotification;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static ChatId validateFromChatId(ChatId fromChatId) {
		if (fromChatId == null)
			throw new IllegalArgumentException("fromChatId cannot be null");

		return fromChatId;
	}

	private static Integer validateMessageId(Integer messageId) {
		if (messageId == null)
			throw new IllegalArgumentException("messageId cannot be null");

		return messageId;
	}

	private static Message validateMessage(Message m) {
		if (m == null)
			throw new IllegalArgumentException("message cannot be null");

		return m;
	}

	public static ForwardMessage fromMessage(ChatId chatId, Message m, Boolean disableNotification) {
		validateChatId(chatId);
		validateMessage(m);

		return new ForwardMessage(chatId, new ChatId(m.chat.id), m.messageId, disableNotification);
	}

	public static ForwardMessage fromMessage(ChatId chatId, Message m) {
		return fromMessage(chatId, m, null);
	}
}
