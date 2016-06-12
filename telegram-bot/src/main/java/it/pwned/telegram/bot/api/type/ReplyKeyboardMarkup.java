package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a custom keyboard with reply options (see Introduction
 * to bots for details and examples).
 *
 */
@JsonInclude(Include.NON_NULL)
public final class ReplyKeyboardMarkup extends DummyKeyboard {

	private final static String JSON_FIELD_KEYBOARD = "keyboard";
	private final static String JSON_FIELD_RESIZE_KEYBOARD = "resize_keyboard";
	private final static String JSON_FIELD_ONE_TIME_KEYBOARD = "one_time_keyboard";
	private final static String JSON_FIELD_SELECTIVE = "selective";

	/**
	 * Array of button rows, each represented by an Array of
	 * {@link KeyboardButton} objects
	 */
	@JsonProperty(JSON_FIELD_KEYBOARD)
	public final KeyboardButton[][] keyboard;

	/**
	 * <em>Optional.</em> Requests clients to resize the keyboard vertically for
	 * optimal fit (e.g., make the keyboard smaller if there are just two rows of
	 * buttons). Defaults to false, in which case the custom keyboard is always of
	 * the same height as the app's standard keyboard.
	 */
	@JsonProperty(JSON_FIELD_RESIZE_KEYBOARD)
	public final Boolean resizeKeyboard;

	/**
	 * <em>Optional.</em> Requests clients to hide the keyboard as soon as it's
	 * been used. The keyboard will still be available, but clients will
	 * automatically display the usual letter-keyboard in the chat – the user can
	 * press a special button in the input field to see the custom keyboard again.
	 * Defaults to false.
	 */
	@JsonProperty(JSON_FIELD_ONE_TIME_KEYBOARD)
	public final Boolean oneTimeKeyboard;

	/**
	 * <em>Optional.</em> Use this parameter if you want to show the keyboard to
	 * specific users only. Targets: <br>
	 * 1) users that are @mentioned in the text of the Message object; <br>
	 * 2) if the bot's message is a reply (has {@link Message#replyToMessageId}),
	 * sender of the original message. <br>
	 * <b>Example:</b> A user requests to change the bot‘s language, bot replies
	 * to the request with a keyboard to select the new language. Other users in
	 * the group don’t see the keyboard.
	 */
	@JsonProperty(JSON_FIELD_SELECTIVE)
	public final Boolean selective;

	/**
	 * 
	 * @param keyboard
	 *          Array of button rows, each represented by an Array of
	 *          {@link KeyboardButton} objects
	 * @param resizeKeyboard
	 *          <em>Optional.</em> Requests clients to resize the keyboard
	 *          vertically for optimal fit (e.g., make the keyboard smaller if
	 *          there are just two rows of buttons). Defaults to false, in which
	 *          case the custom keyboard is always of the same height as the app's
	 *          standard keyboard.
	 * @param oneTimeKeyboard
	 *          <em>Optional.</em> Requests clients to hide the keyboard as soon
	 *          as it's been used. The keyboard will still be available, but
	 *          clients will automatically display the usual letter-keyboard in
	 *          the chat – the user can press a special button in the input field
	 *          to see the custom keyboard again. Defaults to false.
	 * @param selective
	 *          <em>Optional.</em> Use this parameter if you want to show the
	 *          keyboard to specific users only. Targets: <br>
	 *          1) users that are @mentioned in the text of the Message object;
	 *          <br>
	 *          2) if the bot's message is a reply (has
	 *          {@link Message#replyToMessageId}), sender of the original message.
	 *          <br>
	 *          <b>Example:</b> A user requests to change the bot‘s language, bot
	 *          replies to the request with a keyboard to select the new language.
	 *          Other users in the group don’t see the keyboard.
	 */
	public ReplyKeyboardMarkup(@JsonProperty(JSON_FIELD_KEYBOARD) KeyboardButton[][] keyboard,
			@JsonProperty(JSON_FIELD_RESIZE_KEYBOARD) Boolean resizeKeyboard,
			@JsonProperty(JSON_FIELD_ONE_TIME_KEYBOARD) Boolean oneTimeKeyboard,
			@JsonProperty(JSON_FIELD_SELECTIVE) Boolean selective) {
		this.keyboard = keyboard;
		this.resizeKeyboard = resizeKeyboard;
		this.oneTimeKeyboard = oneTimeKeyboard;
		this.selective = selective;
	}

	/**
	 * Utility class used to help building keyboards
	 *
	 */
	public static class Builder {

		private Boolean resizeKeyboard;
		private Boolean oneTimeKeyboard;
		private Boolean selective;

		public Builder() {

		}

		public ReplyKeyboardMarkup build() {
			KeyboardButton[][] keyboard = null;

			// TODO: build keyboard here

			return new ReplyKeyboardMarkup(keyboard, resizeKeyboard, oneTimeKeyboard, selective);
		}

		public Builder setResizeKeyboard(Boolean resizeKeyboard) {
			this.resizeKeyboard = resizeKeyboard;
			return this;
		}

		public Builder setOneTimeKeyboard(Boolean oneTimeKeyboard) {
			this.oneTimeKeyboard = oneTimeKeyboard;
			return this;
		}

		public Builder setSelective(Boolean selective) {
			this.selective = selective;
			return this;
		}

		// TODO: keyboard building (add rows, add buttons)

		/**
		 * 
		 * @return Returns the added row index
		 */
		public int addRow() {
			return 0;
		}

		/**
		 * Deletes the row with the specified index
		 * 
		 * @param index
		 *          row index
		 */
		public void deleteRow(int idx) {

		}

		/**
		 * Adds a KeyboardButton to a specific row
		 * 
		 * @param btn
		 *          The button to add
		 * @param idx
		 *          Index of the row
		 */
		public void addButton(KeyboardButton btn, int idx) {

		}

	}

}
