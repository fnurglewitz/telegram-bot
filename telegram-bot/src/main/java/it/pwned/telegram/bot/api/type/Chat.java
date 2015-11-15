package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Chat {
	public final Long id;
	public final String type;
	public final String title;
	public final String username;
	public final String first_name;
	public final String last_name;

	public Chat(@JsonProperty("id") Long id, @JsonProperty("type") String type, @JsonProperty("title") String title,
			@JsonProperty("username") String username, @JsonProperty("first_name") String first_name,
			@JsonProperty("last_name") String last_name) {

		this.id = id;
		this.type = type;
		this.title = title;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
	}

}