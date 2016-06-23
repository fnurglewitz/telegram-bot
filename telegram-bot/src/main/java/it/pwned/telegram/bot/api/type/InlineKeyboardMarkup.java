package it.pwned.telegram.bot.api.type;

import java.util.ArrayList;

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
	public final InlineKeyboardButton[][] inlineKeyboard;

	/**
	 * Array of button rows, each represented by an Array of
	 * {@link InlineKeyboardButton} objects
	 * 
	 * @param inlineKeyboard
	 */
	public InlineKeyboardMarkup(@JsonProperty(JSON_FIELD_INLINE_KEYBOARD) InlineKeyboardButton[][] inlineKeyboard) {
		this.inlineKeyboard = inlineKeyboard;
	}

	/**
	 * Utility class used to help building keyboards
	 *
	 */
	public static class Builder {

		private ArrayList<ArrayList<InlineKeyboardButton>> keyboard;
		int rows = 0;

		public Builder() {
			keyboard = new ArrayList<ArrayList<InlineKeyboardButton>>();
		}

		public InlineKeyboardMarkup build() {

			// thank you StackOverflow
			final int listSize = keyboard.size();
			InlineKeyboardButton[][] out = new InlineKeyboardButton[listSize][];
			for (int i = 0; i < listSize; i++) {
				ArrayList<InlineKeyboardButton> sublist = keyboard.get(i);
				final int sublistSize = sublist.size();
				out[i] = new InlineKeyboardButton[sublistSize];
				for (int j = 0; j < sublistSize; j++) {
					out[i][j] = sublist.get(j);
				}
			}

			return new InlineKeyboardMarkup(out);
		}

		/**
		 * 
		 * @return Returns the added row index
		 */
		public int addRow() {
			keyboard.add(new ArrayList<InlineKeyboardButton>());
			return rows++;
		}

		/**
		 * Deletes the row with the specified index
		 * 
		 * @param index
		 *            row index
		 */
		public void deleteRow(int idx) {
			keyboard.remove(idx);
		}

		/**
		 * Adds a InlineKeyboardButton to a specific row
		 * 
		 * @param btn
		 *            The button to add
		 * @param idx
		 *            Index of the row
		 */
		public void addButton(InlineKeyboardButton btn, int idx) {
			keyboard.get(idx).add(btn);
		}

	}

}
