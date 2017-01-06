package it.pwned.telegram.bot.handler;

public interface StatefulUpdateHandler extends UpdateHandler {
	
	public void loadState();
	public void saveState();

}
