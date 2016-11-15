package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

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
	private ChatId chatId;

	@ApiMethodParameter("from_chat_id")
	private ChatId fromChatId;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("message_id")
	private Integer messageId;

	public ForwardMessage(ChatId chatId, ChatId fromChatId, Integer messageId) {
		super();

		setChatId(chatId);
		setFromChatId(fromChatId);
		setMessageId(messageId);
	}

	public ForwardMessage setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public ForwardMessage setFromChatId(ChatId fromChatId) {
		if (fromChatId == null)
			throw new InvalidParameterException("fromChatId cannot be null");

		this.fromChatId = fromChatId;
		return this;
	}

	public ForwardMessage setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public ForwardMessage setMessageId(Integer messageId) {
		if (messageId == null)
			throw new InvalidParameterException("messageId cannot be null");

		this.messageId = messageId;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public ChatId getFromChatId() {
		return this.fromChatId;
	}

	public Boolean getDisableNotification() {
		return this.disableNotification;
	}

	public Integer getMessageId() {
		return this.messageId;
	}

}
