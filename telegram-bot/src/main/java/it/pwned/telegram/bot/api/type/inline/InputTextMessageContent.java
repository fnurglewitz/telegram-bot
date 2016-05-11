package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InputTextMessageContent extends InputMessageContent {

	public final String message_text;
	public final String parse_mode;
	public final Boolean disable_web_page_preview;

	public InputTextMessageContent(@JsonProperty("message_text") String message_text,
			@JsonProperty("parse_mode") String parse_mode,
			@JsonProperty("disable_web_page_preview") Boolean disable_web_page_preview) {
		this.message_text = message_text;
		this.parse_mode = parse_mode;
		this.disable_web_page_preview = disable_web_page_preview;
	}

}
