package it.pwned.telegram.bot.handler;

//import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//import org.springframework.core.annotation.AnnotationAwareOrderComparator;

public class StandardUpdateHandlerManager implements UpdateHandlerManager {

	private final List<UpdateHandler> handlers;
	private final UpdateHandler inline_handler;
	private final Map<UpdateHandler, Thread> handler_threads;

	public StandardUpdateHandlerManager(List<UpdateHandler> handlers, UpdateHandler inline_handler) {
		this.handlers = handlers;
		this.inline_handler = inline_handler;

		this.handlers.remove(inline_handler);

		this.handler_threads = new ConcurrentHashMap<UpdateHandler, Thread>();

		// it seems that spring already does this automatically, I should check on
		// the doc
		// Collections.sort(handlers, AnnotationAwareOrderComparator.INSTANCE);

		handlers.forEach(h -> {
			if (h.requiresThread()) {
				Thread t = new Thread(h.getRunnable(), h.getClass().getSimpleName());
				t.start();
				handler_threads.put(h, t);
			}
		});
	}

	@Override
	public void restart(UpdateHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop(UpdateHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart(String handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop(String handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UpdateHandler> getHandlers() {
		return this.handlers;
	}

	@Override
	public UpdateHandler getInlineHandler() {
		return this.inline_handler;
	}

}
