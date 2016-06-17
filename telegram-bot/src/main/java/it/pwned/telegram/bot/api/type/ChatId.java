package it.pwned.telegram.bot.api.type;

/**
 * Represents a Telegram Chat Id
 *
 */
public class ChatId {

	private Long longValue = null;
	private String stringValue = null;

	public ChatId(Long chatId) {
		longValue = chatId;
	}

	public ChatId(String chatId) {
		stringValue = chatId;
	}

	@Override
	public String toString() {
		return longValue == null ? stringValue : Long.toString(longValue);
	}

}
