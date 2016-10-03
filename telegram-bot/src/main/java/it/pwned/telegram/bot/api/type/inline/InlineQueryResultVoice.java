package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to a voice recording in an .ogg container encoded with
 * OPUS. By default, this voice recording will be sent by the user.
 * Alternatively, you can use {@link InlineQueryResultVoice#inputMessageContent
 * inputMessageContent} to send a message with the specified content instead of
 * the the voice message.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultVoice extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_VOICE_URL = "voice_url";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_VOICE_DURATION = "voice_duration";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

	/**
	 * Type of the result, must be voice
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "voice";

	/**
	 * Unique identifier for this result, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * A valid URL for the voice recording
	 */
	@JsonProperty(JSON_FIELD_VOICE_URL)
	public final String voiceUrl;

	/**
	 * Recording title
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Caption, 0-200 characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

	/**
	 * <em>Optional.</em> Recording duration in seconds
	 */
	@JsonProperty(JSON_FIELD_VOICE_DURATION)
	public final Integer voiceDuration;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the voice
	 * recording
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * 
	 * @param id
	 *          Unique identifier for this result, 1-64 bytes
	 * @param voiceUrl
	 *          A valid URL for the voice recording
	 * @param title
	 *          Recording title
	 * @param caption
	 *          <em>Optional.</em> Caption, 0-200 characters
	 * @param voiceDuration
	 *          <em>Optional.</em> Recording duration in seconds
	 * @param replyMarkup
	 *          <em>Optional.</em> Inline keyboard attached to the message
	 * @param inputMessageContent
	 *          <em>Optional.</em> Content of the message to be sent instead of
	 *          the voice recording
	 */
	public InlineQueryResultVoice(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_VOICE_URL) String voiceUrl, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_CAPTION) String caption, @JsonProperty(JSON_FIELD_VOICE_DURATION) Integer voiceDuration,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.voiceUrl = voiceUrl;
		this.title = title;
		this.caption = caption;
		this.voiceDuration = voiceDuration;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}
}
