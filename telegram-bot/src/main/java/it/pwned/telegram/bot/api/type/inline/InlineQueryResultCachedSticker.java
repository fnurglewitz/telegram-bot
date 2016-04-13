package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedSticker extends InlineQueryResult {

	public final String type = "sticker";
	public final String id;
	public final String sticker_file_id;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultCachedSticker(@JsonProperty("id") String id, @JsonProperty("sticker_file_id") String sticker_file_id,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.sticker_file_id = sticker_file_id;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}

}
