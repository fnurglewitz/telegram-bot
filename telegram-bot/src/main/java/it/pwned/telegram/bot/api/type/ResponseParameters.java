package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Contains information about why a request was unsuccessfull.
 * 
 */
public class ResponseParameters {

	private final static String JSON_FIELD_MIGRATE_TO_CHAT_ID = "migrate_to_chat_id";
	private final static String JSON_FIELD_RETRY_AFTER = "retry_after";

	/**
	 * <em>Optional.</em> The group has been migrated to a supergroup with the
	 * specified identifier. This number may be greater than 32 bits and some
	 * programming languages may have difficulty/silent defects in interpreting
	 * it. But it is smaller than 52 bits, so a signed 64 bit integer or
	 * double-precision float type are safe for storing this identifier.
	 */
	@JsonProperty(JSON_FIELD_MIGRATE_TO_CHAT_ID)
	public final Long migrateToChatId;

	/**
	 * <em>Optional.</em> In case of exceeding flood control, the number of
	 * seconds left to wait before the request can be repeated
	 * 
	 */
	@JsonProperty(JSON_FIELD_RETRY_AFTER)
	public final Integer retryAfter;

	/**
	 * 
	 * @param migrateToChatId
	 *          <em>Optional.</em> The group has been migrated to a supergroup
	 *          with the specified identifier. This number may be greater than 32
	 *          bits and some programming languages may have difficulty/silent
	 *          defects in interpreting it. But it is smaller than 52 bits, so a
	 *          signed 64 bit integer or double-precision float type are safe for
	 *          storing this identifier.
	 * @param retryAfter
	 *          <em>Optional.</em> In case of exceeding flood control, the number
	 *          of seconds left to wait before the request can be repeated
	 */
	public ResponseParameters(@JsonProperty(JSON_FIELD_MIGRATE_TO_CHAT_ID) Long migrateToChatId,
			@JsonProperty(JSON_FIELD_RETRY_AFTER) Integer retryAfter) {
		this.migrateToChatId = migrateToChatId;
		this.retryAfter = retryAfter;
	}

}
