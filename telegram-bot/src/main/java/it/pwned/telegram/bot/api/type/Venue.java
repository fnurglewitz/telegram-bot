package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Venue {

	public final Location location;
	public final String title;
	public final String address;
	public final String foursquare_id;

	public Venue(@JsonProperty("location") Location location, @JsonProperty("title") String title,
			@JsonProperty("address") String address, @JsonProperty("foursquare_id") String foursquare_id) {
		this.location = location;
		this.title = title;
		this.address = address;
		this.foursquare_id = foursquare_id;
	}

}
