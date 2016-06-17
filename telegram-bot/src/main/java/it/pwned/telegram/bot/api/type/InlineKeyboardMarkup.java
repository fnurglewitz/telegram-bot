package it.pwned.telegram.bot.api.type;

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
}
