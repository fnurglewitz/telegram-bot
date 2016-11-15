package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodURI;
import it.pwned.telegram.bot.api.method.enums.ApiMethodBaseUri;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("sendChatAction")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
@ApiMethodURI(ApiMethodBaseUri.METHOD)
public final class SendChatAction extends AbstractApiMethod<Boolean> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("action")
	private ChatAction action;

	public SendChatAction(ChatId chatId, ChatAction action) {
		super();

		setChatId(chatId);
		setChatAction(action);
	}

	public SendChatAction setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendChatAction setChatAction(ChatAction action) {
		if (action == null)
			throw new InvalidParameterException("action cannot be null");

		this.action = action;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public ChatAction getChatAction() {
		return this.action;
	}

}
