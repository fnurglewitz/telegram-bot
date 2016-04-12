package it.pwned.telegram.bot;

import java.util.List;

import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.UpdateHandler;
import it.pwned.telegram.bot.handler.UpdateHandlerManager;

public class StandardUpdateDispatcher implements UpdateDispatcher {

	private final List<UpdateHandler> handlers;
	private final UpdateHandler inline_handler;

	public StandardUpdateDispatcher(UpdateHandlerManager manager) {
		this.handlers = manager.getHandlers();
		this.inline_handler = manager.getInlineHandler();
	}

	@Override
	public void dispatch(Update u) {
		if (u.inline_query != null || u.chosen_inline_result != null) {
			if (inline_handler != null)
				inline_handler.submit(u);
		} else {
			for (UpdateHandler h : handlers) {
				if (!h.submit(u))
					return;
			}
		}

	}

}
