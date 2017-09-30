package it.pwned.telegram.bot.api.method;

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
	public final ChatId chatId;

	@ApiMethodParameter("user_id")
	public final Integer userId;

	@ApiMethodParameter("until_date")
	public final Integer untilDate;

	public KickChatMember(ChatId chatId, Integer userId, Integer untilDate) {
		super();

		this.chatId = validateChatId(chatId);
		this.userId = validateUserId(userId);
		this.untilDate = untilDate;
	}

	public KickChatMember(ChatId chatId, Integer userId) {
		this(chatId, userId, null);
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
