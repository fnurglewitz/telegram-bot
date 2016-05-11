package it.pwned.telegram.bot.api;

import java.util.List;

import org.springframework.core.io.Resource;

import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.DummyKeyboard;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

public interface TelegramBotApi {

	User getMe() throws TelegramBotApiException;

	/**
	 * 
	 * @param offset
	 *          Identifier of the first update to be returned. Must be greater by
	 *          one than the highest among the identifiers of previously received
	 *          updates. By default, updates starting with the earliest
	 *          unconfirmed update are returned. An update is considered confirmed
	 *          as soon as getUpdates is called with an offset higher than its
	 *          update_id.
	 * @param limit
	 *          Limits the number of updates to be retrieved. Values between 1-100
	 *          are accepted. Defaults to 100
	 * @param timeout
	 *          Timeout in seconds for long polling. Defaults to 0, i.e. usual
	 *          short polling
	 * @return
	 */
	Update[] getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException;

	Message sendMessage(long chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendMessage(String chat_id, String text, String parse_mode, Boolean disable_web_page_preview,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message forwardMessage(long chat_id, long from_chat_id, Boolean disable_notification, int message_id)
			throws TelegramBotApiException;

	Message forwardMessage(String chat_id, String from_chat_id, Boolean disable_notification, int message_id)
			throws TelegramBotApiException;

	Message sendLocation(long chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendLocation(String chat_id, float latitude, float longitude, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException;

	void sendChatAction(long chat_id, ChatAction action) throws TelegramBotApiException;

	void sendChatAction(String chat_id, ChatAction action) throws TelegramBotApiException;

	UserProfilePhotos getUserProfilePhotos(int user_id, Integer offset, Integer limit) throws TelegramBotApiException;

	Message sendPhoto(long chat_id, Resource photo, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendPhoto(String chat_id, Resource photo, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendAudio(long chat_id, Resource audio, Integer duration, String performer, String title,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendAudio(String chat_id, Resource audio, Integer duration, String performer, String title,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendDocument(long chat_id, Resource document, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendDocument(String chat_id, Resource document, String caption, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendSticker(long chat_id, Resource sticker, Boolean disable_notification, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendSticker(String chat_id, Resource sticker, Boolean disable_notification, Integer reply_to_message_id,
			DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendVideo(long chat_id, Resource video, Integer duration, Integer width, Integer height, String caption,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendVideo(String chat_id, Resource video, Integer duration, Integer width, Integer height, String caption,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendVoice(long chat_id, Resource voice, Integer duration, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendVoice(String chat_id, Resource voice, Integer duration, Boolean disable_notification,
			Integer reply_to_message_id, DummyKeyboard reply_markup) throws TelegramBotApiException;

	Message sendVenue(long chat_id, float latitude, float longitude, String title, String address, String foursquare_id,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendVenue(String chat_id, float latitude, float longitude, String title, String address, String foursquare_id,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendContact(long chat_id, String phone_number, String first_name, String last_name,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	Message sendContact(String chat_id, String phone_number, String first_name, String last_name,
			Boolean disable_notification, Integer reply_to_message_id, DummyKeyboard reply_markup)
					throws TelegramBotApiException;

	TelegramFile getFile(String file_id) throws TelegramBotApiException;

	Resource getResourceFromTelegramFile(TelegramFile file) throws TelegramBotApiException;

	Boolean answerInlineQuery(String inline_query_id, List<InlineQueryResult> results, Integer cache_time,
			Boolean is_personal, String next_offset, String switch_pm_text, String switch_pm_parameter) throws TelegramBotApiException;

	Boolean kickChatMember(long chat_id, int user_id) throws TelegramBotApiException;

	Boolean kickChatMember(String chat_id, int user_id) throws TelegramBotApiException;

	Boolean unbanChatMember(long chat_id, int user_id) throws TelegramBotApiException;

	Boolean unbanChatMember(String chat_id, int user_id) throws TelegramBotApiException;

	Boolean answerCallbackQuery(String callback_query_id, String text, Boolean show_alert) throws TelegramBotApiException;

	Message editMessageText(Long chat_id, Integer message_id, String inline_message_id, String text, String parse_mode,
			Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup) throws TelegramBotApiException;

	Message editMessageText(String chat_id, Integer message_id, String inline_message_id, String text, String parse_mode,
			Boolean disable_web_page_preview, InlineKeyboardMarkup reply_markup) throws TelegramBotApiException;

	Message editMessageCaption(Long chat_id, Integer message_id, String inline_message_id, String caption,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException;

	Message editMessageCaption(String chat_id, Integer message_id, String inline_message_id, String caption,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException;

	Message editMessageReplyMarkup(Long chat_id, Integer message_id, String inline_message_id,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException;

	Message editMessageReplyMarkup(String chat_id, Integer message_id, String inline_message_id,
			InlineKeyboardMarkup reply_markup) throws TelegramBotApiException;
}
