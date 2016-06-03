package it.pwned.telegram.bot.handler;

import java.util.List;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateHandlerManager {

	public List<UpdateHandler> getHandlers();

	public UpdateHandler getInlineHandler();
	
	public void init();
	
	public void shutdown();
	
	public void dispatch(Update u);
	
}
