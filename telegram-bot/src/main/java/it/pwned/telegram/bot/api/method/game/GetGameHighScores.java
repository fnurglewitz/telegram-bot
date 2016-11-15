package it.pwned.telegram.bot.api.method.game;

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
import it.pwned.telegram.bot.api.type.game.GameHighScore;

@ApiMethod("getGameHighScores")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class GetGameHighScores extends AbstractApiMethod<List<GameHighScore>> {

	@ApiMethodParameter("user_id")
	private Integer userId;

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("message_id")
	private Integer messageId;

	@ApiMethodParameter("inline_message_id")
	private String inlineMessageId;

	public GetGameHighScores(Integer userId, ChatId chatId, Integer messageId) {
		super();

		setUserId(userId);
		setChatIdAndMessageId(chatId, messageId);
	}

	public GetGameHighScores(Integer userId, String inlineMessageId) {
		super();

		setUserId(userId);
		setInlineMessageId(inlineMessageId);
	}

	public GetGameHighScores setUserId(Integer userId) {
		if (userId == null)
			throw new InvalidParameterException("userId cannot be null");

		this.userId = userId;
		return this;
	}

	public GetGameHighScores setChatIdAndMessageId(ChatId chatId, Integer messageId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		if (messageId == null)
			throw new InvalidParameterException("messageId cannot be null");

		this.chatId = chatId;
		this.messageId = messageId;
		this.inlineMessageId = null;
		return this;
	}

	public GetGameHighScores setInlineMessageId(String inlineMessageId) {
		if (inlineMessageId == null)
			throw new InvalidParameterException("inlineMessageId cannot be null");

		this.inlineMessageId = inlineMessageId;
		this.chatId = null;
		this.messageId = null;
		return this;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public Integer getMessageId() {
		return this.messageId;
	}

	public String getInlineMessageId() {
		return this.inlineMessageId;
	}

	@Override
	public List<GameHighScore> map(List<GameHighScore> input) {
		return Collections.unmodifiableList(input);
	}

}
