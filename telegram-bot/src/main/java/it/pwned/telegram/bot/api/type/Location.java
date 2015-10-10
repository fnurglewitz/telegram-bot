package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Location {
	public final Float longitude;
	public final Float latitude;

	public Location(@JsonProperty("longitude") Float longitude, @JsonProperty("latitude") Float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
}