package it.pwned.telegram.bot.handler;

import java.util.List;

public interface UpdateHandlerManager {

	public List<UpdateHandler> getHandlers();

	public UpdateHandler getInlineHandler();
	
	public void init();
	
	public void shutdown();
	
}
