package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents the content of a venue message to be sent as the result of an
 * inline query.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InputVenueMessageContent extends InputMessageContent {

	private final static String JSON_FIELD_LATITUDE = "latitude";
	private final static String JSON_FIELD_LONGITUDE = "longitude";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_ADDRESS = "address";
	private final static String JSON_FIELD_FOURSQUARE_ID = "foursquare_id";

	/**
	 * Latitude of the venue in degrees
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final Float latitude;

	/**
	 * Longitude of the venue in degrees
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final Float longitude;

	/**
	 * Name of the venue
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final String title;

	/**
	 * Address of the venue
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final String address;

	/**
	 * <em>Optional.</em> Foursquare identifier of the venue, if known
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final String foursquareId;

	/**
	 * 
	 * @param latitude
	 *          Latitude of the venue in degrees
	 * @param longitude
	 *          Longitude of the venue in degrees
	 * @param title
	 *          Name of the venue
	 * @param address
	 *          Address of the venue
	 * @param foursquareId
	 *          <em>Optional.</em> Foursquare identifier of the venue, if known
	 */
	public InputVenueMessageContent(@JsonProperty(JSON_FIELD_LATITUDE) Float latitude,
			@JsonProperty(JSON_FIELD_LONGITUDE) Float longitude, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_ADDRESS) String address, @JsonProperty(JSON_FIELD_FOURSQUARE_ID) String foursquareId) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.address = address;
		this.foursquareId = foursquareId;

	}

}
