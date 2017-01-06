package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents the content of a location message to be sent as the result of an
 * inline query.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InputLocationMessageContent extends InputMessageContent {

	private final static String JSON_FIELD_LATITUDE = "latitude";
	private final static String JSON_FIELD_LONGITUDE = "longitude";

	/**
	 * Latitude of the location in degrees
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final Float latitude;

	/**
	 * Longitude of the location in degrees
	 */
	@JsonProperty(JSON_FIELD_LONGITUDE)
	public final Float longitude;

	/**
	 * 
	 * @param latitude
	 *          Latitude of the location in degrees
	 * @param longitude
	 *          Longitude of the location in degrees
	 */
	public InputLocationMessageContent(@JsonProperty(JSON_FIELD_LATITUDE) Float latitude,
			@JsonProperty(JSON_FIELD_LONGITUDE) Float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;

	}

}
