package it.pwned.telegram.bot;

import java.util.Collections;
import java.util.List;

import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import it.pwned.telegram.bot.api.type.Update;

public class StandardUpdateDispatcher implements UpdateDispatcher {

	private final List<UpdateHandler> handlers;
	private final UpdateHandler inline_handler;

	public StandardUpdateDispatcher(List<UpdateHandler> handlers, UpdateHandler inline_handler) {
		this.handlers = handlers;
		this.inline_handler = inline_handler;

		this.handlers.remove(inline_handler);

		Collections.sort(handlers, AnnotationAwareOrderComparator.INSTANCE);

	}

	@Override
	public void dispatch(Update u) {
		if (u.inline_query != null || u.chosen_inline_result != null) {
			if (inline_handler != null)
				inline_handler.submit(u);
		} else {
			boolean forward_update = true;
			for (UpdateHandler h : handlers) {
				if (forward_update)
					forward_update = h.submit(u);
			}
		}

	}

}
