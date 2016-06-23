package it.pwned.telegram.bot.api.type;

public class BooleanOrMessage {

	public final Boolean bool;
	public final Message message;

	public BooleanOrMessage(Boolean bool) {
		this.bool = bool;
		this.message = null;
	}

	public BooleanOrMessage(Message message) {
		this.message = message;
		this.bool = null;
	}

	public boolean isMessage() {
		return message != null;
	}

	public boolean isBoolean() {
		return bool != null;
	}

}
