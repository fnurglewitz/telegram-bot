package it.pwned.telegram.bot.api.type;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a message.
 *
 */
public class Message {

	private final static String JSON_FIELD_MESSAGE_ID = "message_id";
	private final static String JSON_FIELD_FROM = "from";
	private final static String JSON_FIELD_DATE = "date";
	private final static String JSON_FIELD_CHAT = "chat";
	private final static String JSON_FIELD_FORWARD_FROM = "forward_from";
	private final static String JSON_FIELD_FORWARD_FROM_CHAT = "forward_from_chat";
	private final static String JSON_FIELD_FORWARD_DATE = "forward_date";
	private final static String JSON_FIELD_REPLY_TO_MESSAGE = "reply_to_message";
	private final static String JSON_FIELD_EDIT_DATE = "edit_date";
	private final static String JSON_FIELD_TEXT = "text";
	private final static String JSON_FIELD_ENTITIES = "entities";
	private final static String JSON_FIELD_AUDIO = "audio";
	private final static String JSON_FIELD_DOCUMENT = "document";
	private final static String JSON_FIELD_PHOTO = "photo";
	private final static String JSON_FIELD_STICKER = "sticker";
	private final static String JSON_FIELD_VIDEO = "video";
	private final static String JSON_FIELD_VOICE = "voice";
	private final static String JSON_FIELD_CAPTION = "caption";
	private final static String JSON_FIELD_CONTACT = "contact";
	private final static String JSON_FIELD_LOCATION = "location";
	private final static String JSON_FIELD_VENUE = "venue";
	private final static String JSON_FIELD_NEW_CHAT_MEMBER = "new_chat_member";
	private final static String JSON_FIELD_LEFT_CHAT_MEMBER = "left_chat_member";
	private final static String JSON_FIELD_NEW_CHAT_TITLE = "new_chat_title";
	private final static String JSON_FIELD_NEW_CHAT_PHOTO = "new_chat_photo";
	private final static String JSON_FIELD_DELETE_CHAT_PHOTO = "delete_chat_photo";
	private final static String JSON_FIELD_GROUP_CHAT_CREATED = "group_chat_created";
	private final static String JSON_FIELD_SUPERGROUP_CHAT_CREATED = "supergroup_chat_created";
	private final static String JSON_FIELD_CHANNEL_CHAT_CREATED = "channel_chat_created";
	private final static String JSON_FIELD_MIGRATE_TO_CHAT_ID = "migrate_to_chat_id";
	private final static String JSON_FIELD_MIGRATE_FROM_CHAT_ID = "migrate_from_chat_id";
	private final static String JSON_FIELD_PINNED_MESSAGE = "pinned_message";

	/**
	 * Unique message identifier
	 */
	@JsonProperty(JSON_FIELD_MESSAGE_ID)
	public final Integer messageId;

	/**
	 * <em>Optional.</em> Sender, can be empty for messages sent to channels
	 */
	@JsonProperty(JSON_FIELD_FROM)
	public final User from;

	/**
	 * Date the message was sent in Unix time
	 */
	@JsonProperty(JSON_FIELD_DATE)
	public final Integer date;

	/**
	 * Conversation the message belongs to
	 */
	@JsonProperty(JSON_FIELD_CHAT)
	public final Chat chat;

	/**
	 * <em>Optional.</em> For forwarded messages, sender of the original message
	 */
	@JsonProperty(JSON_FIELD_FORWARD_FROM)
	public final User forwardFrom;

	/**
	 * <em>Optional.</em> For messages forwarded from a channel, information about
	 * the original channel
	 */
	@JsonProperty(JSON_FIELD_FORWARD_FROM_CHAT)
	public final Chat forwardFromChat;

	/**
	 * <em>Optional.</em> For forwarded messages, date the original message was
	 * sent in Unix time
	 */
	@JsonProperty(JSON_FIELD_FORWARD_DATE)
	public final Integer forwardDate;

	/**
	 * <em>Optional.</em> For replies, the original message. Note that the Message
	 * object in this field will not contain further reply_to_message fields even
	 * if it itself is a reply.
	 */
	@JsonProperty(JSON_FIELD_REPLY_TO_MESSAGE)
	public final Message replyToMessage;

	/**
	 * <em>Optional.</em> Date the message was last edited in Unix time
	 */
	@JsonProperty(JSON_FIELD_EDIT_DATE)
	public final Integer editDate;

	/**
	 * <em>Optional.</em> For text messages, the actual UTF-8 text of the message,
	 * 0-4096 characters.
	 */
	@JsonProperty(JSON_FIELD_TEXT)
	public final String text;

	/**
	 * <em>Optional.</em> For text messages, special entities like usernames,
	 * URLs, bot commands, etc. that appear in the text
	 */
	@JsonProperty(JSON_FIELD_ENTITIES)
	public final MessageEntity[] entities;

	/**
	 * <em>Optional.</em> Message is an audio file, information about the file
	 */
	@JsonProperty(JSON_FIELD_AUDIO)
	public final Audio audio;

	/**
	 * <em>Optional.</em> Message is a general file, information about the file
	 */
	@JsonProperty(JSON_FIELD_DOCUMENT)
	public final Document document;

	/**
	 * <em>Optional.</em> Message is a photo, available sizes of the photo
	 */
	@JsonProperty(JSON_FIELD_PHOTO)
	public final PhotoSize[] photo;

	/**
	 * <em>Optional.</em> Message is a sticker, information about the sticker
	 */
	@JsonProperty(JSON_FIELD_STICKER)
	public final Sticker sticker;

	/**
	 * <em>Optional.</em> Message is a video, information about the video
	 */
	@JsonProperty(JSON_FIELD_VIDEO)
	public final Video video;

	/**
	 * <em>Optional.</em> Message is a voice message, information about the file
	 */
	@JsonProperty(JSON_FIELD_VOICE)
	public final Voice voice;

	/**
	 * <em>Optional.</em> Caption for the document, photo or video, 0-200
	 * characters
	 */
	@JsonProperty(JSON_FIELD_CAPTION)
	public final String caption;

	/**
	 * <em>Optional.</em> Message is a shared contact, information about the
	 * contact
	 */
	@JsonProperty(JSON_FIELD_CONTACT)
	public final Contact contact;

	/**
	 * <em>Optional.</em> Message is a shared location, information about the
	 * location
	 */
	@JsonProperty(JSON_FIELD_LOCATION)
	public final Location location;

	/**
	 * <em>Optional.</em> Message is a venue, information about the venue
	 */
	@JsonProperty(JSON_FIELD_VENUE)
	public final Venue venue;

	/**
	 * <em>Optional.</em> A new member was added to the group, information about
	 * them (this member may be the bot itself)
	 */
	@JsonProperty(JSON_FIELD_NEW_CHAT_MEMBER)
	public final User newChatMember;

	/**
	 * <em>Optional.</em> A member was removed from the group, information about
	 * them (this member may be the bot itself)
	 */
	@JsonProperty(JSON_FIELD_LEFT_CHAT_MEMBER)
	public final User leftChatMember;

	/**
	 * <em>Optional.</em> A chat title was changed to this value
	 */
	@JsonProperty(JSON_FIELD_NEW_CHAT_TITLE)
	public final String newChatTitle;

	/**
	 * <em>Optional.</em> A chat photo was change to this value
	 */
	@JsonProperty(JSON_FIELD_NEW_CHAT_PHOTO)
	public final PhotoSize[] newChatPhoto;

	/**
	 * <em>Optional.</em> Service message: the chat photo was deleted
	 */
	@JsonProperty(JSON_FIELD_DELETE_CHAT_PHOTO)
	public final Boolean deleteChatPhoto;

	/**
	 * <em>Optional.</em> Service message: the group has been created
	 */
	@JsonProperty(JSON_FIELD_GROUP_CHAT_CREATED)
	public final Boolean groupChatCreated;

	/**
	 * <em>Optional.</em> Service message: the supergroup has been created. This
	 * field can‘t be received in a message coming through updates, because bot
	 * can’t be a member of a supergroup when it is created. It can only be found
	 * in reply_to_message if someone replies to a very first message in a
	 * directly created supergroup.
	 */
	@JsonProperty(JSON_FIELD_SUPERGROUP_CHAT_CREATED)
	public final Boolean supergroupChatCreated;

	/**
	 * <em>Optional.</em> Service message: the channel has been created. This
	 * field can‘t be received in a message coming through updates, because bot
	 * can’t be a member of a channel when it is created. It can only be found in
	 * reply_to_message if someone replies to a very first message in a channel.
	 */
	@JsonProperty(JSON_FIELD_CHANNEL_CHAT_CREATED)
	public final Boolean channelChatCreated;

	/**
	 * <em>Optional.</em> The group has been migrated to a supergroup with the
	 * specified identifier. This number may be greater than 32 bits and some
	 * programming languages may have difficulty/silent defects in interpreting
	 * it. But it smaller than 52 bits, so a signed 64 bit integer or
	 * double-precision float type are safe for storing this identifier.
	 */
	@JsonProperty(JSON_FIELD_MIGRATE_TO_CHAT_ID)
	public final Integer migrateToChatId;

	/**
	 * <em>Optional.</em> The supergroup has been migrated from a group with the
	 * specified identifier. This number may be greater than 32 bits and some
	 * programming languages may have difficulty/silent defects in interpreting
	 * it. But it smaller than 52 bits, so a signed 64 bit integer or
	 * double-precision float type are safe for storing this identifier.
	 */
	@JsonProperty(JSON_FIELD_MIGRATE_FROM_CHAT_ID)
	public final Integer migratefromchatId;

	/**
	 * <em>Optional.</em> Specified message was pinned. Note that the Message
	 * object in this field will not contain further reply_to_message fields even
	 * if it is itself a reply.
	 */
	@JsonProperty(JSON_FIELD_PINNED_MESSAGE)
	public final Message pinnedMessage;

	/**
	 * 
	 * @param messageId
	 *          Unique message identifier
	 * @param from
	 *          <em>Optional.</em> Sender, can be empty for messages sent to
	 *          channels
	 * @param date
	 *          Date the message was sent in Unix time
	 * @param chat
	 *          Conversation the message belongs to
	 * @param forwardFrom
	 *          <em>Optional.</em> For forwarded messages, sender of the original
	 *          message
	 * @param forwardFromChat
	 *          <em>Optional.</em> For messages forwarded from a channel,
	 *          information about the original channel
	 * @param forwardDate
	 *          <em>Optional.</em> For forwarded messages, date the original
	 *          message was sent in Unix time
	 * @param replyToMessage
	 *          <em>Optional.</em> For replies, the original message. Note that
	 *          the Message object in this field will not contain further
	 *          replyToMessage fields even if it itself is a reply.
	 * @param editDate
	 *          <em>Optional.</em> Date the message was last edited in Unix time
	 * @param text
	 *          <em>Optional.</em> For text messages, the actual UTF-8 text of the
	 *          message, 0-4096 characters.
	 * @param entities
	 *          <em>Optional.</em> For text messages, special entities like
	 *          usernames, URLs, bot commands, etc. that appear in the text
	 * @param audio
	 *          <em>Optional.</em> Message is an audio file, information about the
	 *          file
	 * @param document
	 *          <em>Optional.</em> Message is a general file, information about
	 *          the file
	 * @param photo
	 *          <em>Optional.</em> Message is a photo, available sizes of the
	 *          photo
	 * @param sticker
	 *          <em>Optional.</em> Message is a sticker, information about the
	 *          sticker
	 * @param video
	 *          <em>Optional.</em> Message is a video, information about the video
	 * @param voice
	 *          <em>Optional.</em> Message is a voice message, information about
	 *          the file
	 * @param caption
	 *          <em>Optional.</em> Caption for the document, photo or video, 0-200
	 *          characters
	 * @param contact
	 *          <em>Optional.</em> Message is a shared contact, information about
	 *          the contact
	 * @param location
	 *          <em>Optional.</em> Message is a shared location, information about
	 *          the location
	 * @param venue
	 *          <em>Optional.</em> Message is a venue, information about the venue
	 * @param newChatMember
	 *          <em>Optional.</em> A new member was added to the group,
	 *          information about them (this member may be the bot itself)
	 * @param leftChatMember
	 *          <em>Optional.</em> A member was removed from the group,
	 *          information about them (this member may be the bot itself)
	 * @param newChatTitle
	 *          <em>Optional.</em> A chat title was changed to this value
	 * @param newChatPhoto
	 *          <em>Optional.</em> A chat photo was change to this value
	 * @param deleteChatPhoto
	 *          <em>Optional.</em> Service message: the chat photo was deleted
	 * @param groupChatCreated
	 *          <em>Optional.</em> Service message: the group has been created
	 * @param supergroupChatCreated
	 *          <em>Optional.</em> Service message: the supergroup has been
	 *          created. This field can‘t be received in a message coming through
	 *          updates, because bot can’t be a member of a supergroup when it is
	 *          created. It can only be found in reply_to_message if someone
	 *          replies to a very first message in a directly created supergroup.
	 * @param channelChatCreated
	 *          <em>Optional.</em> Service message: the channel has been created.
	 *          This field can‘t be received in a message coming through updates,
	 *          because bot can’t be a member of a channel when it is created. It
	 *          can only be found in reply_to_message if someone replies to a very
	 *          first message in a channel.
	 * @param migrateToChatId
	 *          <em>Optional.</em> The group has been migrated to a supergroup
	 *          with the specified identifier. This number may be greater than 32
	 *          bits and some programming languages may have difficulty/silent
	 *          defects in interpreting it. But it smaller than 52 bits, so a
	 *          signed 64 bit integer or double-precision float type are safe for
	 *          storing this identifier.
	 * @param migrateFromChatId
	 *          <em>Optional.</em> The supergroup has been migrated from a group
	 *          with the specified identifier. This number may be greater than 32
	 *          bits and some programming languages may have difficulty/silent
	 *          defects in interpreting it. But it smaller than 52 bits, so a
	 *          signed 64 bit integer or double-precision float type are safe for
	 *          storing this identifier.
	 * @param pinnedMessage
	 *          <em>Optional.</em> Specified message was pinned. Note that the
	 *          Message object in this field will not contain further
	 *          reply_to_message fields even if it is itself a reply.
	 */
	public Message(@JsonProperty(JSON_FIELD_MESSAGE_ID) Integer messageId, @JsonProperty(JSON_FIELD_FROM) User from,
			@JsonProperty(JSON_FIELD_DATE) Integer date, @JsonProperty(JSON_FIELD_CHAT) Chat chat,
			@JsonProperty(JSON_FIELD_FORWARD_FROM) User forwardFrom,
			@JsonProperty(JSON_FIELD_FORWARD_FROM_CHAT) Chat forwardFromChat,
			@JsonProperty(JSON_FIELD_FORWARD_DATE) Integer forwardDate,
			@JsonProperty(JSON_FIELD_REPLY_TO_MESSAGE) Message replyToMessage,
			@JsonProperty(JSON_FIELD_EDIT_DATE) Integer editDate, @JsonProperty(JSON_FIELD_TEXT) String text,
			@JsonProperty(JSON_FIELD_ENTITIES) MessageEntity[] entities, @JsonProperty(JSON_FIELD_AUDIO) Audio audio,
			@JsonProperty(JSON_FIELD_DOCUMENT) Document document, @JsonProperty(JSON_FIELD_PHOTO) PhotoSize[] photo,
			@JsonProperty(JSON_FIELD_STICKER) Sticker sticker, @JsonProperty(JSON_FIELD_VIDEO) Video video,
			@JsonProperty(JSON_FIELD_VOICE) Voice voice, @JsonProperty(JSON_FIELD_CAPTION) String caption,
			@JsonProperty(JSON_FIELD_CONTACT) Contact contact, @JsonProperty(JSON_FIELD_LOCATION) Location location,
			@JsonProperty(JSON_FIELD_VENUE) Venue venue, @JsonProperty(JSON_FIELD_NEW_CHAT_MEMBER) User newChatMember,
			@JsonProperty(JSON_FIELD_LEFT_CHAT_MEMBER) User leftChatMember,
			@JsonProperty(JSON_FIELD_NEW_CHAT_TITLE) String newChatTitle,
			@JsonProperty(JSON_FIELD_NEW_CHAT_PHOTO) PhotoSize[] newChatPhoto,
			@JsonProperty(JSON_FIELD_DELETE_CHAT_PHOTO) Boolean deleteChatPhoto,
			@JsonProperty(JSON_FIELD_GROUP_CHAT_CREATED) Boolean groupChatCreated,
			@JsonProperty(JSON_FIELD_SUPERGROUP_CHAT_CREATED) Boolean supergroupChatCreated,
			@JsonProperty(JSON_FIELD_CHANNEL_CHAT_CREATED) Boolean channelChatCreated,
			@JsonProperty(JSON_FIELD_MIGRATE_TO_CHAT_ID) Integer migrateToChatId,
			@JsonProperty(JSON_FIELD_MIGRATE_FROM_CHAT_ID) Integer migrateFromChatId,
			@JsonProperty(JSON_FIELD_PINNED_MESSAGE) Message pinnedMessage

	) {
		this.messageId = messageId;
		this.from = from;
		this.date = date;
		this.chat = chat;
		this.forwardFrom = forwardFrom;
		this.forwardFromChat = forwardFromChat;
		this.forwardDate = forwardDate;
		this.replyToMessage = replyToMessage;
		this.editDate = editDate;
		this.text = text;
		this.entities = entities;
		this.audio = audio;
		this.document = document;
		this.photo = photo;
		this.sticker = sticker;
		this.video = video;
		this.voice = voice;
		this.caption = caption;
		this.contact = contact;
		this.location = location;
		this.venue = venue;
		this.newChatMember = newChatMember;
		this.leftChatMember = leftChatMember;
		this.newChatTitle = newChatTitle;
		this.newChatPhoto = newChatPhoto;
		this.deleteChatPhoto = deleteChatPhoto;
		this.groupChatCreated = groupChatCreated;
		this.supergroupChatCreated = supergroupChatCreated;
		this.channelChatCreated = channelChatCreated;
		this.migrateToChatId = migrateToChatId;
		this.migratefromchatId = migrateFromChatId;
		this.pinnedMessage = pinnedMessage;

	}

	/**
	 * Utility nested class to parse message fields and infer message content
	 *
	 */
	public static class Util {

		public static class BotCommand {

			public final String command;
			public final String recipient;
			public final String parameters[];

			private BotCommand(String command, String recipient, String[] parameters) {
				this.command = command;
				this.recipient = recipient;
				this.parameters = parameters;
			}

		}

		public static boolean isCommand(Message m) {
			return (m.text != null && m.text.charAt(0) == '/');
		}

		public static BotCommand parseCommand(Message m) {

			BotCommand result = null;

			if (isCommand(m)) {

				String command;
				String recipient;
				String[] parameters;

				String[] cmd_and_params = m.text.split(" ");

				if (cmd_and_params[0].indexOf('@') > 0) {
					String[] cmd_and_botname = cmd_and_params[0].split("@");

					command = cmd_and_botname[0].trim();
					recipient = cmd_and_botname[1].trim();
				} else {
					recipient = null;
					command = cmd_and_params[0].trim();

				}

				if (cmd_and_params.length > 1)
					parameters = Arrays.copyOfRange(cmd_and_params, 1, cmd_and_params.length);
				else
					parameters = null;

				result = new BotCommand(command, recipient, parameters);
			}

			return result;
		}

		public static boolean isReply(Message m) {
			return m.replyToMessage != null;
		}

	}

	/**
	 * Builder class for test purposes (otherwise creating a message by
	 * constructor would be a real pain in the ass)
	 */
	public static class Builder {

		private Integer messageId;
		private User from;
		private Integer date;
		private Chat chat;
		private User forwardFrom;
		private Chat forwardFromChat;
		private Integer forwardDate;
		private Message replyToMessage;
		private Integer editDate;
		private String text;
		private MessageEntity[] entities;
		private Audio audio;
		private Document document;
		private PhotoSize[] photo;
		private Sticker sticker;
		private Video video;
		private Voice voice;
		private String caption;
		private Contact contact;
		private Location location;
		private Venue venue;
		private User newChatMember;
		private User leftChatMember;
		private String newChatTitle;
		private PhotoSize[] newChatPhoto;
		private Boolean deleteChatPhoto;
		private Boolean groupChatCreated;
		private Boolean supergroupChatCreated;
		private Boolean channelChatCreated;
		private Integer migrateToChatId;
		private Integer migrateFromChatId;
		private Message pinnedMessage;

		public Builder() {

		}

		public Message build() {
			return new Message(messageId, from, date, chat, forwardFrom, forwardFromChat, forwardDate, replyToMessage,
					editDate, text, entities, audio, document, photo, sticker, video, voice, caption, contact, location, venue,
					newChatMember, leftChatMember, newChatTitle, newChatPhoto, deleteChatPhoto, groupChatCreated,
					supergroupChatCreated, channelChatCreated, migrateToChatId, migrateFromChatId, pinnedMessage);
		}

		public Builder setmessageId(Integer messageId) {
			this.messageId = messageId;
			return this;
		}

		public Builder setFrom(User from) {
			this.from = from;
			return this;
		}

		public Builder setDate(Integer date) {
			this.date = date;
			return this;
		}

		public Builder setChat(Chat chat) {
			this.chat = chat;
			return this;
		}

		public Builder setForwardFrom(User forwardFrom) {
			this.forwardFrom = forwardFrom;
			return this;
		}

		public Builder setForwardFromChat(Chat forwardFromChat) {
			this.forwardFromChat = forwardFromChat;
			return this;
		}

		public Builder setForwardDate(Integer forwardDate) {
			this.forwardDate = forwardDate;
			return this;
		}

		public Builder setReplyToMessage(Message replyToMessage) {
			this.replyToMessage = replyToMessage;
			return this;
		}

		public Builder setEditDate(Integer editDate) {
			this.editDate = editDate;
			return this;
		}

		public Builder setText(String text) {
			this.text = text;
			return this;
		}

		public Builder setEntities(MessageEntity[] entities) {
			this.entities = entities;
			return this;
		}

		public Builder setAudio(Audio audio) {
			this.audio = audio;
			return this;
		}

		public Builder setDocument(Document document) {
			this.document = document;
			return this;
		}

		public Builder setPhoto(PhotoSize[] photo) {
			this.photo = photo;
			return this;
		}

		public Builder setSticker(Sticker sticker) {
			this.sticker = sticker;
			return this;
		}

		public Builder setVideo(Video video) {
			this.video = video;
			return this;
		}

		public Builder setVoice(Voice voice) {
			this.voice = voice;
			return this;
		}

		public Builder setCaption(String caption) {
			this.caption = caption;
			return this;
		}

		public Builder setContact(Contact contact) {
			this.contact = contact;
			return this;
		}

		public Builder setLocation(Location location) {
			this.location = location;
			return this;
		}

		public Builder setVenue(Venue venue) {
			this.venue = venue;
			return this;
		}

		public Builder setNewChatMember(User newChatMember) {
			this.newChatMember = newChatMember;
			return this;
		}

		public Builder setLeftChatMember(User leftChatMember) {
			this.leftChatMember = leftChatMember;
			return this;
		}

		public Builder setNewChatTitle(String newChatTitle) {
			this.newChatTitle = newChatTitle;
			return this;
		}

		public Builder setNewChatPhoto(PhotoSize[] newChatPhoto) {
			this.newChatPhoto = newChatPhoto;
			return this;
		}

		public Builder setDeleteChatPhoto(Boolean deleteChatPhoto) {
			this.deleteChatPhoto = deleteChatPhoto;
			return this;
		}

		public Builder setGroupChatCreated(Boolean groupChatCreated) {
			this.groupChatCreated = groupChatCreated;
			return this;
		}

		public Builder setSupergroupChatCreated(Boolean supergroupChatCreated) {
			this.supergroupChatCreated = supergroupChatCreated;
			return this;
		}

		public Builder setChannelChatCreated(Boolean channelChatCreated) {
			this.channelChatCreated = channelChatCreated;
			return this;
		}

		public Builder setMigrateToChatId(Integer migrateToChatId) {
			this.migrateToChatId = migrateToChatId;
			return this;
		}

		public Builder setMigratefromChatId(Integer migrateFromChatId) {
			this.migrateFromChatId = migrateFromChatId;
			return this;
		}

		public Builder setPinnedMessage(Message pinnedMessage) {
			this.pinnedMessage = pinnedMessage;
			return this;
		}

	}

}
