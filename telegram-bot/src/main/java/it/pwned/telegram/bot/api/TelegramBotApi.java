package it.pwned.telegram.bot.api;

import java.util.List;

import org.springframework.core.io.Resource;

import it.pwned.telegram.bot.api.type.Chat;
import it.pwned.telegram.bot.api.type.ChatAction;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.ChatMember;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.TelegramFile;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.ParseMode;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.api.type.UserProfilePhotos;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

/**
 * The Bot API is an HTTP-based interface created for developers keen on
 * building bots for Telegram.
 *
 */
public interface TelegramBotApi {

	/**
	 * A simple method for testing your bot's auth token. Requires no parameters.
	 * 
	 * @return Returns basic information about the bot in form of a
	 *         {@link it.pwned.telegram.bot.api.type.User User} object.
	 * @throws TelegramBotApiException
	 */
	User getMe() throws TelegramBotApiException;

	/**
	 * Use this method to receive incoming updates using long polling. A List of
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
	List<Update> getUpdates(Integer offset, Integer limit, Integer timeout) throws TelegramBotApiException;

	/**
	 * Use this method to send text messages.
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendMessage(ChatId chatId, String text, ParseMode parseMode, Boolean disableWebPagePreview,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException;

	/**
	 * Use this method to forward messages of any kind.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param fromChatId
	 *          Unique identifier for the chat where the original message was sent
	 *          (or channel username in the format @channelusername)
	 * @param disableNotification
	 *          Optional. Sends the message silently. iOS users will not receive a
	 *          notification, Android users will receive a notification with no
	 *          sound.
	 * @param messageId
	 *          Unique message identifier
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message forwardMessage(ChatId chatId, ChatId fromChatId, Boolean disableNotification, int messageId)
			throws TelegramBotApiException;

	/**
	 * Use this method to send point on the map.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param latitude
	 *          Latitude of location
	 * @param longitude
	 *          Longitude of location
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendLocation(ChatId chatId, float latitude, float longitude, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method when you need to tell the user that something is happening
	 * on the bot's side. The status is set for 5 seconds or less (when a message
	 * arrives from your bot, Telegram clients clear its typing status).
	 * 
	 * We only recommend using this method when a response from the bot will take
	 * a <b>noticeable</b> amount of time to arrive.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param action
	 *          Type of action to broadcast. Choose one, depending on what the
	 *          user is about to receive: typing for text messages, upload_photo
	 *          for photos, record_video or upload_video for videos, record_audio
	 *          or upload_audio for audio files, upload_document for general
	 *          files, find_location for location data.
	 * @throws TelegramBotApiException
	 */
	void sendChatAction(ChatId chatId, ChatAction action) throws TelegramBotApiException;

	/**
	 * Use this method to get a list of profile pictures for a user.
	 * 
	 * @param userId
	 *          Unique identifier of the target user
	 * @param offset
	 *          Optional. Sequential number of the first photo to be returned. By
	 *          default, all photos are returned.
	 * @param limit
	 *          Optional. Limits the number of photos to be retrieved. Values
	 *          between 1—100 are accepted. Defaults to 100.
	 * @return Returns a {@link it.pwned.telegram.bot.api.type.UserProfilePhotos
	 *         UserProfilePhotos} object.
	 * @throws TelegramBotApiException
	 */
	UserProfilePhotos getUserProfilePhotos(int userId, Integer offset, Integer limit) throws TelegramBotApiException;

	/**
	 * Use this method to send photos.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param photo
	 *          Photo to send. You can either pass a file_id as String to resend a
	 *          photo that is already on the Telegram servers, or upload a new
	 *          photo using multipart/form-data.
	 * @param caption
	 *          Optional. Photo caption (may also be used when resending photos by
	 *          file_id), 0-200 characters
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendPhoto(ChatId chatId, Resource photo, String caption, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method to send audio files, if you want Telegram clients to
	 * display them in the music player. Your audio must be in the .mp3 format.
	 * Bots can currently send audio files of up to 50 MB in size, this limit may
	 * be changed in the future.
	 * 
	 * For sending voice messages, use the
	 * {@link it.pwned.telegram.bot.api.TelegramBotApi.sendVoice sendVoice} method
	 * instead.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param audio
	 *          Audio file to send. You can either pass a file_id as String to
	 *          resend an audio that is already on the Telegram servers, or upload
	 *          a new audio file using multipart/form-data.
	 * @param duration
	 *          Optional. Duration of the audio in seconds
	 * @param performer
	 *          Optional. Performer
	 * @param title
	 *          Optional. Track name
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendAudio(ChatId chatId, Resource audio, Integer duration, String performer, String title,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException;

	/**
	 * Use this method to send general files. Bots can currently send files of any
	 * type of up to 50 MB in size, this limit may be changed in the future.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param document
	 *          File to send. You can either pass a file_id as String to resend a
	 *          file that is already on the Telegram servers, or upload a new file
	 *          using multipart/form-data.
	 * @param caption
	 *          Optional. Document caption (may also be used when resending
	 *          documents by file_id), 0-200 characters
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendDocument(ChatId chatId, Resource document, String caption, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method to send .webp stickers.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param sticker
	 *          Sticker to send. You can either pass a file_id as String to resend
	 *          a sticker that is already on the Telegram servers, or upload a new
	 *          sticker using multipart/form-data.
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendSticker(ChatId chatId, Resource sticker, Boolean disableNotification, Integer replyToMessageId,
			AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method to send video files, Telegram clients support mp4 videos
	 * (other formats may be sent as Document). Bots can currently send video
	 * files of up to 50 MB in size, this limit may be changed in the future.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param video
	 *          Video to send. You can either pass a file_id as String to resend a
	 *          video that is already on the Telegram servers, or upload a new
	 *          video file using multipart/form-data.
	 * @param duration
	 *          Optional. Duration of sent video in seconds
	 * @param width
	 *          Optional. Video width
	 * @param height
	 *          Optional. Video height
	 * @param caption
	 *          Optional. Video caption (may also be used when resending videos by
	 *          file_id), 0-200 characters
	 * @param disableNotification
	 *          Optional. Sends the message silently. iOS users will not receive a
	 *          notification, Android users will receive a notification with no
	 *          sound.
	 * @param replyToMessageId
	 *          Optional. If the message is a reply, ID of the original message
	 * @param replyMarkup
	 *          Optional. Additional interface options. A JSON-serialized object
	 *          for an inline keyboard, custom reply keyboard, instructions to
	 *          hide reply keyboard or to force a reply from the user. *
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendVideo(ChatId chatId, Resource video, Integer duration, Integer width, Integer height, String caption,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException;

	/**
	 * Use this method to send audio files, if you want Telegram clients to
	 * display the file as a playable voice message. For this to work, your audio
	 * must be in an .ogg file encoded with OPUS (other formats may be sent as
	 * Audio or Document). Bots can currently send voice messages of up to 50 MB
	 * in size, this limit may be changed in the future.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param voice
	 *          Audio file to send. You can either pass a file_id as String to
	 *          resend an audio that is already on the Telegram servers, or upload
	 *          a new audio file using multipart/form-data.
	 * @param duration
	 *          Optional. Duration of sent audio in seconds
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendVoice(ChatId chatId, Resource voice, Integer duration, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method to send information about a venue.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param latitude
	 *          Latitude of the venue
	 * @param longitude
	 *          Longitude of the venue
	 * @param title
	 *          Name of the venue
	 * @param address
	 *          Address of the venue
	 * @param foursquareId
	 *          Optional. Foursquare identifier of the venue
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
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendVenue(ChatId chatId, float latitude, float longitude, String title, String address, String foursquareId,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup)
			throws TelegramBotApiException;

	/**
	 * Use this method to send phone contacts.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          channel (in the format @channelusername)
	 * @param phoneNumber
	 *          Contact's phone number
	 * @param firstName
	 *          Contact's first name
	 * @param lastName
	 *          Optional. Contact's last name
	 * @param disableNotification
	 *          Optional. Sends the message silently. iOS users will not receive a
	 *          notification, Android users will receive a notification with no
	 *          sound.
	 * @param replyToMessageId
	 *          Optional. If the message is a reply, ID of the original message
	 * @param replyMarkup
	 *          Optional. Additional interface options. A JSON-serialized object
	 *          for an inline keyboard, custom reply keyboard, instructions to
	 *          hide keyboard or to force a reply from the user.
	 * @return On success, the sent {@link it.pwned.telegram.bot.api.type.Message
	 *         Message} is returned.
	 * @throws TelegramBotApiException
	 */
	Message sendContact(ChatId chatId, String phoneNumber, String firstName, String lastName, Boolean disableNotification,
			Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method to get basic info about a file and prepare it for
	 * downloading. For the moment, bots can download files of up to 20MB in size.
	 * The file can then be downloaded via the link
	 * https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt;, where
	 * &lt;file_path&gt; is taken from the response. It is guaranteed that the
	 * link will be valid for at least 1 hour. When the link expires, a new one
	 * can be requested by calling getFile again. <br/>
	 * <b>Note:</b> This function may not preserve original file name. Mime type
	 * of the file and its name (if available) should be saved when the File
	 * object is received.
	 * 
	 * @param fileId
	 * @return On success, a {@link it.pwned.telegram.bot.api.type.TelegramFile
	 *         TelegramFile} object is returned.
	 * @throws TelegramBotApiException
	 */
	TelegramFile getFile(String fileId) throws TelegramBotApiException;

	/**
	 * Utility function to get a spring
	 * {@link org.springframework.core.io.Resource Resource} from a
	 * {@link it.pwned.telegram.bot.api.type.TelegramFile TelegramFile} object
	 * 
	 * @param file
	 *          The telegram file to convert
	 * @return A spring Resource "pointing" to the Telegram file
	 * @throws TelegramBotApiException
	 */
	Resource getResourceFromTelegramFile(TelegramFile file) throws TelegramBotApiException;

	/**
	 * Use this method to send answers to an inline query.<br/>
	 * No more than <b>50</b> results per query are allowed.
	 * 
	 * @param inlineQueryId
	 *          Unique identifier for the answered query
	 * @param results
	 *          A JSON-serialized array of results for the inline query
	 * @param cacheTime
	 *          Optional. The maximum amount of time in seconds that the result of
	 *          the inline query may be cached on the server. Defaults to 300.
	 * @param isPersonal
	 *          Optional. Pass True, if results may be cached on the server side
	 *          only for the user that sent the query. By default, results may be
	 *          returned to any user who sends the same query
	 * @param nextOffset
	 *          Optional. Pass the offset that a client should send in the next
	 *          query with the same text to receive more results. Pass an empty
	 *          string if there are no more results or if you don‘t support
	 *          pagination. Offset length can’t exceed 64 bytes.
	 * @param switchPmText
	 *          Optional. If passed, clients will display a button with specified
	 *          text that switches the user to a private chat with the bot and
	 *          sends the bot a start message with the parameter
	 *          switch_pm_parameter
	 * @param switchPmParameter
	 *          Optional. Parameter for the start message sent to the bot when
	 *          user presses the switch button.<br/>
	 *          <br/>
	 *          <em>Example:</em> An inline bot that sends YouTube videos can ask
	 *          the user to connect the bot to their YouTube account to adapt
	 *          search results accordingly. To do this, it displays a 'Connect
	 *          your YouTube account' button above the results, or even before
	 *          showing any. The user presses the button, switches to a private
	 *          chat with the bot and, in doing so, passes a start parameter that
	 *          instructs the bot to return an oauth link. Once done, the bot can
	 *          offer a switch_inline button so that the user can easily return to
	 *          the chat where they wanted to use the bot's inline capabilities.
	 * 
	 * @return On success, True is returned.
	 * @throws TelegramBotApiException
	 */
	Boolean answerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cacheTime,
			Boolean isPersonal, String nextOffset, String switchPmText, String switchPmParameter)
			throws TelegramBotApiException;

	/**
	 * Use this method to kick a user from a group or a supergroup. In the case of
	 * supergroups, the user will not be able to return to the group on their own
	 * using invite links, etc., unless unbanned first. The bot must be an
	 * administrator in the group for this to work. <br/>
	 * <br/>
	 * Note: This will method only work if the ‘All Members Are Admins’ setting is
	 * off in the target group. Otherwise members may only be removed by the
	 * group's creator or by the member that added them.
	 * 
	 * @param chatId
	 *          Unique identifier for the target group or username of the target
	 *          supergroup (in the format @supergroupusername)
	 * @param userId
	 *          Unique identifier of the target user
	 * @return Returns True on success.
	 * @throws TelegramBotApiException
	 */
	Boolean kickChatMember(ChatId chatId, int userId) throws TelegramBotApiException;

	/**
	 * Use this method for your bot to leave a group, supergroup or channel.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          supergroup or channel (in the format @channelusername)
	 * @return Returns True on success.
	 * @throws TelegramBotApiException
	 */
	Boolean leaveChat(ChatId chatId) throws TelegramBotApiException;

	/**
	 * Use this method to unban a previously kicked user in a supergroup. The user
	 * will not return to the group automatically, but will be able to join via
	 * link, etc. The bot must be an administrator in the group for this to work.
	 * 
	 * @param chatId
	 *          Unique identifier for the target group or username of the target
	 *          supergroup (in the format @supergroupusername)
	 * @param userId
	 *          Unique identifier of the target user
	 * @return Returns True on success.
	 * @throws TelegramBotApiException
	 */
	Boolean unbanChatMember(ChatId chatId, int userId) throws TelegramBotApiException;

	/**
	 * Use this method to send answers to callback queries sent from inline
	 * keyboards. The answer will be displayed to the user as a notification at
	 * the top of the chat screen or as an alert.
	 * 
	 * @param callbackQueryId
	 *          Unique identifier for the query to be answered
	 * @param text
	 *          Optional. Text of the notification. If not specified, nothing will
	 *          be shown to the user
	 * @param showAlert
	 *          Optional. If true, an alert will be shown by the client instead of
	 *          a notification at the top of the chat screen. Defaults to false.
	 * @return On success, True is returned.
	 * @throws TelegramBotApiException
	 */
	Boolean answerCallbackQuery(String callbackQueryId, String text, Boolean showAlert) throws TelegramBotApiException;

	/**
	 * Use this method to edit text messages sent by the bot or via the bot (for
	 * inline bots).
	 * 
	 * @param chatId
	 *          Required if inline_message_id is not specified. Unique identifier
	 *          for the target chat or username of the target channel (in the
	 *          format @channelusername)
	 * @param messageId
	 *          Required if inline_message_id is not specified. Unique identifier
	 *          of the sent message
	 * @param inlineMessageId
	 *          Required if chat_id and message_id are not specified. Identifier
	 *          of the inline message
	 * @param text
	 *          New text of the message
	 * @param parseMode
	 *          Optional. Send Markdown or HTML, if you want Telegram apps to show
	 *          bold, italic, fixed-width text or inline URLs in your bot's
	 *          message.
	 * @param disableWebPagePreview
	 *          Optional. Disables link previews for links in this message
	 * @param replyMarkup
	 *          Optional. A JSON-serialized object for an inline keyboard.
	 * @return On success, if edited message is sent by the bot, the edited
	 *         Message is returned, otherwise True is returned.
	 * @throws TelegramBotApiException
	 */
	BooleanOrMessage editMessageText(ChatId chatId, Integer messageId, String inlineMessageId, String text,
			ParseMode parseMode, Boolean disableWebPagePreview, InlineKeyboardMarkup replyMarkup)
			throws TelegramBotApiException;

	/**
	 * Use this method to edit captions of messages sent by the bot or via the bot
	 * (for inline bots).
	 * 
	 * @param chatId
	 *          Required if inline_message_id is not specified. Unique identifier
	 *          for the target chat or username of the target channel (in the
	 *          format @channelusername)
	 * @param messageId
	 *          Required if inline_message_id is not specified. Unique identifier
	 *          of the sent message
	 * @param inlineMessageId
	 *          Required if chat_id and message_id are not specified. Identifier
	 *          of the inline message
	 * @param caption
	 *          Optional. New caption of the message
	 * @param replyMarkup
	 *          Optional. A JSON-serialized object for an inline keyboard.
	 * @return On success, if edited message is sent by the bot, the edited
	 *         Message is returned, otherwise True is returned.
	 * @throws TelegramBotApiException
	 */
	BooleanOrMessage editMessageCaption(ChatId chatId, Integer messageId, String inlineMessageId, String caption,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method to edit only the reply markup of messages sent by the bot
	 * or via the bot (for inline bots).
	 * 
	 * @param chatId
	 *          Required if inline_message_id is not specified. Unique identifier
	 *          for the target chat or username of the target channel (in the
	 *          format @channelusername)
	 * @param messageId
	 *          Required if inline_message_id is not specified. Unique identifier
	 *          of the sent message
	 * @param inlineMessageId
	 *          Required if chat_id and message_id are not specified. Identifier
	 *          of the inline message
	 * @param replyMarkup
	 *          Optional. A JSON-serialized object for an inline keyboard.
	 * 
	 * @return On success, if edited message is sent by the bot, the edited
	 *         Message is returned, otherwise True is returned.
	 * @throws TelegramBotApiException
	 */
	BooleanOrMessage editMessageReplyMarkup(ChatId chatId, Integer messageId, String inlineMessageId,
			InlineKeyboardMarkup replyMarkup) throws TelegramBotApiException;

	/**
	 * Use this method to get up to date information about the chat (current name
	 * of the user for one-on-one conversations, current username of a user, group
	 * or channel, etc.).
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          supergroup or channel (in the format @channelusername)
	 * @return Returns a Chat object on success.
	 * @throws TelegramBotApiException
	 */
	Chat getChat(ChatId chatId) throws TelegramBotApiException;

	/**
	 * Use this method to get a list of administrators in a chat. If the chat is a
	 * group or a supergroup and no administrators were appointed, only the
	 * creator will be returned.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          supergroup or channel (in the format @channelusername)
	 * @return On success, returns a List of ChatMember objects that contains
	 *         information about all chat administrators except other bots.
	 * @throws TelegramBotApiException
	 */
	List<ChatMember> getChatAdministrators(ChatId chatId) throws TelegramBotApiException;

	/**
	 * Use this method to get information about a member of a chat.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          supergroup or channel (in the format @channelusername)
	 * @param userId
	 *          Unique identifier of the target user
	 * @return Returns a ChatMember object on success.
	 * @throws TelegramBotApiException
	 */
	ChatMember getChatMember(ChatId chatId, int userId) throws TelegramBotApiException;

	/**
	 * Use this method to get the number of members in a chat.
	 * 
	 * @param chatId
	 *          Unique identifier for the target chat or username of the target
	 *          supergroup or channel (in the format @channelusername)
	 * @return Returns Int on success.
	 * @throws TelegramBotApiException
	 */
	int getChatMembersCount(ChatId chatId) throws TelegramBotApiException;

}
