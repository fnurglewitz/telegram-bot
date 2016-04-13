package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedMpeg4Gif extends InlineQueryResult {

	public final String type = "mpeg4_gif";
	public final String id;
	public final String mpeg4_file_id;
	public final String title;
	public final String caption;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultCachedMpeg4Gif(@JsonProperty("id") String id, @JsonProperty("mpeg4_file_id") String mpeg4_file_id,
			@JsonProperty("title") String title, @JsonProperty("caption") String caption,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.mpeg4_file_id = mpeg4_file_id;
		this.title = title;
		this.caption = caption;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}
	
}
