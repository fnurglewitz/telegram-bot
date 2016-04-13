package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public final class ForceReply extends DummyKeyboard {
	public final Boolean force_reply = true;
	public final Boolean selective;

	public ForceReply() {
		this.selective = false;
	}

	public ForceReply(@JsonProperty("selective") Boolean selective) {
		this.selective = selective;
	}

}
