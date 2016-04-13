package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedPhoto extends InlineQueryResult {

	public final String type = "photo";
	public final String id;
	public final String photo_file_id;
	public final String title;
	public final String description;
	public final String caption;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultCachedPhoto(@JsonProperty("id") String id,
			@JsonProperty("photo_file_id") String photo_file_id, @JsonProperty("title") String title,
			@JsonProperty("description") String description, @JsonProperty("caption") String caption,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.photo_file_id = photo_file_id;
		this.title = title;
		this.description = description;
		this.caption = caption;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}
}
