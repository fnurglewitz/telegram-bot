package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InputVenueMessageContent extends InputMessageContent {

	public final Float latitude;
	public final Float longitude;
	public final String title;
	public final String address;
	public final String foursquare_id;

	public InputVenueMessageContent(@JsonProperty("latitude") Float latitude, @JsonProperty("longitude") Float longitude,
			@JsonProperty("title") String title, @JsonProperty("address") String address,
			@JsonProperty("foursquare_id") String foursquare_id) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.address = address;
		this.foursquare_id = foursquare_id;
	}

}
