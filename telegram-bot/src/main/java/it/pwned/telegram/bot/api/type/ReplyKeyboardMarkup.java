package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ReplyKeyboardMarkup implements DummyKeyboard {
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

	@Override
	public String toJsonString() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException ex) {
			return null;
		}
	}
}