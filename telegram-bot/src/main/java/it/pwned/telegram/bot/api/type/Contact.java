package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Contact {
	public final String phone_number;
	public final String first_name;
	public final String last_name;
	public final Integer user_id;

	public Contact(@JsonProperty("phone_number") String phone_number, @JsonProperty("first_name") String first_name,
			@JsonProperty("last_name") String last_name, @JsonProperty("user_id") Integer user_id) {
		this.phone_number = phone_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_id = user_id;
	}
}