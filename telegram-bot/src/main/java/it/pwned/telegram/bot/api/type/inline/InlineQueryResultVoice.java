package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

@JsonInclude(Include.NON_NULL)
public class InlineQueryResultVoice extends InlineQueryResult {

	public final String type = "voice";
	public final String id;
	public final String voice_url;
	public final String title;
	public final Integer voice_duration;
	public final InlineKeyboardMarkup reply_markup;
	public final InputMessageContent input_message_content;

	public InlineQueryResultVoice(@JsonProperty("id") String id, @JsonProperty("voice_url") String voice_url,
			@JsonProperty("title") String title, @JsonProperty("voice_duration") Integer voice_duration,
			@JsonProperty("reply_markup") InlineKeyboardMarkup reply_markup,
			@JsonProperty("input_message_content") InputMessageContent input_message_content) {
		this.id = id;
		this.voice_url = voice_url;
		this.title = title;
		this.voice_duration = voice_duration;
		this.reply_markup = reply_markup;
		this.input_message_content = input_message_content;

	}
}
