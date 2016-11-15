package it.pwned.telegram.bot.api.method.game;

import java.security.InvalidParameterException;

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
	private Integer userId;

	@ApiMethodParameter("score")
	private Integer score;

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("message_id")
	private Integer messageId;

	@ApiMethodParameter("inline_message_id")
	private String inlineMessageId;

	@ApiMethodParameter("edit_message")
	private Boolean editMessage;

	public SetGameScore(Integer userId, Integer score, ChatId chatId, Integer messageId) {
		super();

		setChatIdAndMessageId(chatId, messageId);
		setUserId(userId);
		setScore(score);
	}

	public SetGameScore(Integer userId, Integer score, String inlineMessageId) {
		super();

		setInlineMessageId(inlineMessageId);
		setUserId(userId);
		setScore(score);
	}

	public SetGameScore setUserId(Integer userId) {
		if (userId == null)
			throw new InvalidParameterException("userId cannot be null");

		this.userId = userId;
		return this;
	}

	public SetGameScore setScore(Integer score) {
		if (score == null)
			throw new InvalidParameterException("score cannot be null");

		if (score < 0)
			throw new InvalidParameterException("score cannot be negative");

		this.score = score;
		return this;
	}

	public SetGameScore setChatIdAndMessageId(ChatId chatId, Integer messageId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		if (messageId == null)
			throw new InvalidParameterException("messageId cannot be null");

		this.chatId = chatId;
		this.messageId = messageId;
		this.inlineMessageId = null;
		return this;
	}

	public SetGameScore setInlineMessageId(String inlineMessageId) {
		if (inlineMessageId == null)
			throw new InvalidParameterException("inlineMessageId cannot be null");

		this.inlineMessageId = inlineMessageId;
		this.chatId = null;
		this.messageId = null;
		return this;
	}

	public SetGameScore setEditMessage(Boolean editMessage) {
		this.editMessage = editMessage;
		return this;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public Integer getScore() {
		return this.score;
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

	public Boolean getEditMessage() {
		return this.editMessage;
	}
}
