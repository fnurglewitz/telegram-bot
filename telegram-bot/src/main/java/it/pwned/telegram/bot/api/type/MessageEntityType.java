package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageEntityType {

	MENTION("mention"), HASHTAG("hashtag"), BOT_COMMAND("bot_command"), URL("url"),
	EMAIL("email"), BOLD("bold"), ITALIC("italic"), CODE("code"), PRE("pre"),
	TEXT_LINK("text_link"), TEXT_MENTION("text_mention"), UNKNOWN("unknown");
	
	private final String value;

	private MessageEntityType(String value) {
		this.value = value;
	}

	@JsonCreator
	public static MessageEntityType fromString(String type) {
		switch (type) {
		case "mention":
			return MessageEntityType.MENTION;
		case "hashtag":
			return MessageEntityType.HASHTAG;
		case "bot_command":
			return MessageEntityType.BOT_COMMAND;
		case "url":
			return MessageEntityType.URL;
		case "email":
			return MessageEntityType.EMAIL;
		case "bold":
			return MessageEntityType.BOLD;
		case "italic":
			return MessageEntityType.ITALIC;
		case "code":
			return MessageEntityType.CODE;
		case "pre":
			return MessageEntityType.PRE;
		case "text_link":
			return MessageEntityType.TEXT_LINK;
		case "text_mention":
			return MessageEntityType.TEXT_MENTION;
		default:
			return MessageEntityType.UNKNOWN;
		}
	}

	@JsonValue
	@Override
	public String toString() {
		return value;
	}

}
