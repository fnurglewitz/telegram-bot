package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a point on the map.
 *
 */
public final class Location {

	private final static String JSON_FIELD_LONGITUDE = "longitude";
	private final static String JSON_FIELD_LATITUDE = "latitude";

	/**
	 * Longitude as defined by sender
	 */
	@JsonProperty(JSON_FIELD_LONGITUDE)
	public final Float longitude;

	/**
	 * Latitude as defined by sender
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final Float latitude;

	/**
	 * 
	 * @param longitude
	 *            Longitude as defined by sender
	 * @param latitude
	 *            Latitude as defined by sender
	 */
	public Location(@JsonProperty(JSON_FIELD_LONGITUDE) Float longitude,
			@JsonProperty(JSON_FIELD_LATITUDE) Float latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

}