package it.pwned.telegram.bot.api.type;

import org.springframework.http.MediaType;

/**
 * Represents a Telegram Chat Id
 *
 */
public class ChatId implements MultipartDataEntity {

	private final Long longValue;
	private final String stringValue;

	public ChatId(long chatId) {
		longValue = chatId;
		stringValue = null;
	}

	public ChatId(String chatId) {
		if (chatId == null || "".equals(chatId))
			throw new IllegalArgumentException("ChatId cannot be null or empty");

		stringValue = chatId;
		longValue = null;
	}

	@Override
	public String toString() {
		return longValue == null ? stringValue : Long.toString(longValue);
	}

	@Override
	public String stringValue() {
		return toString();
	}

	@Override
	public MediaType getContentType() {
		return MediaType.TEXT_PLAIN;
	}

}
