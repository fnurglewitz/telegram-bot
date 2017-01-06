package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.Location;
import it.pwned.telegram.bot.api.type.User;

/**
 * This object represents an incoming inline query. When the user sends an empty
 * query, your bot could return some default or trending results.
 *
 */
public class InlineQuery {

	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_FROM = "from";
	private final static String JSON_FIELD_LOCATION = "location";
	private final static String JSON_FIELD_QUERY = "query";
	private final static String JSON_FIELD_OFFSET = "offset";

	/**
	 * Unique identifier for this query
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * Sender
	 */
	@JsonProperty(JSON_FIELD_FROM)
	public final User from;

	/**
	 * <em>Optional.</em> Sender location, only for bots that request user
	 * location
	 */
	@JsonProperty(JSON_FIELD_LOCATION)
	public final Location location;

	/**
	 * Text of the query (up to 512 characters)
	 */
	@JsonProperty(JSON_FIELD_QUERY)
	public final String query;

	/**
	 * Offset of the results to be returned, can be controlled by the bot
	 */
	@JsonProperty(JSON_FIELD_OFFSET)
	public final String offset;

	/**
	 * 
	 * @param id
	 *          Unique identifier for this query
	 * @param from
	 *          Sender
	 * @param location
	 *          <em>Optional.</em> Sender location, only for bots that request
	 *          user location
	 * @param query
	 *          Text of the query (up to 512 characters)
	 * @param offset
	 *          Offset of the results to be returned, can be controlled by the bot
	 */
	public InlineQuery(@JsonProperty(JSON_FIELD_ID) String id, @JsonProperty(JSON_FIELD_FROM) User from,
			@JsonProperty(JSON_FIELD_LOCATION) Location location, @JsonProperty(JSON_FIELD_QUERY) String query,
			@JsonProperty(JSON_FIELD_OFFSET) String offset) {
		this.id = id;
		this.from = from;
		this.location = location;
		this.query = query;
		this.offset = offset;
	}

}
