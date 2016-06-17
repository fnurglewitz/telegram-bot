package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents one button of the reply keyboard. For simple text
 * buttons String can be used instead of this object to specify text of the
 * button. Optional fields are mutually exclusive.
 * 
 */
public class KeyboardButton {

	private final static String JSON_FIELD_TEXT = "text";
	private final static String JSON_FIELD_REQUEST_CONTACT = "request_contact";
	private final static String JSON_FIELD_REQUEST_LOCATION = "request_location";

	/**
	 * Text of the button. If none of the optional fields are used, it will be
	 * sent to the bot as a message when the button is pressed
	 */
	@JsonProperty(JSON_FIELD_TEXT)
	public final String text;

	/**
	 * <em>Optional.</em> If True, the user's phone number will be sent as a
	 * contact when the button is pressed. Available in private chats only
	 */
	@JsonProperty(JSON_FIELD_REQUEST_CONTACT)
	public final Boolean requestContact;

	/**
	 * <em>Optional.</em> If True, the user's current location will be sent when
	 * the button is pressed. Available in private chats only
	 */
	@JsonProperty(JSON_FIELD_REQUEST_LOCATION)
	public final Boolean requestLocation;

	/**
	 * 
	 * @param text
	 *          Text of the button. If none of the optional fields are used, it
	 *          will be sent to the bot as a message when the button is pressed
	 * @param requestContact
	 *          <em>Optional.</em> If True, the user's phone number will be sent
	 *          as a contact when the button is pressed. Available in private
	 *          chats only
	 * @param requestLocation
	 *          <em>Optional.</em> If True, the user's current location will be
	 *          sent when the button is pressed. Available in private chats only
	 */
	public KeyboardButton(@JsonProperty(JSON_FIELD_TEXT) String text,
			@JsonProperty(JSON_FIELD_REQUEST_CONTACT) Boolean requestContact,
			@JsonProperty(JSON_FIELD_REQUEST_LOCATION) Boolean requestLocation) {

		if (Boolean.TRUE.equals(requestContact) && Boolean.TRUE.equals(requestLocation))
			throw new IllegalArgumentException("requestContact and requestLocation fields are mutually exclusive.");

		this.text = text;
		this.requestContact = requestContact == null ? false : requestContact;
		this.requestLocation = requestLocation == null ? false : requestLocation;
	}
}
