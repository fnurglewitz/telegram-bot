package it.pwned.telegram.bot.api;

import java.util.List;

import org.springframework.core.io.Resource;

import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

public interface TelegramBotApi {

	User getMe() throws TelegramBotApiException;

	/**
	 * Use this method to receive incoming updates using long polling. An Array of
	 * {@link it.pwned.telegram.bot.api.type.Update Update} objects is returned.
	 * 
	 * @param offset
	 *          Identifier of the first update to be returned. Must be greater by
	 *          one than the highest among the identifiers of previously received
	 *          updates. By default, updates starting with the earliest
	 *          unconfirmed update are returned. An update is considered confirmed
	 *          as soon as getUpdates is called with an offset higher than its
	 *          {@link it.pwned.telegram.bot.api.type.Update#updateId updateId}.
	 * @param limit
	 *          Limits the number of updates to be retrieved. Values between 1-100
	 *          are accepted. Defaults to 100
	 * @param timeout
	 *          Timeout in seconds for long polling. Defaults to 0, i.e. usual
	 *          short polling
	 * @return
	 */
	Update[] getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException;

	/**
	 * Use this method to send text messages. On success, the sent
	 * {@link it.pwned.telegram.bot.api.type.Message Message} is returned.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param text
	 *          Text of the message to be sent
	 * @param parseMode
	 *          Optional. Send Markdown or HTML, if you want Telegram apps to show
	 *          bold, italic, fixed-width text or inline URLs in your bot's
	 *          message.
	 * @param disableWebPagePreview
	 *          Optional. Disables link previews for links in this message
	 * @param disableNotification
	 *          Optional. Sends the message silently. iOS users will not receive a
	 *          notification, Android users will receive a notification with no
	 *          sound.
	 * @param replyToMessageId
	 *          Optional. If the message is a reply, ID of the original message
	 * @param replyMarkup
	 *          Optional. Additional interface options. A JSON-serialized object
	 *          for an inline keyboard, custom reply keyboard, instructions to
	 *          hide reply keyboard or to force a reply from the user.
	 * @return
	 * @throws TelegramBotApiException
	 */
	Message sendMessage(ChatId chatId, String text, ParseMode parseMode, Boolean disableWebPagePreview,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message forwardMessage(ChatId chatId, ChatId fromChatId, Boolean disableNotification, int messageId)
			throws TelegramBotApiException;

	Message sendLocation(ChatId chatId, float latitude, float longitude, Integer replyToMessageId,
			AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	void sendChatAction(ChatId chatId, ChatAction action) throws TelegramBotApiException;

	UserProfilePhotos getUserProfilePhotos(int userId, Integer offset, Integer limit) throws TelegramBotApiException;

	Message sendPhoto(ChatId chatId, Resource photo, String caption, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message sendAudio(ChatId chatId, Resource audio, Integer duration, String performer, String title,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message sendDocument(ChatId chatId, Resource document, String caption, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message sendSticker(ChatId chatId, Resource sticker, Boolean disableNotification, Integer replyToMessageId,
			AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message sendVideo(ChatId chatId, Resource video, Integer duration, Integer width, Integer height, String caption,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message sendVoice(ChatId chatId, Resource voice, Integer duration, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message sendVenue(ChatId chatId, float latitude, float longitude, String title, String address, String foursquareId,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message sendContact(ChatId chatId, String phoneNumber, String firstName, String lastName, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	TelegramFile getFile(String fileId) throws TelegramBotApiException;

	Resource getResourceFromTelegramFile(TelegramFile file) throws TelegramBotApiException;

	Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cacheTime,
			Boolean isPersonal, String nextOffset, String switchPmText, String switchPmParameter)
			throws TelegramBotApiException;

	Boolean kickChatMember(ChatId chatId, int userId) throws TelegramBotApiException;

	Boolean leaveChat(ChatId chatId) throws TelegramBotApiException;

	Boolean unbanChatMember(ChatId chatId, int userId) throws TelegramBotApiException;

	Boolean answerCallbackQuery(String callbackQueryId, String text, Boolean showAlert) throws TelegramBotApiException;

	Message editMessageText(ChatId chatId, Integer messageId, String inlineMessageId, String text, ParseMode parseMode,
			Boolean disableWebPagePreview, InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message editMessageCaption(ChatId chatId, Integer messageId, String inlineMessageId, String caption,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Message editMessageReplyMarkup(ChatId chatId, Integer messageId, String inlineMessageId,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	Chat getChat(ChatId chatId) throws TelegramBotApiException;

	ChatMember[] getChatAdministrators(ChatId chatId) throws TelegramBotApiException;

	ChatMember getChatMember(ChatId chatId, int userId) throws TelegramBotApiException;

	int getChatMembersCount(ChatId chatId) throws TelegramBotApiException;

}
