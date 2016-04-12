package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyboardButton {
	public final String text;
	public final Boolean request_contact;
	public final Boolean request_location;

	public KeyboardButton(@JsonProperty("text") String text, @JsonProperty("request_contact") Boolean request_contact,
			@JsonProperty("request_location") Boolean request_location) {
		this.text = text;
		this.request_contact = request_contact;
		this.request_location = request_location;
	}
}
