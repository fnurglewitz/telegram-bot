package it.pwned.telegram.bot.api.method;

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
	public final ChatId chatId;

	@ApiMethodParameter("user_id")
	public final Integer userId;

	public GetChatMember(ChatId chatId, Integer userId) {
		super();

		this.chatId = validateChatId(chatId);
		this.userId = validateUserId(userId);
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static Integer validateUserId(Integer userId) {
		if (userId == null)
			throw new IllegalArgumentException("userId cannot be null");

		return userId;
	}

}
