package it.pwned.telegram.bot.authorization;

public class NullAuthorization implements Authorization {

	@Override
	public Boolean isCommandAllowed(String command, Integer user_id, Integer chat_id) {
		return true;
	}

}
