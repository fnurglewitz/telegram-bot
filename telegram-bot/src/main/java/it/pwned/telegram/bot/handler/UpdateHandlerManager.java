package it.pwned.telegram.bot.handler;

import java.util.List;

public interface UpdateHandlerManager {

	public void restart(UpdateHandler handler);

	public void stop(UpdateHandler handler);

	public void restart(String handler);

	public void stop(String handler);

	public List<UpdateHandler> getHandlers();

	public UpdateHandler getInlineHandler();
}
