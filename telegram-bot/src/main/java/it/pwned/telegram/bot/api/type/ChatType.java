package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of chat, can be either "private", "group", "supergroup" or "channel"
 * "unknown" added as a fallback value if more types are added in the future but this implementation is not up-to-date
 */
public enum ChatType {

	PRIVATE("private"), GROUP("group"), SUPERGROUP("supergroup"), CHANNEL("channel"), UNKNOWN("unknown");

	private final String value;

	private ChatType(String value) {
		this.value = value;
	}
	
	public static ChatType fromString(String type) {
		switch (type) {
		case "private":
			return ChatType.PRIVATE;

		case "group":
			return ChatType.GROUP;
			
		case "supergroup":
			return ChatType.SUPERGROUP;
			
		case "channel":
			return ChatType.CHANNEL;
			
		default:
			return ChatType.UNKNOWN;
		}
	}

	@Override
	public String toString() {
		return value;
	}
	
	@JsonValue
	public String getType() {
		return value;
	}

}
