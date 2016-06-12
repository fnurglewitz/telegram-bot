package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Chat {

	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_USERNAME = "username";
	private final static String JSON_FIELD_FIRST_NAME = "first_name";
	private final static String JSON_FIELD_LAST_NAME = "last_name";

	@JsonProperty(JSON_FIELD_ID)
	public final Long id;

	@JsonProperty(JSON_FIELD_TYPE)
	public final String type;

	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	@JsonProperty(JSON_FIELD_USERNAME)
	public final String username;

	@JsonProperty(JSON_FIELD_FIRST_NAME)
	public final String firstName;

	@JsonProperty(JSON_FIELD_LAST_NAME)
	public final String lastName;

	public Chat(@JsonProperty(JSON_FIELD_ID) Long id, @JsonProperty(JSON_FIELD_TYPE) String type,
			@JsonProperty(JSON_FIELD_TITLE) String title, @JsonProperty(JSON_FIELD_USERNAME) String username,
			@JsonProperty(JSON_FIELD_FIRST_NAME) String firstName, @JsonProperty(JSON_FIELD_LAST_NAME) String lastName) {

		this.id = id;
		this.type = type;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}

}
