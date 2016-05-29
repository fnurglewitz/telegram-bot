package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMember {

	public final User user;
	public final String status;

	public ChatMember(@JsonProperty("user") User user, @JsonProperty("status") String status) {
		this.user = user;
		this.status = status;
	}
}
