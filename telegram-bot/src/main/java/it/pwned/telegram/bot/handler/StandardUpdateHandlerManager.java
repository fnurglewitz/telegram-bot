package it.pwned.telegram.bot.handler;

//import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.pwned.telegram.bot.api.type.Update;

//import org.springframework.core.annotation.AnnotationAwareOrderComparator;

public class StandardUpdateHandlerManager implements UpdateHandlerManager {

	private static final Logger log = LoggerFactory.getLogger(StandardUpdateHandlerManager.class);

	private final List<UpdateHandler> handlers;
	private final UpdateHandler inline_handler;
	private final Map<UpdateHandler, Thread> handler_threads;

	public StandardUpdateHandlerManager(List<UpdateHandler> handlers, UpdateHandler inline_handler) {
		this.handlers = handlers;
		this.inline_handler = inline_handler;

		this.handler_threads = new ConcurrentHashMap<UpdateHandler, Thread>();

		// Collections.sort(handlers, AnnotationAwareOrderComparator.INSTANCE);
	}

	@Override
	public void shutdown() {
		log.info("Shutting down handlers");

		handler_threads.forEach((h, t) -> {
			log.info(String.format("Sending interrupt signal to handler <%>", h.getName()));
			t.interrupt();
		});

		handler_threads.forEach((h, t) -> {
			log.info(String.format("Join thread on handler <%>", h.getName()));
			joinThreadAndIgnoreInterrupt(t);
		});

		handlers.forEach(h -> {
			log.info(String.format("Saving state for handler <%>", h.getName()));
			h.saveState();
		});

	}

	@Override
	public void init() {
		log.info("Initializing handlers");

		handlers.forEach(h -> {
			log.info(String.format("Loading state for handler <%s>", h.getName()));
			h.loadState();

			if (h.requiresThread()) {
				log.info(String.format("Starting thread for handler <%s>", h.getName()));
				Thread t = new Thread(h.getRunnable(), h.getName());
				t.start();
				handler_threads.put(h, t);
			}
		});

	}

	private static void joinThreadAndIgnoreInterrupt(Thread t) {
		// @formatter:off
		try { t.join(); } catch (InterruptedException e) { }
		// @formatter:on
	}

	@Override
	public void dispatch(Update u) {
		if (u.is_inline) {
			if (inline_handler != null) {
				log.trace(String.format("Sending update <%d> to inline handler <%s>", u.update_id, inline_handler.getName()));
				inline_handler.submit(u);
			}
		} else {
			for (UpdateHandler h : handlers) {
				if (h == inline_handler)
					continue;

				log.trace(String.format("Sending update <%d> to handler <%s>", u.update_id, h.getName()));
				if (!h.submit(u))
					return;
			}
		}
	}

}
