package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InlineKeyboardMarkup {
	public final InlineKeyboardButton[][] inline_keyboard;

	public InlineKeyboardMarkup(@JsonProperty("inline_keyboard") InlineKeyboardButton[][] inline_keyboard) {
		this.inline_keyboard = inline_keyboard;
	}
}
