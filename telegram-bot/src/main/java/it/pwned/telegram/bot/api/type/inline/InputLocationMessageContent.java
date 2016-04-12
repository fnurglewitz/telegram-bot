package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputLocationMessageContent extends InputMessageContent {

	public final Float latitude;
	public final Float longitude;

	public InputLocationMessageContent(@JsonProperty("latitude") Float latitude,
			@JsonProperty("longitude") Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
