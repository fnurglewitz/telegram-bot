package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public final class ReplyKeyboardMarkup extends DummyKeyboard {
	public String[][] keyboard;
	public final Boolean resize_keyboard;
	public final Boolean one_time_keyboard;
	public final Boolean selective;

	public ReplyKeyboardMarkup(@JsonProperty("resize_keyboard") Boolean resize_keyboard,
			@JsonProperty("one_time_keyboard") Boolean one_time_keyboard,
			@JsonProperty("selective") Boolean selective) {
		this.resize_keyboard = resize_keyboard;
		this.one_time_keyboard = one_time_keyboard;
		this.selective = selective;
	}

}