package it.pwned.telegram.bot;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateCollector {

	public Update next() throws InterruptedException;

}
