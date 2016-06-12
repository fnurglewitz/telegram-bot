package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to an article or web page.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultArticle extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_URL = "url";
	private final static String JSON_FIELD_HIDE_URL = "hide_url";
	private final static String JSON_FIELD_DESCRIPTION = "description";
	private final static String JSON_FIELD_THUMB_URL = "thumb_url";
	private final static String JSON_FIELD_THUMB_WIDTH = "thumb_width";
	private final static String JSON_FIELD_THUMB_HEIGHT = "thumb_height";

	/**
	 * Type of the result, must be article
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "article";

	/**
	 * Unique identifier for this result, 1-64 Bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * Title of the result
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * Content of the message to be sent
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> URL of the result
	 */
	@JsonProperty(JSON_FIELD_URL)
	public final String url;

	/**
	 * <em>Optional.</em> Pass True, if you don't want the URL to be shown in the
	 * message
	 */
	@JsonProperty(JSON_FIELD_HIDE_URL)
	public final Boolean hideUrl;

	/**
	 * <em>Optional.</em> Short description of the result
	 */
	@JsonProperty(JSON_FIELD_DESCRIPTION)
	public final String description;

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
	 *          Unique identifier for this result, 1-64 Bytes
	 * @param title
	 *          Title of the result
	 * @param inputMessageContent
	 *          Content of the message to be sent
	 * @param replyMarkup
	 *          <em>Optional.</em> Inline keyboard attached to the message
	 * @param url
	 *          <em>Optional.</em> URL of the result
	 * @param hideUrl
	 *          <em>Optional.</em> Pass True, if you don't want the URL to be
	 *          shown in the message
	 * @param description
	 *          <em>Optional.</em> Short description of the result
	 * @param thumbUrl
	 *          <em>Optional.</em> Url of the thumbnail for the result
	 * @param thumbWidth
	 *          <em>Optional.</em> Thumbnail width
	 * @param thumbHeight
	 *          <em>Optional.</em> Thumbnail height
	 */
	public InlineQueryResultArticle(@JsonProperty(JSON_FIELD_ID) String id, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup, @JsonProperty(JSON_FIELD_URL) String url,
			@JsonProperty(JSON_FIELD_HIDE_URL) Boolean hideUrl, @JsonProperty(JSON_FIELD_DESCRIPTION) String description,
			@JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl, @JsonProperty(JSON_FIELD_THUMB_WIDTH) Integer thumbWidth,
			@JsonProperty(JSON_FIELD_THUMB_HEIGHT) Integer thumbHeight) {
		this.id = id;
		this.title = title;
		this.inputMessageContent = inputMessageContent;
		this.replyMarkup = replyMarkup;
		this.url = url;
		this.hideUrl = hideUrl;
		this.description = description;
		this.thumbUrl = thumbUrl;
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;
	}

}
