package it.pwned.telegram.bot.api.type.game;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

/**
 * Represents a Game.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultGame extends InlineQueryResult {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_GAME_SHORT_NAME = "game_short_name";
	private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";

	/**
	 * Type of the result, must be game
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type = "game";

	/**
	 * Unique identifier for this result, 1-64 Bytes
	 */
	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	/**
	 * Short name of the game
	 */
	@JsonProperty(JSON_FIELD_GAME_SHORT_NAME)
	public final String gameShortName;

	/**
	 * <em>Optional.</em> Inline keyboard attached to the message
	 */
	@JsonProperty(JSON_FIELD_REPLY_MARKUP)
	public final InlineKeyboardMarkup replyMarkup;

	/**
	 * 
	 * @param id
	 *          Unique identifier for this result, 1-64 Bytes
	 * @param gameShortName
	 *          Short name of the game
	 * @param replyMarkup
	 *          <em>Optional.</em> Inline keyboard attached to the message
	 */
	public InlineQueryResultGame(@JsonProperty(JSON_FIELD_ID) String id,
			@JsonProperty(JSON_FIELD_GAME_SHORT_NAME) String gameShortName,
			@JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup) {

		this.id = id;
		this.gameShortName = gameShortName;
		this.replyMarkup = replyMarkup;

	}

}
