package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultMpeg4Gif extends InlineQueryResult {

	public final String type = "mpeg4_gif";
	public final String id;
	public final String mpeg4_url;
	public final Integer mpeg4_width;
	public final Integer mpeg4_height;
	public final String thumb_url;
	public final String title;
	public final String caption;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultMpeg4Gif(@JsonProperty("id") String id, @JsonProperty("mpeg4_url") String mpeg4_url,
			@JsonProperty("mpeg4_width") Integer mpeg4_width, @JsonProperty("mpeg4_height") Integer mpeg4_height,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("title") String title,
			@JsonProperty("caption") String caption, @JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.mpeg4_url = mpeg4_url;
		this.mpeg4_width = mpeg4_width;
		this.mpeg4_height = mpeg4_height;
		this.thumb_url = thumb_url;
		this.title = title;
		this.caption = caption;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}

}
