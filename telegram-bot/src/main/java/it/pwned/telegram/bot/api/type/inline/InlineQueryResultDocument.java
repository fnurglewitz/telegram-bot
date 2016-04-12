package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultDocument extends InlineQueryResult {

	public final String type = "document";
	public final String id;
	public final String title;
	public final String caption;
	public final String document_url;
	public final String mime_type;
	public final String description;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;
	public final String thumb_url;
	public final Integer thumb_width;
	public final Integer thumb_height;

	public InlineQueryResultDocument(@JsonProperty("id") String id, @JsonProperty("title") String title,
			@JsonProperty("caption") String caption, @JsonProperty("document_url") String document_url,
			@JsonProperty("mime_type") String mime_type, @JsonProperty("description") String description,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content,
			@JsonProperty("thumb_url") String thumb_url, @JsonProperty("thumb_width") Integer thumb_width,
			@JsonProperty("thumb_height") Integer thumb_height) {
		this.id = id;
		this.title = title;
		this.caption = caption;
		this.document_url = document_url;
		this.mime_type = mime_type;
		this.description = description;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
		this.thumb_url = thumb_url;
		this.thumb_width = thumb_width;
		this.thumb_height = thumb_height;

	}
}
