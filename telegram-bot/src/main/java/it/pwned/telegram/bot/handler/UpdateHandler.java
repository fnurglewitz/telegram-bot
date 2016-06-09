package it.pwned.telegram.bot.handler;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateHandler {

	/**
	 * @param Update to process
	 * @return if true the Update will be forwarded to other handlers
	 */
	public boolean submit(Update u);
	
	public boolean requiresThread();
	
	public Runnable getRunnable();
	
	public String getName();
	
	public void loadState();
	public void saveState();

}
