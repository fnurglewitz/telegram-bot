package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.Location;
import it.pwned.telegram.bot.api.type.User;

public class ChosenInlineResult {

	public final String result_id;
	public final User from;
	public final Location location;
	public final String inline_message_id;
	public final String query;

	public ChosenInlineResult(@JsonProperty("result_id") String result_id, @JsonProperty("from") User from,
			@JsonProperty("location") Location location, @JsonProperty("inline_message_id") String inline_message_id,
			@JsonProperty("query") String query) {
		this.result_id = result_id;
		this.from = from;
		this.location = location;
		this.inline_message_id = inline_message_id;
		this.query = query;
	}

}
