package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultVenue extends InlineQueryResult {

	public final String type = "venue";
	public final String id;
	public final Float latitude;
	public final Float longitude;
	public final String title;
	public final String address;
	public final String foursquare_id;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;
	public final String thumb_url;
	public final Integer thumb_width;
	public final Integer thumb_height;

	public InlineQueryResultVenue(@JsonProperty("id") String id, @JsonProperty("latitude") Float latitude,
			@JsonProperty("longitude") Float longitude, @JsonProperty("title") String title,
			@JsonProperty("address") String address, @JsonProperty("foursquare_id") String foursquare_id,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("thumb_width") Integer thumb_width,
			@JsonProperty("thumb_height") Integer thumb_height) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.address = address;
		this.foursquare_id = foursquare_id;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
		this.thumb_url = thumb_url;
		this.thumb_width = thumb_width;
		this.thumb_height = thumb_height;
	}

}
