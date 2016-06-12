package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a venue.
 * 
 */
public class Venue {

	private final static String JSON_FIELD_LOCATION = "location";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_ADDRESS = "address";
	private final static String JSON_FIELD_FOURSQUARE_ID = "foursquare_id";

	/**
	 * Venue location
	 */
	@JsonProperty(JSON_FIELD_LOCATION)
	public final Location location;

	/**
	 * Name of the venue
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * Address of the venue
	 */
	@JsonProperty(JSON_FIELD_ADDRESS)
	public final String address;

	/**
	 * <em>Optional.</em> Foursquare identifier of the venue
	 */
	@JsonProperty(JSON_FIELD_FOURSQUARE_ID)
	public final String foursquareId;

	/**
	 * 
	 * @param location
	 *          Venue location
	 * @param title
	 *          Name of the venue
	 * @param address
	 *          Address of the venue
	 * @param foursquareId
	 *          <em>Optional.</em> Foursquare identifier of the venue
	 */
	public Venue(@JsonProperty(JSON_FIELD_LOCATION) Location location, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_ADDRESS) String address, @JsonProperty(JSON_FIELD_FOURSQUARE_ID) String foursquareId) {
		this.location = location;
		this.title = title;
		this.address = address;
		this.foursquareId = foursquareId;
	}

}
