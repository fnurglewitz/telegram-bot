package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to a file. By default, this file will be sent by the user
 * with an optional caption. Alternatively, you can use
 * {@link InlineQueryResultDocument#inputMessageContent inputMessageContent} to
 * send a message with the specified content instead of the file. Currently,
 * only <b>.PDF</b> and <b>.ZIP</b> files can be sent using this method.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultDocument extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_DOCUMENT_URL = "document_url";
	private final static String JSON_FIELD_MIME_TYPE = "mime_type";
	private final static String JSON_FIELD_DESCRIPTION = "description";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";
	private final static String JSON_FIELD_THUMB_URL = "thumb_url";
	private final static String JSON_FIELD_THUMB_WIDTH = "thumb_width";
	private final static String JSON_FIELD_THUMB_HEIGHT = "thumb_height";

	/**
	 * Type of the result, must be document
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "document";

	/**
	 * Unique identifier for this result, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * Title for the result
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Caption of the document to be sent, 0-200 characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

	/**
	 * A valid URL for the file
	 */
	@JsonProperty(JSON_FIELD_DOCUMENT_URL)
	public final String documentUrl;

	/**
	 * Mime type of the content of the file, either "application/pdf" or
	 * "application/zip"
	 */
	@JsonProperty(JSON_FIELD_MIME_TYPE)
	public final String mimeType;

	/**
	 * <em>Optional.</em> Short description of the result
	 */
	@JsonProperty(JSON_FIELD_DESCRIPTION)
	public final String description;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the file
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * <em>Optional.</em> URL of the thumbnail (jpeg only) for the file
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
	 *            Unique identifier for this result, 1-64 bytes
	 * @param title
	 *            Title for the result
	 * @param caption
	 *            <em>Optional.</em> Caption of the document to be sent, 0-200
	 *            characters
	 * @param documentUrl
	 *            A valid URL for the file
	 * @param mimeType
	 *            Mime type of the content of the file, either "application/pdf"
	 *            or "application/zip"
	 * @param description
	 *            <em>Optional.</em> Short description of the result
	 * @param replyMarkup
	 *            <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the file
	 * @param thumbUrl
	 *            <em>Optional.</em> URL of the thumbnail (jpeg only) for the
	 *            file
	 * @param thumbWidth
	 *            <em>Optional.</em> Thumbnail width
	 * @param thumbHeight
	 *            <em>Optional.</em> Thumbnail height
	 */
	public InlineQueryResultDocument(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_TITLE) String title, @JsonProperty(JSON_FIELD_CAPTION) String caption,
			@JsonProperty(JSON_FIELD_DOCUMENT_URL) String documentUrl,
			@JsonProperty(JSON_FIELD_MIME_TYPE) String mimeType,
			@JsonProperty(JSON_FIELD_DESCRIPTION) String description,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent,
			@JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl,
			@JsonProperty(JSON_FIELD_THUMB_WIDTH) Integer thumbWidth,
			@JsonProperty(JSON_FIELD_THUMB_HEIGHT) Integer thumbHeight) {
		this.id = id;
		this.title = title;
		this.caption = caption;
		this.documentUrl = documentUrl;
		this.mimeType = mimeType;
		this.description = description;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;
		this.thumbUrl = thumbUrl;
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;

	}
}
