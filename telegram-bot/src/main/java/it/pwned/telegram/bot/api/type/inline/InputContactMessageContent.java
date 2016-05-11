package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InputContactMessageContent extends InputMessageContent {

	public final String phone_number;
	public final String first_name;
	public final String last_name;

	public InputContactMessageContent(@JsonProperty("phone_number") String phone_number,
			@JsonProperty("first_name") String first_name, @JsonProperty("last_name") String last_name) {
		this.phone_number = phone_number;
		this.first_name = first_name;
		this.last_name = last_name;
	}

}
