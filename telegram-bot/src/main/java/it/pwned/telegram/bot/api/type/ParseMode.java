package it.pwned.telegram.bot.api.type;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ParseMode implements MultipartDataEntity {
	MARKDOWN("Markdown"), HTML("HTML"), PLAIN("");

	private final String value;

	private ParseMode(String value) {
		this.value = value;
	}

	@JsonCreator
	public static ParseMode fromString(String mode) {
		switch (mode) {
		case "Markdown":
			return ParseMode.MARKDOWN;
		case "HTML":
			return ParseMode.HTML;
		default:
			return ParseMode.PLAIN;
		}
	}

	@JsonValue
	@Override
	public String toString() {
		return value;
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
