package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChosenInlineResult {

	public final String result_id;
	public final User from;
	public final String query;

	public ChosenInlineResult(@JsonProperty("result_id") String result_id, @JsonProperty("from") User from,
			@JsonProperty("query") String query) {
		this.result_id = result_id;
		this.from = from;
		this.query = query;
	}

}
