package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultArticle extends InlineQueryResult {

	public final String type = "article";
	public final String id;
	public final String title;
	public final String message_text;
	public final String parse_mode;
	public final Boolean disable_web_page_preview;
	public final String url;
	public final Boolean hide_url;
	public final String description;
	public final String thumb_url;
	public final Integer thumb_width;
	public final Integer thumb_height;

	public InlineQueryResultArticle(@JsonProperty("id") String id, @JsonProperty("title") String title,
			@JsonProperty("message_text") String message_text, @JsonProperty("parse_mode") String parse_mode,
			@JsonProperty("disable_web_page_preview") Boolean disable_web_page_preview, @JsonProperty("url") String url,
			@JsonProperty("hide_url") Boolean hide_url, @JsonProperty("description") String description,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("thumb_width") Integer thumb_width,
			@JsonProperty("thumb_height") Integer thumb_height) {
		this.id = id;
		this.title = title;
		this.message_text = message_text;
		this.parse_mode = parse_mode;
		this.disable_web_page_preview = disable_web_page_preview;
		this.url = url;
		this.hide_url = hide_url;
		this.description = description;
		this.thumb_url = thumb_url;
		this.thumb_width = thumb_width;
		this.thumb_height = thumb_height;
	}

}
