package it.pwned.telegram.bot.api.method.game;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("setGameScore")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SetGameScore extends AbstractApiMethod<BooleanOrMessage> {

	@ApiMethodParameter("user_id")
	public final Integer userId;

	@ApiMethodParameter("score")
	public final Integer score;

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("message_id")
	public final Integer messageId;

	@ApiMethodParameter("inline_message_id")
	public final String inlineMessageId;

	@ApiMethodParameter("edit_message")
	public final Boolean editMessage;

	public SetGameScore(Integer userId, Integer score, ChatId chatId, Integer messageId, Boolean editMessage) {
		super();

		this.userId = validateUserId(userId);
		this.chatId = validateChatId(chatId);
		this.messageId = validateMessageId(messageId);
		this.score = validateScore(score);
		this.editMessage = editMessage;
		this.inlineMessageId = null;
	}

	public SetGameScore(Integer userId, Integer score, String inlineMessageId, Boolean editMessage) {
		super();

		this.userId = validateUserId(userId);
		this.inlineMessageId = validateInlineMessageId(inlineMessageId);
		this.score = validateScore(score);
		this.editMessage = editMessage;
		this.chatId = null;
		this.messageId = null;
	}

	private static Integer validateScore(Integer score) {
		if (score == null)
			throw new IllegalArgumentException("score cannot be null");

		if (score < 0)
			throw new IllegalArgumentException("score cannot be negative");

		return score;
	}

	private static Integer validateUserId(Integer userId) {
		if (userId == null)
			throw new IllegalArgumentException("userId cannot be null");

		return userId;
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
