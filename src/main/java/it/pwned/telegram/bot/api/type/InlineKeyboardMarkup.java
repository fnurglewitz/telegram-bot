package it.pwned.telegram.bot.api.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents an inline keyboard that appears right next to the
 * message it belongs to.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineKeyboardMarkup extends AbstractKeyboardMarkup {

	private final static String JSON_FIELD_INLINE_KEYBOARD = "inline_keyboard";

	/**
	 * Array of button rows, each represented by an Array of
	 * {@link InlineKeyboardButton} objects
	 */
	@JsonProperty(JSON_FIELD_INLINE_KEYBOARD)
	public final List<List<InlineKeyboardButton>> inlineKeyboard;

	/**
	 * Array of button rows, each represented by an Array of
	 * {@link InlineKeyboardButton} objects
	 * 
	 * @param inlineKeyboard
	 */
	public InlineKeyboardMarkup(
			@JsonProperty(JSON_FIELD_INLINE_KEYBOARD) List<List<InlineKeyboardButton>> inlineKeyboard) {
		this.inlineKeyboard = inlineKeyboard;
	}

	/**
	 * Utility class used to help building keyboards
	 *
	 */
	public static class Builder {

		private List<List<InlineKeyboardButton>> keyboard;
		int rows = 0;

		public Builder() {
			keyboard = new ArrayList<List<InlineKeyboardButton>>();
		}

		public InlineKeyboardMarkup build() {

			List<List<InlineKeyboardButton>> tmp = new ArrayList<List<InlineKeyboardButton>>();

			for (List<InlineKeyboardButton> l : keyboard)
				tmp.add(Collections.unmodifiableList(l));

			return new InlineKeyboardMarkup(Collections.unmodifiableList(tmp));
		}

		public void loadKeyboardFromButtonList(List<InlineKeyboardButton> buttons, int buttonsPerRow) {
			keyboard = AbstractKeyboardMarkup.createKeyboardFromButtonList(buttons, buttonsPerRow);
		}

		/**
		 * 
		 * @return Returns the added row index
		 */
		public int addRow() {
			keyboard.add(new ArrayList<InlineKeyboardButton>());
			return ++rows;
		}

		/**
		 * Deletes the row with the specified index
		 * 
		 * @param index
		 *          row index
		 */
		public void deleteRow(int idx) {
			keyboard.remove(idx);
		}

		/**
		 * Adds a InlineKeyboardButton to a specific row
		 * 
		 * @param btn
		 *          The button to add
		 * @param idx
		 *          Index of the row
		 */
		public void addButton(InlineKeyboardButton btn, int idx) {
			keyboard.get(idx).add(btn);
		}

	}

}
