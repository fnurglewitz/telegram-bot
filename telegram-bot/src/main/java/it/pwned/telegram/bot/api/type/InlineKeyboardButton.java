package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineKeyboardButton {
	public final String text;
	public final String url;
	public final String callback_data;
	public final String switch_inline_query;

	public InlineKeyboardButton(@JsonProperty("text") String text, @JsonProperty("url") String url,
			@JsonProperty("callback_data") String callback_data,
			@JsonProperty("switch_inline_query") String switch_inline_query) {
		this.text = text;
		this.url = url;
		this.callback_data = callback_data;
		this.switch_inline_query = switch_inline_query;
	}
}
