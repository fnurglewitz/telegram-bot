package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public final class ReplyKeyboardHide extends DummyKeyboard {
	public final Boolean hide_keyboard = true;
	public final Boolean selective;

	public ReplyKeyboardHide(@JsonProperty("selective") Boolean selective) {
		this.selective = selective;
	}

}
