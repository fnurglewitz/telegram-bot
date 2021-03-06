package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ParseMode;

/**
 * Represents a link to a page containing an embedded video player or a video
 * file. By default, this video file will be sent by the user with an optional
 * caption. Alternatively, you can use
 * {@link InlineQueryResultVideo#inputMessageContent inputMessageContent} to
 * send a message with the specified content instead of the video.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultVideo extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_VIDEO_URL = "video_url";
	private final static String JSON_FIELD_MIME_TYPE = "mime_type";
	private final static String JSON_FIELD_THUMB_URL = "thumb_url";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_PARSE_MODE = "parse_mode";
	private final static String JSON_FIELD_VIDEO_WIDTH = "video_width";
	private final static String JSON_FIELD_VIDEO_HEIGHT = "video_height";
	private final static String JSON_FIELD_VIDEO_DURATION = "video_duration";
	private final static String JSON_FIELD_DESCRIPTION = "description";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

	/**
	 * Type of the result, must be video
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "video";

	/**
	 * Unique identifier for this result, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * A valid URL for the embedded video player or video file
	 */
	@JsonProperty(JSON_FIELD_VIDEO_URL)
	public final String videoUrl;

	/**
	 * Mime type of the content of video url, "text/html" or "video/mp4"
	 */
	@JsonProperty(JSON_FIELD_MIME_TYPE)
	public final String mimeType;

	/**
	 * URL of the thumbnail (jpeg only) for the video
	 */
	@JsonProperty(JSON_FIELD_THUMB_URL)
	public final String thumbUrl;

	/**
	 * Title for the result
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Caption of the video to be sent, 0-200 characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

    /**
     * <em>Optional.</em> Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     */
    @JsonProperty(JSON_FIELD_PARSE_MODE)
    public final ParseMode parseMode;

	/**
	 * <em>Optional.</em> Video width
	 */
	@JsonProperty(JSON_FIELD_VIDEO_WIDTH)
	public final Integer videoWidth;

	/**
	 * <em>Optional.</em> Video height
	 */
	@JsonProperty(JSON_FIELD_VIDEO_HEIGHT)
	public final Integer videoHeight;

	/**
	 * <em>Optional.</em> Video duration in seconds
	 */
	@JsonProperty(JSON_FIELD_VIDEO_DURATION)
	public final Integer videoDuration;

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
	 * <em>Optional.</em> Content of the message to be sent instead of the video
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * 
	 * @param id
	 *          Unique identifier for this result, 1-64 bytes
	 * @param videoUrl
	 *          A valid URL for the embedded video player or video file
	 * @param mimeType
	 *          Mime type of the content of video url, "text/html" or "video/mp4"
	 * @param thumbUrl
	 *          URL of the thumbnail (jpeg only) for the video
	 * @param title
	 *          Title for the result
	 * @param caption
	 *          <em>Optional.</em> Caption of the video to be sent, 0-200
	 *          characters
     * @param parseMode           <em>Optional.</em> Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
	 * @param videoWidth
	 *          <em>Optional.</em> Video width
	 * @param videoHeight
	 *          <em>Optional.</em> Video height
	 * @param videoDuration
	 *          <em>Optional.</em> Video duration in seconds
	 * @param description
	 *          <em>Optional.</em> Short description of the result
	 * @param replyMarkup
	 *          <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *          <em>Optional.</em> Content of the message to be sent instead of
	 *          the video
	 */
	public InlineQueryResultVideo(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_VIDEO_URL) String videoUrl, @JsonProperty(JSON_FIELD_MIME_TYPE) String mimeType,
			@JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_CAPTION) String caption, @JsonProperty(JSON_FIELD_PARSE_MODE) ParseMode parseMode,
            @JsonProperty(JSON_FIELD_VIDEO_WIDTH) Integer videoWidth,
			@JsonProperty(JSON_FIELD_VIDEO_HEIGHT) Integer videoHeight,
			@JsonProperty(JSON_FIELD_VIDEO_DURATION) Integer videoDuration,
			@JsonProperty(JSON_FIELD_DESCRIPTION) String description,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.videoUrl = videoUrl;
		this.mimeType = mimeType;
		this.thumbUrl = thumbUrl;
		this.title = title;
		this.caption = caption;
		this.parseMode = parseMode;
		this.videoWidth = videoWidth;
		this.videoHeight = videoHeight;
		this.videoDuration = videoDuration;
		this.description = description;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}

}
