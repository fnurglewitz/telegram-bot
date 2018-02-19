package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ParseMode;

/**
 * Represents a link to an mp3 audio file. By default, this audio file will be
 * sent by the user. Alternatively, you can use
 * {@link InlineQueryResultAudio#inputMessageContent inputMessageContent} to
 * send a message with the specified content instead of the audio.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultAudio extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_AUDIO_URL = "audio_url";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_PARSE_MODE = "parse_mode";
	private final static String JSON_FIELD_PERFORMER = "performer";
	private final static String JSON_FIELD_AUDIO_DURATION = "audio_duration";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

	/**
	 * Type of the result, must be audio
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "audio";

	/**
	 * Unique identifier for this result, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * A valid URL for the audio file
	 */
	@JsonProperty(JSON_FIELD_AUDIO_URL)
	public final String audioUrl;

	/**
	 * Title
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Caption, 0-200 characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

    /**
     * <em>Optional.</em> Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     */
    @JsonProperty(JSON_FIELD_PARSE_MODE)
    public final ParseMode parseMode;

	/**
	 * <em>Optional.</em> Performer
	 */
	@JsonProperty(JSON_FIELD_PERFORMER)
	public final String performer;

	/**
	 * <em>Optional.</em> Audio duration in seconds
	 */
	@JsonProperty(JSON_FIELD_AUDIO_DURATION)
	public final Integer audioDuration;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the audio
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * 
	 * @param id
	 *          Unique identifier for this result, 1-64 bytes
	 * @param audioUrl
	 *          A valid URL for the audio file
	 * @param title
	 *          Title
	 * @param caption
	 *          <em>Optional.</em> Caption, 0-200 characters
     * @param parseMode           <em>Optional.</em> Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
	 * @param performer
	 *          <em>Optional.</em> Performer
	 * @param audioDuration
	 *          <em>Optional.</em> Audio duration in seconds
	 * @param replyMarkup
	 *          <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *          <em>Optional.</em> Content of the message to be sent instead of
	 *          the audio
	 */
	public InlineQueryResultAudio(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_AUDIO_URL) String audioUrl, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_CAPTION) String caption, @JsonProperty(JSON_FIELD_PARSE_MODE) ParseMode parseMode,
            @JsonProperty(JSON_FIELD_PERFORMER) String performer,
			@JsonProperty(JSON_FIELD_AUDIO_DURATION) Integer audioDuration,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.audioUrl = audioUrl;
		this.title = title;
		this.caption = caption;
		this.parseMode = parseMode;
		this.performer = performer;
		this.audioDuration = audioDuration;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}
}
