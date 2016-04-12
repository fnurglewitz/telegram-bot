package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultPhoto extends InlineQueryResult {

	public final String type = "photo";
	public final String id;
	public final String photo_url;
	public final String thumb_url;
	public final Integer photo_width;
	public final Integer photo_height;
	public final String title;
	public final String description;
	public final String caption;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultPhoto(@JsonProperty("id") String id, @JsonProperty("photo_url") String photo_url,
			@JsonProperty("photo_width") Integer photo_width, @JsonProperty("photo_height") Integer photo_height,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("title") String title,
			@JsonProperty("description") String description, @JsonProperty("caption") String caption,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.photo_url = photo_url;
		this.photo_width = photo_width;
		this.photo_height = photo_height;
		this.thumb_url = thumb_url;
		this.title = title;
		this.description = description;
		this.caption = caption;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}
}
