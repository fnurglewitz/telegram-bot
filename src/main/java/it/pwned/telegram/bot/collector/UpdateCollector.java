package it.pwned.telegram.bot.collector;

import java.util.Optional;

import it.pwned.telegram.bot.api.type.Update;

public interface UpdateCollector {

	public Optional<Update> next(boolean goOn) throws InterruptedException;

}
