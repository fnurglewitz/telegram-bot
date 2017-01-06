package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.Location;
import it.pwned.telegram.bot.api.type.User;

/**
 * Represents a result of an inline query that was chosen by the user and sent
 * to their chat partner.
 *
 */
public class ChosenInlineResult {

	private final static String JSON_FIELD_RESULT_ID = "result_id";
	private final static String JSON_FIELD_FROM = "from";
	private final static String JSON_FIELD_LOCATION = "location";
	private final static String JSON_FIELD_INLINE_MESSAGE_ID = "inline_message_id";
	private final static String JSON_FIELD_QUERY = "query";

	/**
	 * The unique identifier for the result that was chosen
	 */
	@JsonProperty(JSON_FIELD_RESULT_ID)
	public final String resultId;

	/**
	 * The user that chose the result
	 */
	@JsonProperty(JSON_FIELD_FROM)
	public final User from;

	/**
	 * <em>Optional.</em> Sender location, only for bots that require user
	 * location
	 */
	@JsonProperty(JSON_FIELD_LOCATION)
	public final Location location;

	/**
	 * <em>Optional.</em> Identifier of the sent inline message. Available only if
	 * there is an inline keyboard attached to the message. Will be also received
	 * in callback queries and can be used to edit the message.
	 */
	@JsonProperty(JSON_FIELD_INLINE_MESSAGE_ID)
	public final String inlineMessageId;

	/**
	 * The query that was used to obtain the result
	 */
	@JsonProperty(JSON_FIELD_QUERY)
	public final String query;

	/**
	 * 
	 * @param resultId
	 *          The unique identifier for the result that was chosen
	 * @param from
	 *          The user that chose the result
	 * @param location
	 *          <em>Optional.</em> Sender location, only for bots that require
	 *          user location
	 * @param inlineMessageId
	 *          <em>Optional.</em> Identifier of the sent inline message.
	 *          Available only if there is an inline keyboard attached to the
	 *          message. Will be also received in callback queries and can be used
	 *          to edit the message.
	 * @param query
	 *          The query that was used to obtain the result
	 */
	public ChosenInlineResult(@JsonProperty(JSON_FIELD_RESULT_ID) String resultId,
			@JsonProperty(JSON_FIELD_FROM) User from, @JsonProperty(JSON_FIELD_LOCATION) Location location,
			@JsonProperty(JSON_FIELD_INLINE_MESSAGE_ID) String inlineMessageId,
			@JsonProperty(JSON_FIELD_QUERY) String query) {
		this.resultId = resultId;
		this.from = from;
		this.location = location;
		this.inlineMessageId = inlineMessageId;
		this.query = query;
	}

}
