package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CallbackQuery {

	private final static String JSON_FIELD_ID = "id";
	private final static String JSON_FIELD_FROM = "from";
	private final static String JSON_FIELD_MESSAGE = "message";
	private final static String JSON_FIELD_INLINE_MESSAGE_ID = "inline_message_id";
	private final static String JSON_FIELD_DATA = "data";

	@JsonProperty(JSON_FIELD_ID)
	public final String id;

	@JsonProperty(JSON_FIELD_FROM)
	public final User from;

	@JsonProperty(JSON_FIELD_MESSAGE)
	public final Message message;

	@JsonProperty(JSON_FIELD_INLINE_MESSAGE_ID)
	public final String inlineMessageId;

	@JsonProperty(JSON_FIELD_DATA)
	public final String data;

	public CallbackQuery(@JsonProperty(JSON_FIELD_ID) String id, @JsonProperty(JSON_FIELD_FROM) User from,
			@JsonProperty(JSON_FIELD_MESSAGE) Message message,
			@JsonProperty(JSON_FIELD_INLINE_MESSAGE_ID) String inlineMessageId, @JsonProperty(JSON_FIELD_DATA) String data) {

		this.id = id;
		this.from = from;
		this.message = message;
		this.inlineMessageId = inlineMessageId;
		this.data = data;

	}

}
