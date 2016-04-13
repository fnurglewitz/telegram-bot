package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultContact extends InlineQueryResult {

	public final String type = "contact";
	public final String id;
	public final String phone_number;
	public final String first_name;
	public final String last_name;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;
	public final String thumb_url;
	public final Integer thumb_width;
	public final Integer thumb_height;

	public InlineQueryResultContact(@JsonProperty("id") String id, @JsonProperty("phone_number") String phone_number,
			@JsonProperty("first_name") String first_name, @JsonProperty("last_name") String last_name,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("thumb_width") Integer thumb_width,
			@JsonProperty("thumb_height") Integer thumb_height) {
		this.id = id;
		this.phone_number = phone_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
		this.thumb_url = thumb_url;
		this.thumb_width = thumb_width;
		this.thumb_height = thumb_height;
	}
}
