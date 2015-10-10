package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ForceReply implements DummyKeyboard {
	public final Boolean force_reply = true;
	public final Boolean selective;

	public ForceReply() {
		this.selective = false;
	}

	public ForceReply(@JsonProperty("selective") Boolean selective) {
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