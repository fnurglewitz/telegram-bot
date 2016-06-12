package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The member's status in the chat.
 *
 */
public enum ChatMemberStatus {

	CREATOR("creator"), ADMINISTRATOR("administrator"), MEMBER("member"), LEFT("left"), KICKED("kicked"), 
	UNKNOWN("unknown");

	private final String value;

	private ChatMemberStatus(String value) {
		this.value = value;
	}

	public static ChatMemberStatus fromString(String type) {
		switch (type) {
		case "creator":
			return ChatMemberStatus.CREATOR;

		case "administrator":
			return ChatMemberStatus.ADMINISTRATOR;

		case "member":
			return ChatMemberStatus.MEMBER;

		case "left":
			return ChatMemberStatus.LEFT;

		case "kicked":
			return ChatMemberStatus.KICKED;

		default:
			return ChatMemberStatus.UNKNOWN;
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
