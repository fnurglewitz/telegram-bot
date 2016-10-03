package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a chat.
 *
 */
public final class Chat {

	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_USERNAME = "username";
	private final static String JSON_FIELD_FIRST_NAME = "first_name";
	private final static String JSON_FIELD_LAST_NAME = "last_name";
	private final static String JSON_FIELD_ALL_ADMINS = "all_members_are_administrators";

	/**
	 * Unique identifier for this chat. This number may be greater than 32 bits
	 * and some programming languages may have difficulty/silent defects in
	 * interpreting it. But it smaller than 52 bits, so a signed 64 bit integer or
	 * double-precision float type are safe for storing this identifier.
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final Long id;

	/**
	 * Type of chat
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final ChatType type;

	/**
	 * <em>Optional.</em> Title, for channels and group chats
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	/**
	 * <em>Optional.</em> Username, for private chats, supergroups and channels if
	 * available
	 */
	@JsonProperty(JSON_FIELD_USERNAME)
	public final String username;

	/**
	 * <em>Optional.</em> First name of the other party in a private chat
	 */
	@JsonProperty(JSON_FIELD_FIRST_NAME)
	public final String firstName;

	/**
	 * <em>Optional.</em> Last name of the other party in a private chat
	 */
	@JsonProperty(JSON_FIELD_LAST_NAME)
	public final String lastName;

	/**
	 * <em>Optional.</em> True if a group has "All Members Are Admins" enabled.
	 */
	@JsonProperty(JSON_FIELD_ALL_ADMINS)
	public final Boolean allMembersAreAdmins;

	/**
	 * 
	 * @param id
	 *          Unique identifier for this chat. This number may be greater than
	 *          32 bits and some programming languages may have difficulty/silent
	 *          defects in interpreting it. But it smaller than 52 bits, so a
	 *          signed 64 bit integer or double-precision float type are safe for
	 *          storing this identifier.
	 * @param type
	 *          Type of chat, can be either "private", "group", "supergroup" or
	 *          "channel"
	 * @param title
	 *          <em>Optional.</em> Title, for channels and group chats
	 * @param username
	 *          <em>Optional.</em> Username, for private chats, supergroups and
	 *          channels if available
	 * @param firstName
	 *          <em>Optional.</em> First name of the other party in a private chat
	 * @param lastName
	 *          <em>Optional.</em> Last name of the other party in a private chat
	 * @param allMembersAreAdmins
	 *          <em>Optional.</em> True if a group has "All Members Are Admins"
	 *          enabled.
	 */
	public Chat(@JsonProperty(JSON_FIELD_ID) Long id, @JsonProperty(JSON_FIELD_TYPE) String type,
			@JsonProperty(JSON_FIELD_TITLE) String title, @JsonProperty(JSON_FIELD_USERNAME) String username,
			@JsonProperty(JSON_FIELD_FIRST_NAME) String firstName, @JsonProperty(JSON_FIELD_LAST_NAME) String lastName,
			@JsonProperty(JSON_FIELD_ALL_ADMINS) Boolean allMembersAreAdmins) {

		this.id = id;
		this.type = ChatType.fromString(type);
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.allMembersAreAdmins = allMembersAreAdmins;
	}

}
