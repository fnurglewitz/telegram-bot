package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents an incoming callback query from a callback button in an
 * inline keyboard. If the button that originated the query was attached to a
 * message sent by the bot, the field {@link CallbackQuery#message} will be
 * presented. If the button was attached to a message sent via the bot (in
 * inline mode), the field {@link CallbackQuery#inlineMessageId} will be
 * presented.
 *
 */
public final class CallbackQuery {

	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_FROM = "from";
	private final static String JSON_FIELD_MESSAGE = "message";
	private final static String JSON_FIELD_INLINE_MESSAGE_ID = "inline_message_id";
	private final static String JSON_FIELD_DATA = "data";

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
	 * <em>Optional.</em> Message with the callback button that originated the
	 * query. Note that message content and message date will not be available
	 * if the message is too old
	 */
	@JsonProperty(JSON_FIELD_MESSAGE)
	public final Message message;

	/**
	 * <em>Optional.</em> Identifier of the message sent via the bot in inline
	 * mode, that originated the query
	 */
	@JsonProperty(JSON_FIELD_INLINE_MESSAGE_ID)
	public final String inlineMessageId;

	/**
	 * Data associated with the callback button. Be aware that a bad client can
	 * send arbitrary data in this field
	 */
	@JsonProperty(JSON_FIELD_DATA)
	public final String data;

	/**
	 * 
	 * @param id
	 *            Unique identifier for this query
	 * @param from
	 *            Sender
	 * @param message
	 *            <em>Optional.</em> Message with the callback button that
	 *            originated the query. Note that message content and message
	 *            date will not be available if the message is too old
	 * @param inlineMessageId
	 *            <em>Optional.</em> Identifier of the message sent via the bot
	 *            in inline mode, that originated the query
	 * @param data
	 *            Data associated with the callback button. Be aware that a bad
	 *            client can send arbitrary data in this field
	 */
	public CallbackQuery(@JsonProperty(JSON_FIELD_ID) String id, @JsonProperty(JSON_FIELD_FROM) User from,
			@JsonProperty(JSON_FIELD_MESSAGE) Message message,
			@JsonProperty(JSON_FIELD_INLINE_MESSAGE_ID) String inlineMessageId,
			@JsonProperty(JSON_FIELD_DATA) String data) {

		this.id = id;
		this.from = from;
		this.message = message;
		this.inlineMessageId = inlineMessageId;
		this.data = data;

	}

}
