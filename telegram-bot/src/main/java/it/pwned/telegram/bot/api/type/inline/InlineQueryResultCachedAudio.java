package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to an mp3 audio file stored on the Telegram servers. By
 * default, this audio file will be sent by the user. Alternatively, you can use
 * {@link InlineQueryResultCachedAudio#inputMessageContent inputMessageContent}
 * to send a message with the specified content instead of the audio.
 * 
 * 
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedAudio extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_AUDIO_FILE_ID = "audio_file_id";
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
	 * A valid file identifier for the audio file
	 */
	@JsonProperty(JSON_FIELD_AUDIO_FILE_ID)
	public final String audioFileId;

	/**
	 * <em>Optional.</em> An Inline keyboard attached to the message
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
	 *            Unique identifier for this result, 1-64 bytes
	 * @param audioFileId
	 *            A valid file identifier for the audio file
	 * @param replyMarkup
	 *            <em>Optional.</em> An Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the audio
	 */
	public InlineQueryResultCachedAudio(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_AUDIO_FILE_ID) String audioFileId,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.audioFileId = audioFileId;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}

}
