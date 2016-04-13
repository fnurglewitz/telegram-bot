package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InlineKeyboardMarkup implements DummyKeyboard {
	public final InlineKeyboardButton[][] inline_keyboard;

	public InlineKeyboardMarkup(@JsonProperty("inline_keyboard") InlineKeyboardButton[][] inline_keyboard) {
		this.inline_keyboard = inline_keyboard;
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
