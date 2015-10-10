package it.pwned.telegram.bot.authorization;

public interface Authorization {
	
	public Boolean isCommandAllowed(String command, Integer user_id, Integer chat_id);

}
