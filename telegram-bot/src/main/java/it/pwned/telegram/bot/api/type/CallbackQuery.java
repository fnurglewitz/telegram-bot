package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CallbackQuery {

	public final String id;
	public final User from;
	public final Message message;
	public final String inline_message_id;
	public final String data;

	public CallbackQuery(@JsonProperty("id") String id, @JsonProperty("from") User from,
			@JsonProperty("message") Message message, @JsonProperty("inline_message_id") String inline_message_id,
			@JsonProperty("data") String data) {

		this.id = id;
		this.from = from;
		this.message = message;
		this.inline_message_id = inline_message_id;
		this.data = data;

	}

}
