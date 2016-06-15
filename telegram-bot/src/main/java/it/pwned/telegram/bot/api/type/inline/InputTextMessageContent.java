package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents the content of a text message to be sent as the result of an
 * inline query.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InputTextMessageContent extends InputMessageContent {

	private final static String JSON_FIELD_MESSAGE_TEXT = "message_text";
	private final static String JSON_FIELD_PARSE_MODE = "parse_mode";
	private final static String JSON_FIELD_DISABLE_WEB_PAGE_PREVIEW = "disable_web_page_preview";

	/**
	 * Text of the message to be sent, 1-4096 characters
	 */
	@JsonProperty(JSON_FIELD_MESSAGE_TEXT)
	public final String messageText;

	/**
	 * <em>Optional.</em> Send Markdown or HTML, if you want Telegram apps to show
	 * bold, italic, fixed-width text or inline URLs in your bot's message.
	 */
	@JsonProperty(JSON_FIELD_PARSE_MODE)
	public final String parseMode;

	/**
	 * <em>Optional.</em> Disables link previews for links in the sent message
	 */
	@JsonProperty(JSON_FIELD_DISABLE_WEB_PAGE_PREVIEW)
	public final Boolean disableWebPagePreview;

	/**
	 * 
	 * @param messageText
	 *          Text of the message to be sent, 1-4096 characters
	 * @param parseMode
	 *          <em>Optional.</em> Send Markdown or HTML, if you want Telegram
	 *          apps to show bold, italic, fixed-width text or inline URLs in your
	 *          bot's message.
	 * @param disableWebPagePreview
	 *          <em>Optional.</em> Disables link previews for links in the sent
	 *          message
	 */
	public InputTextMessageContent(@JsonProperty(JSON_FIELD_MESSAGE_TEXT) String messageText,
			@JsonProperty(JSON_FIELD_PARSE_MODE) String parseMode,
			@JsonProperty(JSON_FIELD_DISABLE_WEB_PAGE_PREVIEW) Boolean disableWebPagePreview) {
		this.messageText = messageText;
		this.parseMode = parseMode;
		this.disableWebPagePreview = disableWebPagePreview;

	}

}
