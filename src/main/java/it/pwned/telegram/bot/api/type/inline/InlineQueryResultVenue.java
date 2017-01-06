package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a venue. By default, the venue will be sent by the user.
 * Alternatively, you can use {@link InlineQueryResultVenue#inputMessageContent
 * inputMessageContent} to send a message with the specified content instead of
 * the venue.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultVenue extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_LATITUDE = "latitude";
	private final static String JSON_FIELD_LONGITUDE = "longitude";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_ADDRESS = "address";
	private final static String JSON_FIELD_FOURSQUARE_ID = "foursquare_id";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";
	private final static String JSON_FIELD_THUMB_URL = "thumb_url";
	private final static String JSON_FIELD_THUMB_WIDTH = "thumb_width";
	private final static String JSON_FIELD_THUMB_HEIGHT = "thumb_height";

	/**
	 * Type of the result, must be venue
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "venue";

	/**
	 * Unique identifier for this result, 1-64 Bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * Latitude of the venue location in degrees
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final Float latitude;

	/**
	 * Longitude of the venue location in degrees
	 */
	@JsonProperty(JSON_FIELD_LONGITUDE)
	public final Float longitude;

	/**
	 * Title of the venue
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * Address of the venue
	 */
	@JsonProperty(JSON_FIELD_ADDRESS)
	public final String address;

	/**
	 * <em>Optional.</em> Foursquare identifier of the venue if known
	 */
	@JsonProperty(JSON_FIELD_FOURSQUARE_ID)
	public final String foursquareId;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the venue
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * <em>Optional.</em> Url of the thumbnail for the result
	 */
	@JsonProperty(JSON_FIELD_THUMB_URL)
	public final String thumbUrl;

	/**
	 * <em>Optional.</em> Thumbnail width
	 */
	@JsonProperty(JSON_FIELD_THUMB_WIDTH)
	public final Integer thumbWidth;

	/**
	 * <em>Optional.</em> Thumbnail height
	 */
	@JsonProperty(JSON_FIELD_THUMB_HEIGHT)
	public final Integer thumbHeight;

	/**
	 * 
	 * @param id
	 *            Unique identifier for this result, 1-64 Bytes
	 * @param latitude
	 *            Latitude of the venue location in degrees
	 * @param longitude
	 *            Longitude of the venue location in degrees
	 * @param title
	 *            Title of the venue
	 * @param address
	 *            Address of the venue
	 * @param foursquareId
	 *            <em>Optional.</em> Foursquare identifier of the venue if known
	 * @param replyMarkup
	 *            <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the venue
	 * @param thumbUrl
	 *            <em>Optional.</em> Url of the thumbnail for the result
	 * @param thumbWidth
	 *            <em>Optional.</em> Thumbnail width
	 * @param thumbHeight
	 *            <em>Optional.</em> Thumbnail height
	 */
	public InlineQueryResultVenue(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_LATITUDE) Float latitude, @JsonProperty(JSON_FIELD_LONGITUDE) Float longitude,
			@JsonProperty(JSON_FIELD_TITLE) String title, @JsonProperty(JSON_FIELD_ADDRESS) String address,
			@JsonProperty(JSON_FIELD_FOURSQUARE_ID) String foursquareId,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent,
			@JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl,
			@JsonProperty(JSON_FIELD_THUMB_WIDTH) Integer thumbWidth,
			@JsonProperty(JSON_FIELD_THUMB_HEIGHT) Integer thumbHeight) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.address = address;
		this.foursquareId = foursquareId;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;
		this.thumbUrl = thumbUrl;
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;

	}

}
