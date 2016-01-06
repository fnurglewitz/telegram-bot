package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
	public final String message_text;
	public final String parse_mode;
	public final Boolean disable_web_page_preview;

	public InlineQueryResultMpeg4Gif(@JsonProperty("id") String id, @JsonProperty("mpeg4_url") String mpeg4_url,
			@JsonProperty("mpeg4_width") Integer mpeg4_width, @JsonProperty("mpeg4_height") Integer mpeg4_height,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("title") String title,
			@JsonProperty("caption") String caption, @JsonProperty("message_text") String message_text,
			@JsonProperty("parse_mode") String parse_mode,
			@JsonProperty("disable_web_page_preview") Boolean disable_web_page_preview) {
		this.id = id;
		this.mpeg4_url = mpeg4_url;
		this.mpeg4_width = mpeg4_width;
		this.mpeg4_height = mpeg4_height;
		this.thumb_url = thumb_url;
		this.title = title;
		this.caption = caption;
		this.message_text = message_text;
		this.parse_mode = parse_mode;
		this.disable_web_page_preview = disable_web_page_preview;
	}

}
