package it.pwned.telegram.bot.api.method;

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
	public final ChatId chatId;

	@ApiMethodParameter("action")
	public final ChatAction action;

	public SendChatAction(ChatId chatId, ChatAction action) {
		super();

		this.chatId = validateChatId(chatId);
		this.action = validateChatAction(action);
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	public ChatAction validateChatAction(ChatAction action) {
		if (action == null)
			throw new IllegalArgumentException("action cannot be null");

		return action;
	}

}
