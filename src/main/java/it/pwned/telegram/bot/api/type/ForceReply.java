package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Upon receiving a message with this object, Telegram clients will display a
 * reply interface to the user (act as if the user has selected the bot‘s
 * message and tapped ’Reply'). This can be extremely useful if you want to
 * create user-friendly step-by-step interfaces without having to sacrifice
 * privacy mode.
 *
 */
@JsonInclude(Include.NON_NULL)
public final class ForceReply extends AbstractKeyboardMarkup {

	private final static String JSON_FIELD_FORCE_REPLY = "force_reply";
	private final static String JSON_FIELD_SELECTIVE = "selective";

	/**
	 * Shows reply interface to the user, as if they manually selected the bot's
	 * message and tapped 'Reply'
	 */
	@JsonProperty(JSON_FIELD_FORCE_REPLY)
	public final Boolean forceReply = true;

	/**
	 * <em>Optional.</em> Use this parameter if you want to force reply from
	 * specific users only. Targets:<br>
	 * 1) users that are mentioned in the text of the {@link Message}
	 * object;<br>
	 * 2) if the bot's message is a reply (has
	 * {@link Message#replyToMessageId}), sender of the original message.
	 */
	@JsonProperty(JSON_FIELD_SELECTIVE)
	public final Boolean selective;

	/**
	 * {@link ForceReply#ForceReply(Boolean)} with false as default parameter
	 */
	public ForceReply() {
		this(false);
	}

	/**
	 * 
	 * @param selective
	 *            <em>Optional.</em> Use this parameter if you want to force
	 *            reply from specific users only. Targets:<br>
	 *            1) users that are mentioned in the text of the {@link Message}
	 *            object;<br>
	 *            2) if the bot's message is a reply (has
	 *            {@link Message#replyToMessageId}), sender of the original
	 *            message.
	 * 
	 */
	public ForceReply(@JsonProperty(JSON_FIELD_SELECTIVE) Boolean selective) {
		this.selective = selective;
	}

}
