package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to a sticker stored on the Telegram servers. By default,
 * this sticker will be sent by the user. Alternatively, you can use
 * {@link InlineQueryResultCachedSticker#inputMessageContent
 * inputMessageContent} to send a message with the specified content instead of
 * the sticker.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedSticker extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_STICKER_FILE_ID = "sticker_file_id";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
	private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

	/**
	 * Type of the result, must be sticker
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "sticker";

	/**
	 * Unique identifier for this result, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * A valid file identifier of the sticker
	 */
	@JsonProperty(JSON_FIELD_STICKER_FILE_ID)
	public final String stickerFileId;

	/**
	 * <em>Optional.</em> An Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * <em>Optional.</em> Content of the message to be sent instead of the
	 * sticker
	 */
	@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
	public final InputMessageContent inputMessageContent;

	/**
	 * 
	 * @param id
	 *            Unique identifier for this result, 1-64 bytes
	 * @param stickerFileId
	 *            A valid file identifier of the sticker
	 * @param replyMarkup
	 *            <em>Optional.</em> An Inline keyboard attached to the message
	 * @param inputMessageContent
	 *            <em>Optional.</em> Content of the message to be sent instead
	 *            of the sticker
	 */
	public InlineQueryResultCachedSticker(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_STICKER_FILE_ID) String stickerFileId,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
			@JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
		this.id = id;
		this.stickerFileId = stickerFileId;
		this.replyMarkup = replyMarkup;
		this.inputMessageContent = inputMessageContent;

	}

}
