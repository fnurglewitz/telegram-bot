package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents the content of a contact message to be sent as the result of an
 * inline query.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InputContactMessageContent extends InputMessageContent {

	private final static String JSON_FIELD_PHONE_NUMBER = "phone_number";
	private final static String JSON_FIELD_FIRST_NAME = "first_name";
	private final static String JSON_FIELD_LAST_NAME = "last_name";

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
	 * 
	 * @param phoneNumber
	 *          Contact's phone number
	 * @param firstName
	 *          Contact's first name
	 * @param lastName
	 *          <em>Optional.</em> Contact's last name
	 */
	public InputContactMessageContent(@JsonProperty(JSON_FIELD_PHONE_NUMBER) String phoneNumber,
			@JsonProperty(JSON_FIELD_FIRST_NAME) String firstName, @JsonProperty(JSON_FIELD_LAST_NAME) String lastName) {
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;

	}

}
