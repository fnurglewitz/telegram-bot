package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultArticle extends InlineQueryResult {

	public final String type = "article";
	public final String id;
	public final String title;
	public final InputMessageContent input_message_content;
	public final InlineKeyboardMarkup reply_markup;
	public final String parse_mode;
	public final Boolean disable_web_page_preview;
	public final String url;
	public final Boolean hide_url;
	public final String description;
	public final String thumb_url;
	public final Integer thumb_width;
	public final Integer thumb_height;

	public InlineQueryResultArticle(@JsonProperty("id") String id, @JsonProperty("title") String title,
			@JsonProperty("input_message_content") InputMessageContent input_message_content,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup, @JsonProperty("parse_mode") String parse_mode,
			@JsonProperty("disable_web_page_preview") Boolean disable_web_page_preview, @JsonProperty("url") String url,
			@JsonProperty("hide_url") Boolean hide_url, @JsonProperty("description") String description,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("thumb_width") Integer thumb_width,
			@JsonProperty("thumb_height") Integer thumb_height) {
		this.id = id;
		this.title = title;
		this.input_message_content = input_message_content;
		this.reply_markup = reply_markup;
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
