package it.pwned.telegram.bot.dispatcher;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateDispatcher {

	public void dispatch(Update u);
	
}
