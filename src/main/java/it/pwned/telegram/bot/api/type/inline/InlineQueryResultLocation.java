package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a location on a map. By default, the location will be sent by the
 * user. Alternatively, you can use
 * {@link InlineQueryResultLocation#inputMessageContent inputMessageContent} to
 * send a message with the specified content instead of the location.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultLocation extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_LATITUDE = "latitude";
	private final static String JSON_FIELD_LONGITUDE = "longitude";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_LIVE_PERIOD = "live_period";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";
	private final static String JSON_FIELD_THUMB_URL = "thumb_url";
	private final static String JSON_FIELD_THUMB_WIDTH = "thumb_width";
	private final static String JSON_FIELD_THUMB_HEIGHT = "thumb_height";

	/**
	 * Type of the result, must be location
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "location";

	/**
	 * Unique identifier for this result, 1-64 Bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * Location latitude in degrees
	 */
	@JsonProperty(JSON_FIELD_LATITUDE)
	public final Float latitude;

	/**
	 * Location longitude in degrees
	 */
	@JsonProperty(JSON_FIELD_LONGITUDE)
	public final Float longitude;

	/**
	 * Location title
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Period in seconds for which the location can be updated, should be between 60 and 86400.
	 */
	@JsonProperty(JSON_FIELD_LIVE_PERIOD)
	public final Integer livePeriod;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the
	 * location
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
	 *            Location latitude in degrees
	 * @param longitude
	 *            Location longitude in degrees
	 * @param title
	 *            Location title
	 * @param livePeriod
	 * 			  <em>Optional.</em> Period in seconds for which the location can be updated, should be between 60 and 86400.
	 * @param replyMarkup
	 *            <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the location
	 * @param thumbUrl
	 *            <em>Optional.</em> Url of the thumbnail for the result
	 * @param thumbWidth
	 *            <em>Optional.</em> Thumbnail width
	 * @param thumbHeight
	 *            <em>Optional.</em> Thumbnail height
	 */
	public InlineQueryResultLocation(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_LATITUDE) Float latitude, @JsonProperty(JSON_FIELD_LONGITUDE) Float longitude,
			@JsonProperty(JSON_FIELD_TITLE) String title, @JsonProperty(JSON_FIELD_LIVE_PERIOD) Integer livePeriod,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent,
			@JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl,
			@JsonProperty(JSON_FIELD_THUMB_WIDTH) Integer thumbWidth,
			@JsonProperty(JSON_FIELD_THUMB_HEIGHT) Integer thumbHeight) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.livePeriod = validateLivePeriod(livePeriod);
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;
		this.thumbUrl = thumbUrl;
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;
	}

	private static Integer validateLivePeriod(Integer livePeriod) {
		if(livePeriod != null && (livePeriod < 60 || livePeriod > 86400))
			throw new IllegalArgumentException("invalid livePeriod value");

		return livePeriod;
	}

}
