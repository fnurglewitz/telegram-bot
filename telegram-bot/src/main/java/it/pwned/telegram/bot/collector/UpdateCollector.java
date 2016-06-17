package it.pwned.telegram.bot.collector;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateCollector {

	public Update next(boolean goOn) throws InterruptedException;

}
