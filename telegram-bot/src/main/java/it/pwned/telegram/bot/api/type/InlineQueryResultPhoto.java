package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultPhoto extends InlineQueryResult {

	public final String type = "photo";
	public final String id;
	public final String photo_url;
	public final Integer photo_width;
	public final Integer photo_height;
	public final String thumb_url;
	public final String title;
	public final String description;
	public final String caption;
	public final String message_text;
	public final String parse_mode;
	public final Boolean disable_web_page_preview;

	public InlineQueryResultPhoto(@JsonProperty("id") String id, @JsonProperty("photo_url") String photo_url,
			@JsonProperty("photo_width") Integer photo_width, @JsonProperty("photo_height") Integer photo_height,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("title") String title,
			@JsonProperty("description") String description, @JsonProperty("caption") String caption,
			@JsonProperty("message_text") String message_text, @JsonProperty("parse_mode") String parse_mode,
			@JsonProperty("disable_web_page_preview") Boolean disable_web_page_preview) {
		this.id = id;
		this.photo_url = photo_url;
		this.photo_width = photo_width;
		this.photo_height = photo_height;
		this.thumb_url = thumb_url;
		this.title = title;
		this.description = description;
		this.caption = caption;
		this.message_text = message_text;
		this.parse_mode = parse_mode;
		this.disable_web_page_preview = disable_web_page_preview;
	}
}
