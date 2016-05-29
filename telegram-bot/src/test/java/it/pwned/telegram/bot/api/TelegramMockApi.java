package it.pwned.telegram.bot.api;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.core.io.Resource;

import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.TelegramFile;
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
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendMessage(String chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message forwardMessage(long chat_id, long from_chat_id, Boolean disable_notification, int message_id)
			throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message forwardMessage(String chat_id, String from_chat_id, Boolean disable_notification, int message_id)
			throws TelegramBotApiException {
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
	public Message sendPhoto(long chat_id, Resource photo, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendPhoto(String chat_id, Resource photo, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendAudio(long chat_id, Resource audio, Integer duration, String performer, String title,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendDocument(long chat_id, Resource document, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendDocument(String chat_id, Resource document, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendSticker(long chat_id, Resource sticker, Boolean disable_notification, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendSticker(String chat_id, Resource sticker, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVideo(long chat_id, Resource video, Integer duration, Integer width, Integer height,
			String caption, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVideo(String chat_id, Resource video, Integer duration, Integer width, Integer height,
			String caption, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVoice(long chat_id, Resource voice, Integer duration, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVoice(String chat_id, Resource voice, Integer duration, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVenue(long chat_id, float latitude, float longitude, String title, String address,
			String foursquare_id, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendVenue(String chat_id, float latitude, float longitude, String title, String address,
			String foursquare_id, Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendContact(long chat_id, String phone_number, String first_name, String last_name,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message sendContact(String chat_id, String phone_number, String first_name, String last_name,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TelegramFile getFile(String file_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource getResourceFromTelegramFile(TelegramFile file) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean answerInlineQuery(String inline_query_id, List<InlineQueryResult> results, Integer cache_time,
			Boolean is_personal, String next_offset, String switch_pm_text, String switch_pm_parameter)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean kickChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean kickChatMember(String chat_id, int user_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean unbanChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean unbanChatMember(String chat_id, int user_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean answerCallbackQuery(String callback_query_id, String text, Boolean show_alert) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message editMessageText(long chat_id, Integer message_id, String inline_message_id, String text,
			String parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message editMessageText(String chat_id, Integer message_id, String inline_message_id, String text,
			String parse_mode, Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup)
					throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message editMessageCaption(long chat_id, Integer message_id, String inline_message_id, String caption,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message editMessageCaption(String chat_id, Integer message_id, String inline_message_id, String caption,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message editMessageReplyMarkup(long chat_id, Integer message_id, String inline_message_id,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message editMessageReplyMarkup(String chat_id, Integer message_id, String inline_message_id,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Chat getChat(long chat_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chat getChat(String chat_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatMember[] getChatAdministrators(long chat_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatMember[] getChatAdministrators(String chat_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatMember getChatMember(long chat_id, int user_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChatMember getChatMember(String chat_id, int user_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChatMembersCount(long chat_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getChatMembersCount(String chat_id) throws TelegramBotApiException {
		// TODO Auto-generated method stub
		return 0;
	}

}
