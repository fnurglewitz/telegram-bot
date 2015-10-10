package it.pwned.telegram.bot.api;

import org.springframework.core.io.Resource;

import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.File;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.Response;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;

public interface TelegramBotApi {

	Response<User> getMe();

	/**
	 * 
	 * @param offset
	 *            Identifier of the first update to be returned. Must be greater
	 *            by one than the highest among the identifiers of previously
	 *            received updates. By default, updates starting with the
	 *            earliest unconfirmed update are returned. An update is
	 *            considered confirmed as soon as getUpdates is called with an
	 *            offset higher than its update_id.
	 * @param limit
	 *            Limits the number of updates to be retrieved. Values between
	 *            1-100 are accepted. Defaults to 100
	 * @param timeout
	 *            Timeout in seconds for long polling. Defaults to 0, i.e. usual
	 *            short polling
	 * @return
	 */
	Response<Update[]> getUpdates(Integer offset, Integer limit, Integer timeout);

	Response<Message> sendMessage(int chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Integer reply_to_message_id, DummyKeyboard reply_markup);

	Response<Message> sendMessage(String chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Integer reply_to_message_id, DummyKeyboard reply_markup);

	Response<Message> forwardMessage(int chat_id, int from_chat_id, int message_id);

	Response<Message> forwardMessage(String chat_id, String from_chat_id, int message_id);

	Response<Message> sendLocation(int chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendLocation(String chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	void sendChatAction(int chat_id, ChatAction action);

	void sendChatAction(String chat_id, ChatAction action);

	Response<UserProfilePhotos> getUserProfilePhotos(int user_id, Integer offset, Integer limit);

	Response<Message> sendPhoto(int chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendPhoto(String chat_id, Resource photo, String caption, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendAudio(int chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup);

	Response<Message> sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Integer reply_to_message_id, DummyKeyboard reply_markup);

	Response<Message> sendDocument(int chat_id, Resource document, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendDocument(String chat_id, Resource document, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendSticker(int chat_id, Resource sticker, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendSticker(String chat_id, Resource sticker, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendVideo(int chat_id, Resource video, Integer duration, String caption,
			Integer reply_to_message_id, DummyKeyboard reply_markup);

	Response<Message> sendVideo(String chat_id, Resource video, Integer duration, String caption,
			Integer reply_to_message_id, DummyKeyboard reply_markup);

	Response<Message> sendVoice(int chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<Message> sendVoice(String chat_id, Resource voice, Integer duration, Integer reply_to_message_id,
			DummyKeyboard reply_markup);

	Response<File> getFile(String file_id);

	Resource getResourceFromTelegramFile(File file);

}