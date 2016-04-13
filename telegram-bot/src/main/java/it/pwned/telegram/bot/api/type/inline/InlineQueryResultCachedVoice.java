package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedVoice extends InlineQueryResult {

	public final String type = "voice";
	public final String id;
	public final String voice_file_id;
	public final String title;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultCachedVoice(@JsonProperty("id") String id,
			@JsonProperty("voice_file_id") String voice_file_id, @JsonProperty("title") String title,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.voice_file_id = voice_file_id;
		this.title = title;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}

}
