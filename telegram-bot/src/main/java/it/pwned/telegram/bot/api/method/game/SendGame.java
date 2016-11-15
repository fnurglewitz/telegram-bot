package it.pwned.telegram.bot.api.method.game;

import java.security.InvalidParameterException;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.Message;

@ApiMethod("sendGame")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendGame extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("game_short_name")
	private String gameShortName;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private InlineKeyboardMarkup replyMarkup;

	public SendGame(ChatId chatId, String gameShortName) {
		super();

		setChatId(chatId);
		setGameShortName(gameShortName);
	}

	public SendGame setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendGame setGameShortName(String gameShortName) {
		if (gameShortName == null)
			throw new InvalidParameterException("gameShortName cannot be null");

		this.gameShortName = gameShortName;
		return this;
	}

	public SendGame setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendGame setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendGame setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public String getGameShortName() {
		return this.gameShortName;
	}

	public Boolean getDisableNotification() {
		return this.disableNotification;
	}

	public Integer getReplyToMessageId() {
		return this.replyToMessageId;
	}

	public InlineKeyboardMarkup getReplyMarkup() {
		return this.replyMarkup;
	}

}
