package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class User {
	public final Integer id;
	public final String first_name;
	public final String last_name;
	public final String username;

	public User(@JsonProperty("id") Integer id, @JsonProperty("first_name") String first_name,
			@JsonProperty("last_name") String last_name, @JsonProperty("username") String username) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.username = username;
	}

}