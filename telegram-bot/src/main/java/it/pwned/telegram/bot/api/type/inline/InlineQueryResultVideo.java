package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultVideo extends InlineQueryResult {

	public final String type = "video";
	public final String id;
	public final String video_url;
	public final String mime_type;
	public final String thumb_url;
	public final String title;
	public final String caption;
	public final Integer video_width;
	public final Integer video_height;
	public final Integer video_duration;
	public final String description;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultVideo(@JsonProperty("id") String id, @JsonProperty("video_url") String video_url,
			@JsonProperty("mime_type") String mime_type, @JsonProperty("thumb_url") String thumb_url,
			@JsonProperty("title") String title, @JsonProperty("caption") String caption,
			@JsonProperty("video_width") Integer video_width, @JsonProperty("video_height") Integer video_height,
			@JsonProperty("video_duration") Integer video_duration, @JsonProperty("description") String description,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.video_url = video_url;
		this.mime_type = mime_type;
		this.thumb_url = thumb_url;
		this.title = title;
		this.caption = caption;
		this.video_width = video_width;
		this.video_height = video_height;
		this.video_duration = video_duration;
		this.description = description;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;

	}

}
