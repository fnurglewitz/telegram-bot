package it.pwned.telegram.bot;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateHandler {

	/**
	 * @param Update to process
	 * @return if true the Update will be forwarded to other handlers
	 */
	public boolean submit(Update u);

}
