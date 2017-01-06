package it.pwned.telegram.bot.api.method.game;

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
import it.pwned.telegram.bot.api.type.game.GameHighScore;

@ApiMethod("getGameHighScores")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class GetGameHighScores extends AbstractApiMethod<List<GameHighScore>> {

	@ApiMethodParameter("user_id")
	public final Integer userId;

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("message_id")
	public final Integer messageId;

	@ApiMethodParameter("inline_message_id")
	public final String inlineMessageId;

	public GetGameHighScores(Integer userId, ChatId chatId, Integer messageId) {
		super();

		this.userId = validateUserId(userId);
		this.chatId = validateChatId(chatId);
		this.messageId = validateMessageId(messageId);
		this.inlineMessageId = null;
	}

	public GetGameHighScores(Integer userId, String inlineMessageId) {
		super();

		this.userId = validateUserId(userId);
		this.inlineMessageId = validateInlineMessageId(inlineMessageId);
		this.chatId = null;
		this.messageId = null;
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

	@Override
	public List<GameHighScore> map(List<GameHighScore> input) {
		return Collections.unmodifiableList(input);
	}

}
