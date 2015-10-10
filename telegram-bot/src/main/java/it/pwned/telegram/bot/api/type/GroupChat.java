package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class GroupChat {
	public final Integer id;
	public final String title;

	public GroupChat(@JsonProperty("id") Integer id, @JsonProperty("title") String title) {
		this.id = id;
		this.title = title;
	}
}