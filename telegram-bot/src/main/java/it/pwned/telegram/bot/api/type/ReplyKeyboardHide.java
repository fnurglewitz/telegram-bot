package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Upon receiving a message with this object, Telegram clients will hide the
 * current custom keyboard and display the default letter-keyboard. By default,
 * custom keyboards are displayed until a new keyboard is sent by a bot. An
 * exception is made for one-time keyboards that are hidden immediately after
 * the user presses a button (see {@link ReplyKeyboardMarkup}).
 *
 */
@JsonInclude(Include.NON_NULL)
public final class ReplyKeyboardHide extends DummyKeyboard {

	private final static String JSON_FIELD_HIDE_KEYBOARD = "hide_keyboard";
	private final static String JSON_FIELD_SELECTIVE = "selective";

	/**
	 * Requests clients to hide the custom keyboard
	 */
	@JsonProperty(JSON_FIELD_HIDE_KEYBOARD)
	public final Boolean hideKeyboard = true;

	/**
	 * <em>Optional.</em> Use this parameter if you want to hide keyboard for
	 * specific users only. Targets: <br>
	 * 1) users that are @mentioned in the text of the Message object; <br>
	 * 2) if the bot's message is a reply (has {@link Message#replyToMessageId}),
	 * sender of the original message. <br>
	 * <b>Example:</b> A user votes in a poll, bot returns confirmation message in
	 * reply to the vote and hides keyboard for that user, while still showing the
	 * keyboard with poll options to users who haven't voted yet
	 */
	@JsonProperty(JSON_FIELD_SELECTIVE)
	public final Boolean selective;

	/**
	 * 
	 * @param selective
	 *          <em>Optional.</em> Use this parameter if you want to hide keyboard
	 *          for specific users only. Targets: <br>
	 *          1) users that are @mentioned in the text of the Message object;
	 *          <br>
	 *          2) if the bot's message is a reply (has
	 *          {@link Message#replyToMessageId}), sender of the original message.
	 *          <br>
	 *          <b>Example:</b> A user votes in a poll, bot returns confirmation
	 *          message in reply to the vote and hides keyboard for that user,
	 *          while still showing the keyboard with poll options to users who
	 *          haven't voted yet
	 */
	public ReplyKeyboardHide(@JsonProperty(JSON_FIELD_SELECTIVE) Boolean selective) {
		this.selective = selective;
	}

	/**
	 * {@link ReplyKeyboardHide#ReplyKeyboardHide(Boolean)} with false as default
	 * parameter
	 */
	public ReplyKeyboardHide() {
		this(false);
	}

}
