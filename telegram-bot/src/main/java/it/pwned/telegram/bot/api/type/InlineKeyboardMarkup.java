package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class InlineKeyboardMarkup extends DummyKeyboard {
	public final InlineKeyboardButton[][] inline_keyboard;

	public InlineKeyboardMarkup(@JsonProperty("inline_keyboard") InlineKeyboardButton[][] inline_keyboard) {
		this.inline_keyboard = inline_keyboard;
	}
}
