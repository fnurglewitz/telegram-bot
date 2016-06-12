package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents one special entity in a text message. For example,
 * hashtags, usernames, URLs, etc.
 *
 */
public class MessageEntity {

	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_OFFSET = "offset";
	private final static String JSON_FIELD_LENGTH = "length";
	private final static String JSON_FIELD_URL = "url";
	private final static String JSON_FIELD_USER = "user";

	/**
	 * Type of the entity. Can be mention (@username), hashtag, bot_command, url,
	 * email, bold (bold text), italic (italic text), code (monowidth string), pre
	 * (monowidth block), text_link (for clickable text URLs), text_mention (for
	 * users without usernames)
	 */
	@JsonProperty(JSON_FIELD_TYPE)
	public final String type;

	/**
	 * Offset in UTF-16 code units to the start of the entity
	 */
	@JsonProperty(JSON_FIELD_OFFSET)
	public final Integer offset;

	/**
	 * Length of the entity in UTF-16 code units
	 */
	@JsonProperty(JSON_FIELD_LENGTH)
	public final Integer length;

	/**
	 * <em>Optional.</em> For “text_link” only, url that will be opened after user
	 * taps on the text
	 */
	@JsonProperty(JSON_FIELD_URL)
	public final String url;

	/**
	 * <em>Optional.</em> For “text_mention” only, the mentioned user
	 */
	@JsonProperty(JSON_FIELD_USER)
	public final User user;

	/**
	 * 
	 * @param type
	 *          Type of the entity. Can be mention (@username), hashtag,
	 *          bot_command, url, email, bold (bold text), italic (italic text),
	 *          code (monowidth string), pre (monowidth block), text_link (for
	 *          clickable text URLs), text_mention (for users without usernames)
	 * @param offset
	 *          Offset in UTF-16 code units to the start of the entity
	 * @param length
	 *          Length of the entity in UTF-16 code units
	 * @param url
	 *          <em>Optional.</em> For “text_link” only, url that will be opened
	 *          after user taps on the text
	 * @param user
	 *          <em>Optional.</em> For “text_mention” only, the mentioned user
	 */
	public MessageEntity(@JsonProperty(JSON_FIELD_TYPE) String type, @JsonProperty(JSON_FIELD_OFFSET) Integer offset,
			@JsonProperty(JSON_FIELD_LENGTH) Integer length, @JsonProperty(JSON_FIELD_URL) String url,
			@JsonProperty(JSON_FIELD_USER) User user) {
		this.type = type;
		this.offset = offset;
		this.length = length;
		this.url = url;
		this.user = user;
	}

}
