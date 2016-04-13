package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultCachedAudio extends InlineQueryResult {

	public final String type = "audio";
	public final String id;
	public final String audio_file_id;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultCachedAudio(@JsonProperty("id") String id,
			@JsonProperty("audio_file_id") String audio_file_id,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.audio_file_id = audio_file_id;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;
	}

}
