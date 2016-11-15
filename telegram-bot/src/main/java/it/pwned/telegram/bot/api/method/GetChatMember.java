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
import it.pwned.telegram.bot.api.type.ChatMember;

@ApiMethod("getChatMember")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class GetChatMember extends AbstractApiMethod<ChatMember> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("user_id")
	private Integer userId;

	public GetChatMember(ChatId chatId, Integer userId) {
		super();

		setChatId(chatId);
		setUserId(userId);
	}

	public GetChatMember setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public GetChatMember setUserId(Integer userId) {
		if (userId == null)
			throw new InvalidParameterException("userId cannot be null");

		this.userId = userId;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public Integer getUserId() {
		return this.userId;
	}

}
