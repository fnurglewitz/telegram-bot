package it.pwned.telegram.bot.handler;

public interface StatefulUpdateHandler {
	
	public void loadState();
	public void saveState();

}
