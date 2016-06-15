package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without sound)
 * stored on the Telegram servers. By default, this animated MPEG-4 file will be
 * sent by the user with an optional caption. Alternatively, you can use
 * {@link InlineQueryResultCachedMpeg4Gif#inputMessageContent
 * inputMessageContent} to send a message with the specified content instead of
 * the animation.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedMpeg4Gif extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_MPEG4_FILE_ID = "mpeg4_file_id";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

	/**
	 * Type of the result, must be mpeg4_gif
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "mpeg4_gif";

	/**
	 * Unique identifier for this result, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * A valid file identifier for the MP4 file
	 */
	@JsonProperty(JSON_FIELD_MPEG4_FILE_ID)
	public final String mpeg4FileId;

	/**
	 * <em>Optional.</em> Title for the result
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Caption of the MPEG-4 file to be sent, 0-200 characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

	/**
	 * <em>Optional.</em> An Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the video
	 * animation
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * 
	 * @param id
	 *          Unique identifier for this result, 1-64 bytes
	 * @param mpeg4FileId
	 *          A valid file identifier for the MP4 file
	 * @param title
	 *          <em>Optional.</em> Title for the result
	 * @param caption
	 *          <em>Optional.</em> Caption of the MPEG-4 file to be sent, 0-200
	 *          characters
	 * @param replyMarkup
	 *          <em>Optional.</em> An Inline keyboard attached to the message
	 * @param inputMessageContent
	 *          <em>Optional.</em> Content of the message to be sent instead of
	 *          the video animation
	 */
	public InlineQueryResultCachedMpeg4Gif(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_MPEG4_FILE_ID) String mpeg4FileId, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_CAPTION) String caption,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.mpeg4FileId = mpeg4FileId;
		this.title = title;
		this.caption = caption;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}

}
