package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains information about one member of the chat.
 *
 */
public class ChatMember {

	private final static String JSON_FIELD_USER = "user";
	private final static String JSON_FIELD_STATUS = "status";

	/**
	 * Information about the user
	 */
	@JsonProperty(JSON_FIELD_USER)
	public final User user;

	/**
	 * The member's status in the chat.
	 */
	@JsonProperty(JSON_FIELD_STATUS)
	public final ChatMemberStatus status;

	/**
	 * 
	 * @param user
	 *          Information about the user
	 * @param status
	 *          The member's status in the chat. Can be "creator",
	 *          "administrator", "member", "left" or "kicked"
	 */
	public ChatMember(@JsonProperty(JSON_FIELD_USER) User user,
			@JsonProperty(JSON_FIELD_STATUS) ChatMemberStatus status) {
		this.user = user;
		this.status = status;
	}

}
