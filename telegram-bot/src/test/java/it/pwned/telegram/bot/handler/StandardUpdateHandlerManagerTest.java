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
		
		UpdateHandler inline_handler = new UpdateHandler() {
			
			@Override
			public boolean submit(Update u) {
				counter.set(counter.intValue()+1);
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

			@Override
			public void loadState() {
			}

			@Override
			public void saveState() {
			}
			
		};
		
		List<UpdateHandler> handlers = new LinkedList<UpdateHandler>();
		handlers.add(inline_handler);
		
		StandardUpdateHandlerManager m = new StandardUpdateHandlerManager(handlers);
		m.setInlineHandler(inline_handler);
		
		Update inline_update = new Update.Builder().setInlineQuery(new InlineQuery(null, null, null, null, null)).build();
		Update inline_update2 = new Update.Builder().setChosenInlineResult(new ChosenInlineResult(null, null, null, null, null)).build();
		
		Update not_inline_update = new Update.Builder().build();
		
		m.dispatch(inline_update);
		m.dispatch(inline_update2);
		m.dispatch(not_inline_update);
		
		assertEquals(2, counter.intValue());
		
	}
	
	/**
	 * Asserts that non-inline updates are broadcasted to all the non-inline handlers
	 */
	@Test
	public void dispatchTest() {
	
		AtomicInteger counter = new AtomicInteger(0);
		
		UpdateHandler inline_handler = new UpdateHandler() {
			
			@Override
			public boolean submit(Update u) {
				counter.set(counter.intValue()+1);
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

			@Override
			public void loadState() {
			}

			@Override
			public void saveState() {
			}
			
		};

		UpdateHandler handler1 = new UpdateHandler() {
			
			@Override
			public boolean submit(Update u) {
				counter.set(counter.intValue()+1);
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

			@Override
			public void loadState() {
			}

			@Override
			public void saveState() {
			}
			
		};
		
		UpdateHandler handler2 = new UpdateHandler() {
			
			@Override
			public boolean submit(Update u) {
				counter.set(counter.intValue()+1);
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

			@Override
			public void loadState() {
			}

			@Override
			public void saveState() {
			}
			
		};

		
		List<UpdateHandler> handlers = new LinkedList<UpdateHandler>();
		handlers.add(inline_handler);
		handlers.add(handler1);
		handlers.add(handler2);
		
		StandardUpdateHandlerManager m = new StandardUpdateHandlerManager(handlers);
		m.setInlineHandler(inline_handler);
		
		Update not_inline_update = new Update.Builder().build();
		
		m.dispatch(not_inline_update);
		m.dispatch(not_inline_update);
		
		assertEquals(4, counter.intValue());
		
	}
	
	
	
	
}
