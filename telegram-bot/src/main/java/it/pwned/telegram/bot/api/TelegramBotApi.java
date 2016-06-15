package it.pwned.telegram.bot.api;

import java.util.List;

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
	 *          updateId.
	 * @param limit
	 *          Limits the number of updates to be retrieved. Values between 1-100
	 *          are accepted. Defaults to 100
	 * @param timeout
	 *          Timeout in seconds for long polling. Defaults to 0, i.e. usual
	 *          short polling
	 * @return
	 */
	Update[] getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException;

	Message sendMessage(long chatId, String text, String parseMode, Boolean disableWebPagePreview,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendMessage(String chatId, String text, String parseMode, Boolean disableWebPagePreview,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message forwardMessage(long chatId, long fromChatId, Boolean disableNotification, int messageId)
			throws TelegramBotApiException;

	Message forwardMessage(String chatId, String fromChatId, Boolean disableNotification, int messageId)
			throws TelegramBotApiException;

	Message sendLocation(long chatId, float latitude, float longitude, Integer replyToMessageId,
			DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendLocation(String chatId, float latitude, float longitude, Integer replyToMessageId,
			DummyKeyboard replyMarkup) throws TelegramBotApiException;

	void sendChatAction(long chatId, ChatAction action) throws TelegramBotApiException;

	void sendChatAction(String chatId, ChatAction action) throws TelegramBotApiException;

	UserProfilePhotos getUserProfilePhotos(int userId, Integer offset, Integer limit) throws TelegramBotApiException;

	Message sendPhoto(long chatId, Resource photo, String caption, Boolean disableNotification, Integer replyToMessageId,
			DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendPhoto(String chatId, Resource photo, String caption, Boolean disableNotification,
			Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendAudio(long chatId, Resource audio, Integer duration, String performer, String title,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendAudio(String chatId, Resource audio, Integer duration, String performer, String title,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendDocument(long chatId, Resource document, String caption, Boolean disableNotification,
			Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendDocument(String chatId, Resource document, String caption, Boolean disableNotification,
			Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendSticker(long chatId, Resource sticker, Boolean disableNotification, Integer replyToMessageId,
			DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendSticker(String chatId, Resource sticker, Boolean disableNotification, Integer replyToMessageId,
			DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendVideo(long chatId, Resource video, Integer duration, Integer width, Integer height, String caption,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendVideo(String chatId, Resource video, Integer duration, Integer width, Integer height, String caption,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendVoice(long chatId, Resource voice, Integer duration, Boolean disableNotification,
			Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendVoice(String chatId, Resource voice, Integer duration, Boolean disableNotification,
			Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendVenue(long chatId, float latitude, float longitude, String title, String address, String foursquareId,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendVenue(String chatId, float latitude, float longitude, String title, String address, String foursquareId,
			Boolean disableNotification, Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendContact(long chatId, String phoneNumber, String firstName, String lastName, Boolean disableNotification,
			Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	Message sendContact(String chatId, String phoneNumber, String firstName, String lastName, Boolean disableNotification,
			Integer replyToMessageId, DummyKeyboard replyMarkup) throws TelegramBotApiException;

	TelegramFile getFile(String fileId) throws TelegramBotApiException;

	Resource getResourceFromTelegramFile(TelegramFile file) throws TelegramBotApiException;

	Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cacheTime,
			Boolean isPersonal, String nextOffset, String switchPmText, String switchPmParameter)
			throws TelegramBotApiException;

	Boolean kickChatMember(long chatId, int userId) throws TelegramBotApiException;

	Boolean kickChatMember(String chatId, int userId) throws TelegramBotApiException;

	Boolean leaveChat(long chatId) throws TelegramBotApiException;

	Boolean leaveChat(String chatId) throws TelegramBotApiException;

	Boolean unbanChatMember(long chatId, int userId) throws TelegramBotApiException;

	Boolean unbanChatMember(String chatId, int userId) throws TelegramBotApiException;

	Boolean answerCallbackQuery(String callbackQueryId, String text, Boolean showAlert) throws TelegramBotApiException;

	Message editMessageText(long chatId, Integer messageId, String inlineMessageId, String text, String parseMode,
			Boolean disableWebPagePreview, InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message editMessageText(String chatId, Integer messageId, String inlineMessageId, String text, String parseMode,
			Boolean disableWebPagePreview, InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message editMessageCaption(long chatId, Integer messageId, String inlineMessageId, String caption,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message editMessageCaption(String chatId, Integer messageId, String inlineMessageId, String caption,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message editMessageReplyMarkup(long chatId, Integer messageId, String inlineMessageId,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message editMessageReplyMarkup(String chatId, Integer messageId, String inlineMessageId,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Chat getChat(long chatId) throws TelegramBotApiException;

	Chat getChat(String chatId) throws TelegramBotApiException;

	ChatMember[] getChatAdministrators(long chatId) throws TelegramBotApiException;

	ChatMember[] getChatAdministrators(String chatId) throws TelegramBotApiException;

	ChatMember getChatMember(long chatId, int userId) throws TelegramBotApiException;

	ChatMember getChatMember(String chatId, int userId) throws TelegramBotApiException;

	int getChatMembersCount(long chatId) throws TelegramBotApiException;

	int getChatMembersCount(String chatId) throws TelegramBotApiException;

}
