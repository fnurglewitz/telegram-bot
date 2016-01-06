package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultVideo extends InlineQueryResult {

	public final String type = "video";
	public final String id;
	public final String video_url;
	public final String mime_type;
	public final String message_text;
	public final String parse_mode;
	public final Boolean disable_web_page_preview;
	public final Integer video_width;
	public final Integer video_height;
	public final Integer video_duration;
	public final String thumb_url;
	public final String title;
	public final String description;

	public InlineQueryResultVideo(@JsonProperty("id") String id, @JsonProperty("video_url") String video_url,
			@JsonProperty("mime_type") String mime_type, @JsonProperty("message_text") String message_text,
			@JsonProperty("parse_mode") String parse_mode,
			@JsonProperty("disable_web_page_preview") Boolean disable_web_page_preview,
			@JsonProperty("video_width") Integer video_width, @JsonProperty("video_height") Integer video_height,
			@JsonProperty("video_duration") Integer video_duration, @JsonProperty("thumb_url") String thumb_url,
			@JsonProperty("title") String title, @JsonProperty("description") String description) {
		this.id = id;
		this.video_url = video_url;
		this.mime_type = mime_type;
		this.message_text = message_text;
		this.parse_mode = parse_mode;
		this.disable_web_page_preview = disable_web_page_preview;
		this.video_width = video_width;
		this.video_height = video_height;
		this.video_duration = video_duration;
		this.thumb_url = thumb_url;
		this.title = title;
		this.description = description;
	}

}
