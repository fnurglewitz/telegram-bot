package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a contact with a phone number. By default, this contact will be
 * sent by the user. Alternatively, you can use
 * {@link InlineQueryResultContact#inputMessageContent inputMessageContent} to
 * send a message with the specified content instead of the contact.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultContact extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_PHONE_NUMBER = "phone_number";
	private final static String JSON_FIELD_FIRST_NAME = "first_name";
	private final static String JSON_FIELD_LAST_NAME = "last_name";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";
	private final static String JSON_FIELD_THUMB_URL = "thumb_url";
	private final static String JSON_FIELD_THUMB_WIDTH = "thumb_width";
	private final static String JSON_FIELD_THUMB_HEIGHT = "thumb_height";

	/**
	 * Type of the result, must be contact
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "contact";

	/**
	 * Unique identifier for this result, 1-64 Bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * Contact's phone number
	 */
	@JsonProperty(JSON_FIELD_PHONE_NUMBER)
	public final String phoneNumber;

	/**
	 * Contact's first name
	 */
	@JsonProperty(JSON_FIELD_FIRST_NAME)
	public final String firstName;

	/**
	 * <em>Optional.</em> Contact's last name
	 */
	@JsonProperty(JSON_FIELD_LAST_NAME)
	public final String lastName;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the
	 * contact
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
	 * @param phoneNumber
	 *            Contact's phone number
	 * @param firstName
	 *            Contact's first name
	 * @param lastName
	 *            <em>Optional.</em> Contact's last name
	 * @param replyMarkup
	 *            <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the contact
	 * @param thumbUrl
	 *            <em>Optional.</em> Url of the thumbnail for the result
	 * @param thumbWidth
	 *            <em>Optional.</em> Thumbnail width
	 * @param thumbHeight
	 *            <em>Optional.</em> Thumbnail height
	 */
	public InlineQueryResultContact(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_PHONE_NUMBER) String phoneNumber,
			@JsonProperty(JSON_FIELD_FIRST_NAME) String firstName, @JsonProperty(JSON_FIELD_LAST_NAME) String lastName,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent,
			@JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl,
			@JsonProperty(JSON_FIELD_THUMB_WIDTH) Integer thumbWidth,
			@JsonProperty(JSON_FIELD_THUMB_HEIGHT) Integer thumbHeight) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;
		this.thumbUrl = thumbUrl;
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;

	}

}
