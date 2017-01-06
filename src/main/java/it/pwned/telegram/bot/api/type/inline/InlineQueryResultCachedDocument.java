package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to a file stored on the Telegram servers. By default, this
 * file will be sent by the user with an optional caption. Alternatively, you
 * can use {@link InlineQueryResultCachedDocument#inputMessageContent
 * inputMessageContent} to send a message with the specified content instead of
 * the file. Currently, only <b>pdf</b>-files and <b>zip</b> archives can be
 * sent using this method.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedDocument extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_DOCUMENT_FILE_ID = "document_file_id";
	private final static String JSON_FIELD_DESCRIPTION = "description";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

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
	 * A valid file identifier for the file
	 */
	@JsonProperty(JSON_FIELD_DOCUMENT_FILE_ID)
	public final String documentFileId;

	/**
	 * <em>Optional.</em> Short description of the result
	 */
	@JsonProperty(JSON_FIELD_DESCRIPTION)
	public final String description;

	/**
	 * <em>Optional.</em> Caption of the document to be sent, 0-200 characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

	/**
	 * <em>Optional.</em> An Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the file
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * 
	 * @param id
	 *            Unique identifier for this result, 1-64 bytes
	 * @param title
	 *            Title for the result
	 * @param documentFileId
	 *            A valid file identifier for the file
	 * @param description
	 *            <em>Optional.</em> Short description of the result
	 * @param caption
	 *            <em>Optional.</em> Caption of the document to be sent, 0-200
	 *            characters
	 * @param replyMarkup
	 *            <em>Optional.</em> An Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the file
	 */
	public InlineQueryResultCachedDocument(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_DOCUMENT_FILE_ID) String documentFileId,
			@JsonProperty(JSON_FIELD_DESCRIPTION) String description, @JsonProperty(JSON_FIELD_CAPTION) String caption,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.title = title;
		this.documentFileId = documentFileId;
		this.description = description;
		this.caption = caption;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}

}
