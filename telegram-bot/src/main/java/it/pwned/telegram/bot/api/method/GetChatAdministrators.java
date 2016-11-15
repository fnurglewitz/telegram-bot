package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.ChatMember;

@ApiMethod("getChatAdministrators")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class GetChatAdministrators extends AbstractApiMethod<List<ChatMember>> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	public GetChatAdministrators(ChatId chatId) {
		super();
		
		setChatId(chatId);
	}

	public GetChatAdministrators setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}
	
	public ChatId getChatId() {
		return this.chatId;
	}

	@Override
	public List<ChatMember> map(List<ChatMember> input) {
		return Collections.unmodifiableList(input);
	}

}
