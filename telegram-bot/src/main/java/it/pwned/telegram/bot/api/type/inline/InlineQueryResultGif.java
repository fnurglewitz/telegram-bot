package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultGif extends InlineQueryResult {

	public final String type = "gif";
	public final String id;
	public final String gif_url;
	public final Integer gif_width;
	public final Integer gif_height;
	public final String thumb_url;
	public final String title;
	public final String caption;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultGif(@JsonProperty("id") String id, @JsonProperty("gif_url") String gif_url,
			@JsonProperty("gif_width") Integer gif_width, @JsonProperty("gif_height") Integer gif_height,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("title") String title,
			@JsonProperty("caption") String caption, @JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.gif_url = gif_url;
		this.gif_width = gif_width;
		this.gif_height = gif_height;
		this.thumb_url = thumb_url;
		this.title = title;
		this.caption = caption;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}

}
