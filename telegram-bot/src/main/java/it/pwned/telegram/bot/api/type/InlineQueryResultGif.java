package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
	public final String message_text;
	public final String parse_mode;
	public final Boolean disable_web_page_preview;

	public InlineQueryResultGif(@JsonProperty("id") String id, @JsonProperty("gif_url") String gif_url,
			@JsonProperty("gif_width") Integer gif_width, @JsonProperty("gif_height") Integer gif_height,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("title") String title,
			@JsonProperty("caption") String caption, @JsonProperty("message_text") String message_text,
			@JsonProperty("parse_mode") String parse_mode,
			@JsonProperty("disable_web_page_preview") Boolean disable_web_page_preview) {
		this.id = id;
		this.gif_url = gif_url;
		this.gif_width = gif_width;
		this.gif_height = gif_height;
		this.thumb_url = thumb_url;
		this.title = title;
		this.caption = caption;
		this.message_text = message_text;
		this.parse_mode = parse_mode;
		this.disable_web_page_preview = disable_web_page_preview;
	}

}
