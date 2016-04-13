package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedDocument extends InlineQueryResult {

	public final String type = "document";
	public final String id;
	public final String title;
	public final String document_file_id;
	public final String description;
	public final String caption;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultCachedDocument(@JsonProperty("id") String id, @JsonProperty("title") String title,
			@JsonProperty("document_file_id") String document_file_id, @JsonProperty("description") String description,
			@JsonProperty("caption") String caption, @JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.title = title;
		this.document_file_id = document_file_id;
		this.description = description;
		this.caption = caption;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}

}
