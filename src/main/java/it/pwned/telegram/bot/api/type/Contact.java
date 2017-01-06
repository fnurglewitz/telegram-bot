package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a phone contact.
 *
 */
public final class Contact {

	private final static String JSON_FIELD_PHONE_NUMBER = "phone_number";
	private final static String JSON_FIELD_FIRST_NAME = "first_name";
	private final static String JSON_FIELD_LAST_NAME = "last_name";
	private final static String JSON_FIELD_USER_ID = "user_id";

	/**
	 * Contact's phone number
	 */
	@JsonProperty(JSON_FIELD_PHONE_NUMBER)
	public final String phoneNumber;

	/**
	 * Contact's first name
	 */
	@JsonProperty(JSON_FIELD_FIRST_NAME)
	public final String firstName;

	/**
	 * <em>Optional.</em> Contact's last name
	 */
	@JsonProperty(JSON_FIELD_LAST_NAME)
	public final String lastName;

	/**
	 * <em>Optional.</em> Contact's user identifier in Telegram
	 */
	@JsonProperty(JSON_FIELD_USER_ID)
	public final Integer userId;

	/**
	 * 
	 * @param phoneNumber
	 *            Contact's phone number
	 * @param firstName
	 *            Contact's first name
	 * @param lastName
	 *            <em>Optional.</em> Contact's last name
	 * @param userId
	 *            <em>Optional.</em> Contact's user identifier in Telegram
	 */
	public Contact(@JsonProperty(JSON_FIELD_PHONE_NUMBER) String phoneNumber,
			@JsonProperty(JSON_FIELD_FIRST_NAME) String firstName, @JsonProperty(JSON_FIELD_LAST_NAME) String lastName,
			@JsonProperty(JSON_FIELD_USER_ID) Integer userId) {
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
	}

}