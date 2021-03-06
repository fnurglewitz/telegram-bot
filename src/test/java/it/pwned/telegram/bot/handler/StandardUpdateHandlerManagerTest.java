package it.pwned.telegram.bot.handler;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.inline.ChosenInlineResult;
import it.pwned.telegram.bot.api.type.inline.InlineQuery;

public class StandardUpdateHandlerManagerTest {

	/**
	 * Asserts that non-inline updates are not sent to the inline handler
	 */
	@Test
	public void inlineDispatchTest() {

		AtomicInteger counter = new AtomicInteger(0);

		UpdateHandler inlineHandler = new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				counter.incrementAndGet();
				return true;
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public String getName() {
				return "Inc";
			}

		};

		List<UpdateHandler> handlers = new LinkedList<UpdateHandler>();
		handlers.add(inlineHandler);

		StandardUpdateHandlerManager m = new StandardUpdateHandlerManager(handlers);
		m.setInlineHandler(inlineHandler);

		Update inlineUpdate = new Update.Builder().setInlineQuery(new InlineQuery(null, null, null, null, null)).build();
		Update inlineUpdate2 = new Update.Builder()
				.setChosenInlineResult(new ChosenInlineResult(null, null, null, null, null)).build();

		Update notInlineUpdate = new Update.Builder().build();

		m.dispatch(inlineUpdate);
		m.dispatch(inlineUpdate2);
		m.dispatch(notInlineUpdate);

		assertEquals(2, counter.intValue());

	}

	/**
	 * Asserts that non-inline updates are broadcasted to all the non-inline
	 * handlers
	 */
	@Test
	public void dispatchTest() {

		AtomicInteger counter = new AtomicInteger(0);

		UpdateHandler inlineHandler = new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				counter.incrementAndGet();
				return true;
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public String getName() {
				return "Inc";
			}

		};

		UpdateHandler handler1 = new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				counter.incrementAndGet();
				return true;
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public String getName() {
				return "h1";
			}

		};

		UpdateHandler handler2 = new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				counter.incrementAndGet();
				return true;
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public String getName() {
				return "h2";
			}

		};

		List<UpdateHandler> handlers = new LinkedList<UpdateHandler>();
		handlers.add(inlineHandler);
		handlers.add(handler1);
		handlers.add(handler2);

		StandardUpdateHandlerManager m = new StandardUpdateHandlerManager(handlers);
		m.setInlineHandler(inlineHandler);

		Update notInlineUpdate = new Update.Builder().build();

		m.dispatch(notInlineUpdate);
		m.dispatch(notInlineUpdate);

		assertEquals(4, counter.intValue());

	}

	/**
	 * Asserts that broadcast is stopped upon false returned by
	 * UpdateHandler.submit(Update)
	 */
	@Test
	public void voidStopPropagationTest() {

		AtomicInteger counter = new AtomicInteger(0);

		UpdateHandler handler1 = new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				counter.incrementAndGet();
				return true; // continue broadcasting
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public String getName() {
				return null;
			}

		};

		UpdateHandler handler2 = new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				counter.incrementAndGet();
				return false; // stop broadcasting
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public String getName() {
				return null;
			}

		};

		UpdateHandler handler3 = new UpdateHandler() {

			@Override
			public boolean submit(Update u) {
				counter.incrementAndGet();
				return true; // continue broadcasting
			}

			@Override
			public boolean requiresThread() {
				return false;
			}

			@Override
			public Runnable getRunnable() {
				return null;
			}

			@Override
			public String getName() {
				return null;
			}
		};

		List<UpdateHandler> handlers = new LinkedList<UpdateHandler>();
		handlers.add(handler1);
		handlers.add(handler2);
		handlers.add(handler3);

		StandardUpdateHandlerManager m = new StandardUpdateHandlerManager(handlers);

		Update notInlineUpdate = new Update.Builder().build();

		m.dispatch(notInlineUpdate);

		assertEquals(2, counter.intValue());

	}
}
