package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Update implements Comparable<Update> {

	public final Integer update_id;
	public final Message message;

	public Update(@JsonProperty("update_id") Integer update_id, @JsonProperty("message") Message message) {
		this.update_id = update_id;
		this.message = message;
	}

	@Override
	public int compareTo(Update other) {
		return this.update_id.compareTo(other.update_id);
	}
}