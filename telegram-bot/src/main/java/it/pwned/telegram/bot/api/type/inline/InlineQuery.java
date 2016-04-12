package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.Location;
import it.pwned.telegram.bot.api.type.User;

public class InlineQuery {

	public final String id;
	public final User from;
	public final Location location;
	public final String query;
	public final String offset;

	public InlineQuery(@JsonProperty("id") String id, @JsonProperty("from") User from,
			@JsonProperty("location") Location location, @JsonProperty("query") String query,
			@JsonProperty("offset") String offset) {
		this.id = id;
		this.from = from;
		this.location = location;
		this.query = query;
		this.offset = offset;
	}

}
