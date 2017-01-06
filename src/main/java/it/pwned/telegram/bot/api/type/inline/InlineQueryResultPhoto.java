package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to a photo. By default, this photo will be sent by the user
 * with optional caption. Alternatively, you can use
 * {@link InlineQueryResultPhoto#inputMessageContent inputMessageContent} to
 * send a message with the specified content instead of the photo.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultPhoto extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_PHOTO_URL = "photo_url";
	private final static String JSON_FIELD_THUMB_URL = "thumb_url";
	private final static String JSON_FIELD_PHOTO_WIDTH = "photo_width";
	private final static String JSON_FIELD_PHOTO_HEIGHT = "photo_height";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_DESCRIPTION = "description";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

	/**
	 * Type of the result, must be photo
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "photo";

	/**
	 * Unique identifier for this result, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * A valid URL of the photo. Photo must be in jpeg format. Photo size must
	 * not exceed 5MB
	 */
	@JsonProperty(JSON_FIELD_PHOTO_URL)
	public final String photoUrl;

	/**
	 * URL of the thumbnail for the photo
	 */
	@JsonProperty(JSON_FIELD_THUMB_URL)
	public final String thumbUrl;

	/**
	 * <em>Optional.</em> Width of the photo
	 */
	@JsonProperty(JSON_FIELD_PHOTO_WIDTH)
	public final Integer photoWidth;

	/**
	 * <em>Optional.</em> Height of the photo
	 */
	@JsonProperty(JSON_FIELD_PHOTO_HEIGHT)
	public final Integer photoHeight;

	/**
	 * <em>Optional.</em> Title for the result
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Short description of the result
	 */
	@JsonProperty(JSON_FIELD_DESCRIPTION)
	public final String description;

	/**
	 * <em>Optional.</em> Caption of the photo to be sent, 0-200 characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the photo
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * 
	 * @param id
	 *            Unique identifier for this result, 1-64 bytes
	 * @param photoUrl
	 *            A valid URL of the photo. Photo must be in jpeg format. Photo
	 *            size must not exceed 5MB
	 * @param thumbUrl
	 *            URL of the thumbnail for the photo
	 * @param photoWidth
	 *            <em>Optional.</em> Width of the photo
	 * @param photoHeight
	 *            <em>Optional.</em> Height of the photo
	 * @param title
	 *            <em>Optional.</em> Title for the result
	 * @param description
	 *            <em>Optional.</em> Short description of the result
	 * @param caption
	 *            <em>Optional.</em> Caption of the photo to be sent, 0-200
	 *            characters
	 * @param replyMarkup
	 *            <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the photo
	 */
	public InlineQueryResultPhoto(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_PHOTO_URL) String photoUrl, @JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl,
			@JsonProperty(JSON_FIELD_PHOTO_WIDTH) Integer photoWidth,
			@JsonProperty(JSON_FIELD_PHOTO_HEIGHT) Integer photoHeight, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_DESCRIPTION) String description, @JsonProperty(JSON_FIELD_CAPTION) String caption,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.photoUrl = photoUrl;
		this.thumbUrl = thumbUrl;
		this.photoWidth = photoWidth;
		this.photoHeight = photoHeight;
		this.title = title;
		this.description = description;
		this.caption = caption;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}
}
