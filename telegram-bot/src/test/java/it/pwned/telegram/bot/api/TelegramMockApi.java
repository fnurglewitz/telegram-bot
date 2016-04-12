package it.pwned.telegram.bot.api;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.core.io.Resource;

import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.File;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

public class TelegramMockApi implements TelegramBotApi {
	
	public static class MockApiAction {
		public final String action;
		public final Object[] params;
		
		public MockApiAction(String action, Object[] params) {
			this.action = action;
			this.params = params;
		}
	}
	
	private final BlockingQueue<MockApiAction> actions_log;

	public TelegramMockApi() {
		this.actions_log = new LinkedBlockingQueue<MockApiAction>();
	}
	
	private void addToQueue(MockApiAction a) {
		try {
			this.actions_log.put(a);
		} catch (InterruptedException e) {
		}
	}
	
	public MockApiAction next() throws InterruptedException {
		return this.actions_log.take();
	}

	@Override
	public User getMe() throws TelegramBotApiException {
		addToQueue(new MockApiAction("getMe", null));
		return null;
	}

	@Override
	public Update[] getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException {
		addToQueue(new MockApiAction("getMe", new Object[] { offset, limit, timeout }));
		return null;
	}

	@Override
	public Message sendMessage(long chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendMessage(String chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message forwardMessage(long chat_id, long from_chat_id, int message_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message forwardMessage(String chat_id, String from_chat_id, int message_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendLocation(long chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendLocation(String chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendChatAction(long chat_id, ChatAction action) throws TelegramBotApiException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendChatAction(String chat_id, ChatAction action) throws TelegramBotApiException {
		// TODO Auto-generated method stub

	}

	@Override
	public UserProfilePhotos getUserProfilePhotos(int user_id, Integer offset, Integer limit)
			throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendPhoto(long chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendPhoto(String chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendAudio(long chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendDocument(long chat_id, Resource document, Integer reply_to_message_id, DummyKeyboard reply_markup)
			throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendDocument(String chat_id, Resource document, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendSticker(long chat_id, Resource sticker, Integer reply_to_message_id, DummyKeyboard reply_markup)
			throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendSticker(String chat_id, Resource sticker, Integer reply_to_message_id, DummyKeyboard reply_markup)
			throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVideo(long chat_id, Resource video, Integer duration, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVideo(String chat_id, Resource video, Integer duration, String caption,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVoice(long chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVoice(String chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File getFile(String file_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource getResourceFromTelegramFile(File file) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean answerInlineQuery(String inline_query_id, List<InlineQueryResult> results, Integer cache_time,
			Boolean is_personal, String next_offset) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

}
