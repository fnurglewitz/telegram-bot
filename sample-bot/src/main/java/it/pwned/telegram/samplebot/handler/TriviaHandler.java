package it.pwned.telegram.samplebot.handler;

import java.util.concurrent.BlockingQueue;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.UpdateHandler;

public class TriviaHandler implements UpdateHandler, Runnable {

	public TriviaHandler(TelegramBotApi api, BlockingQueue<Update> updateQueue, ThreadPoolTaskExecutor executor) {
		
	}
	
	@Override
	public boolean submit(Update u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean requiresThread() {
		return true;
	}

	@Override
	public Runnable getRunnable() {
		return this;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
