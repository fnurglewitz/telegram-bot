package it.pwned.telegram.bot.handler;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateHandlerManager {

	public void setInlineHandler(UpdateHandler handler);
	
	public void initHandlers();

	public void shutdownHandlers();

	public void dispatch(Update u);

}
