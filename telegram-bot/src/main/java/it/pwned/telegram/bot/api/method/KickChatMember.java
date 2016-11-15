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

@ApiMethod("kickChatMember")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class KickChatMember extends AbstractApiMethod<Boolean> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("user_id")
	private Integer userId;

	public KickChatMember(ChatId chatId, Integer userId) {
		super();

		setChatId(chatId);
		setUserId(userId);
	}

	public KickChatMember setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public KickChatMember setUserId(Integer userId) {
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
