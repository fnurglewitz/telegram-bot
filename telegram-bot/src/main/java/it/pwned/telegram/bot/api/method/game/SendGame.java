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
	public final ChatId chatId;

	@ApiMethodParameter("game_short_name")
	public final String gameShortName;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final InlineKeyboardMarkup replyMarkup;

	public SendGame(ChatId chatId, String gameShortName) {
		this(chatId, gameShortName, null, null, null);
	}

	public SendGame(ChatId chatId, String gameShortName, Boolean disableNotification, Integer replyToMessageId,
			InlineKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.gameShortName = validateGameShortName(gameShortName);
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static String validateGameShortName(String gameShortName) {
		if (gameShortName == null)
			throw new InvalidParameterException("gameShortName cannot be null");

		return gameShortName;
	}

}
