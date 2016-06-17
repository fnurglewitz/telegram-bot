package it.pwned.telegram.bot.api.type;

import org.springframework.http.MediaType;

/**
 * Represents a Telegram Chat Id
 *
 */
public class ChatId implements MultipartDataEntity {

	private Long longValue = null;
	private String stringValue = null;

	public ChatId(long chatId) {
		longValue = chatId;
	}

	public ChatId(String chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("ChatId cannot be null");

		stringValue = chatId;
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
