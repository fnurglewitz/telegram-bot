package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultAudio extends InlineQueryResult {

	public final String type = "audio";
	public final String id;
	public final String audio_url;
	public final String title;
	public final String performer;
	public final Integer audio_duration;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultAudio(@JsonProperty("id") String id, @JsonProperty("audio_url") String audio_url,
			@JsonProperty("title") String title, @JsonProperty("performer") String performer,
			@JsonProperty("audio_duration") Integer audio_duration,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.audio_url = audio_url;
		this.title = title;
		this.performer = performer;
		this.audio_duration = audio_duration;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;

	}
}
