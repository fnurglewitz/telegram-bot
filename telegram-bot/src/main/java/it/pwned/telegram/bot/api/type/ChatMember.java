package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMember {

	private final static String JSON_FIELD_USER = "user";
	private final static String JSON_FIELD_STATUS = "status";

	@JsonProperty(JSON_FIELD_USER)
	public final User user;

	@JsonProperty(JSON_FIELD_STATUS)
	public final String status;

	public ChatMember(@JsonProperty(JSON_FIELD_USER) User user, @JsonProperty(JSON_FIELD_STATUS) String status) {
		this.user = user;
		this.status = status;
	}

}
