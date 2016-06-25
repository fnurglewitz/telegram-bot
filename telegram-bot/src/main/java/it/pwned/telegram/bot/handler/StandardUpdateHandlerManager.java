package it.pwned.telegram.bot.handler;

//import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.pwned.telegram.bot.api.type.Update;

//import org.springframework.core.annotation.AnnotationAwareOrderComparator;

public class StandardUpdateHandlerManager implements UpdateHandlerManager {

	private static final Logger log = LoggerFactory.getLogger(StandardUpdateHandlerManager.class);

	private final List<UpdateHandler> handlers;
	private UpdateHandler inlineHandler;
	private final Map<UpdateHandler, Thread> handlerThreads;

	public StandardUpdateHandlerManager(List<UpdateHandler> handlers) {
		this.handlers = handlers;

		this.handlerThreads = new ConcurrentHashMap<UpdateHandler, Thread>();

		// Collections.sort(handlers, AnnotationAwareOrderComparator.INSTANCE);
	}

	@Override
	@InlineHandlerQualifier
	@Autowired(required = false)
	public void setInlineHandler(UpdateHandler handler) {
		this.inlineHandler = handler;
	}

	@Override
	public void shutdown() {
		log.info("Shutting down handlers");

		handlerThreads.forEach((h, t) -> {
			log.info(String.format("Sending interrupt signal to handler <%>", h.getName()));
			t.interrupt();
		});

		handlerThreads.forEach((h, t) -> {
			log.info(String.format("Join thread on handler <%>", h.getName()));
			joinThreadAndIgnoreInterrupt(t);
		});

		handlers.forEach(h -> {
			log.info(String.format("Saving state for handler <%>", h.getName()));
			if (h instanceof StatefulUpdateHandler)
				((StatefulUpdateHandler) h).saveState();
		});

	}

	@Override
	public void init() {
		log.info("Initializing handlers");

		handlers.forEach(h -> {
			log.info(String.format("Loading state for handler <%s>", h.getName()));
			if (h instanceof StatefulUpdateHandler)
				((StatefulUpdateHandler) h).loadState();

			if (h.requiresThread()) {
				log.info(String.format("Starting thread for handler <%s>", h.getName()));
				Thread t = new Thread(h.getRunnable(), h.getName());
				t.start();
				handlerThreads.put(h, t);
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
		if (Update.Util.isInline(u)) {
			if (inlineHandler != null) {
				log.trace(String.format("Sending update <%d> to inline handler <%s>", u.updateId, inlineHandler.getName()));
				inlineHandler.submit(u);
			}
		} else {
			for (UpdateHandler h : handlers) {
				if (h == inlineHandler)
					continue;

				log.trace(String.format("Sending update <%d> to handler <%s>", u.updateId, h.getName()));
				if (!h.submit(u))
					return;
			}
		}
	}

}
