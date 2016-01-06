package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineQuery {

	public final String id;
	public final User from;
	public final String query;
	public final String offset;

	public InlineQuery(@JsonProperty("id") String id, @JsonProperty("from") User from,
			@JsonProperty("query") String query, @JsonProperty("offset") String offset) {
		this.id = id;
		this.from = from;
		this.query = query;
		this.offset = offset;
	}

}
