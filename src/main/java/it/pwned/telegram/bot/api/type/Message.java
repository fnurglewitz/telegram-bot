package it.pwned.telegram.bot.api.type;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.game.Game;
import it.pwned.telegram.bot.api.type.payment.Invoice;
import it.pwned.telegram.bot.api.type.payment.SuccessfulPayment;

/**
 * This class represents a message.
 */
public class Message {

    private final static String JSON_FIELD_MESSAGE_ID = "message_id";
    private final static String JSON_FIELD_FROM = "from";
    private final static String JSON_FIELD_DATE = "date";
    private final static String JSON_FIELD_CHAT = "chat";
    private final static String JSON_FIELD_FORWARD_FROM = "forward_from";
    private final static String JSON_FIELD_FORWARD_FROM_CHAT = "forward_from_chat";
    private final static String JSON_FIELD_FORWARD_FROM_MESSAGE_ID = "forward_from_message_id";
    private final static String JSON_FIELD_FORWARD_SIGNATURE = "forward_signature";
    private final static String JSON_FIELD_FORWARD_DATE = "forward_date";
    private final static String JSON_FIELD_REPLY_TO_MESSAGE = "reply_to_message";
    private final static String JSON_FIELD_EDIT_DATE = "edit_date";
    private final static String JSON_FIELD_AUTHOR_SIGNATURE = "author_signature";
    private final static String JSON_FIELD_TEXT = "text";
    private final static String JSON_FIELD_ENTITIES = "entities";
    private final static String JSON_FIELD_CAPTION_ENTITIES = "caption_entities";
    private final static String JSON_FIELD_AUDIO = "audio";
    private final static String JSON_FIELD_DOCUMENT = "document";
    private final static String JSON_FIELD_GAME = "game";
    private final static String JSON_FIELD_PHOTO = "photo";
    private final static String JSON_FIELD_STICKER = "sticker";
    private final static String JSON_FIELD_VIDEO = "video";
    private final static String JSON_FIELD_VOICE = "voice";
    private final static String JSON_FIELD_VIDEO_NOTE = "video_note";
    private final static String JSON_FIELD_CAPTION = "caption";
    private final static String JSON_FIELD_CONTACT = "contact";
    private final static String JSON_FIELD_LOCATION = "location";
    private final static String JSON_FIELD_VENUE = "venue";
    private final static String JSON_FIELD_NEW_CHAT_MEMBERS = "new_chat_members";
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
    private final static String JSON_FIELD_INVOICE = "invoice";
    private final static String JSON_FIELD_SUCCESSFUL_PAYMENT = "successful_payment";
    private final static String JSON_FIELD_CONNECTED_WEBSITE = "connected_website";

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
     * <em>Optional.</em> For forwarded channel posts, identifier of the original
     * message in the channel
     */
    @JsonProperty(JSON_FIELD_FORWARD_FROM_MESSAGE_ID)
    public final Integer forwardFromMessageID;

    /**
     * <em>Optional.</em> For messages forwarded from channels, signature of the post author if present
     */
    @JsonProperty(JSON_FIELD_FORWARD_SIGNATURE)
    public final String forwardSignature;

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
     * <em>Optional.</em> Signature of the post author for messages in channels
     */
    @JsonProperty(JSON_FIELD_AUTHOR_SIGNATURE)
    public final String authorSignature;

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
    public final List<MessageEntity> entities;

    /**
     * <em>Optional.</em> For messages with a caption, special entities like usernames, URLs,
     * bot commands, etc. that appear in the caption
     */
    @JsonProperty(JSON_FIELD_CAPTION_ENTITIES)
    public final List<MessageEntity> captionEntities;

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
     * <em>Optional.</em> Message is a game, information about the game
     */
    @JsonProperty(JSON_FIELD_GAME)
    public final Game game;

    /**
     * <em>Optional.</em> Message is a photo, available sizes of the photo
     */
    @JsonProperty(JSON_FIELD_PHOTO)
    public final List<PhotoSize> photo;

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
     * <em>Optional.</em> Message is a video note, information about the video message
     */
    @JsonProperty(JSON_FIELD_VIDEO_NOTE)
    public final VideoNote videoNote;

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
    @JsonProperty(JSON_FIELD_NEW_CHAT_MEMBERS)
    public final List<User> newChatMembers;

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
    public final List<PhotoSize> newChatPhoto;

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
     * <em>Optional.</em> Message is an invoice for a payment, information about the invoice.
     */
    @JsonProperty(JSON_FIELD_INVOICE)
    public final Invoice invoice;

    /**
     * <em>Optional.</em> Message is a service message about a successful payment, information about the payment.
     */
    @JsonProperty(JSON_FIELD_SUCCESSFUL_PAYMENT)
    public final SuccessfulPayment successfulPayment;

    /**
     * <em>Optional.</em> The domain name of the website on which the user has logged in.
     */
    @JsonProperty(JSON_FIELD_CONNECTED_WEBSITE)
    public final String connectedWebsite;

    /**
     * @param messageId             Unique message identifier
     * @param from                  <em>Optional.</em> Sender, can be empty for messages sent to
     *                              channels
     * @param date                  Date the message was sent in Unix time
     * @param chat                  Conversation the message belongs to
     * @param forwardFrom           <em>Optional.</em> For forwarded messages, sender of the original
     *                              message
     * @param forwardFromChat       <em>Optional.</em> For messages forwarded from a channel,
     *                              information about the original channel
     * @param forwardFromMessageID  <em>Optional.</em> For forwarded channel posts, identifier of the
     *                              original message in the channel
     * @param forwardSignature      <em>Optional.</em> For messages forwarded from channels, signature of the post author if present
     * @param forwardDate           <em>Optional.</em> For forwarded messages, date the original
     *                              message was sent in Unix time
     * @param replyToMessage        <em>Optional.</em> For replies, the original message. Note that
     *                              the Message object in this field will not contain further
     *                              replyToMessage fields even if it itself is a reply.
     * @param editDate              <em>Optional.</em> Date the message was last edited in Unix time
     * @param authorSignature       <em>Optional.</em> Signature of the post author for messages in channels
     * @param text                  <em>Optional.</em> For text messages, the actual UTF-8 text of the
     *                              message, 0-4096 characters.
     * @param entities              <em>Optional.</em> For text messages, special entities like
     *                              usernames, URLs, bot commands, etc. that appear in the text
     * @param captionEntities       <em>Optional.</em> For messages with a caption, special entities like usernames,
     *                              URLs, bot commands, etc. that appear in the caption
     * @param audio                 <em>Optional.</em> Message is an audio file, information about the
     *                              file
     * @param document              <em>Optional.</em> Message is a general file, information about
     *                              the file
     * @param game                  <em>Optional.</em> Message is a game, information about the game
     * @param photo                 <em>Optional.</em> Message is a photo, available sizes of the
     *                              photo
     * @param sticker               <em>Optional.</em> Message is a sticker, information about the
     *                              sticker
     * @param video                 <em>Optional.</em> Message is a video, information about the video
     * @param voice                 <em>Optional.</em> Message is a voice message, information about
     *                              the file
     * @param videoNote             <em>Optional.</em> Message is a video note, information about the video message
     * @param caption               <em>Optional.</em> Caption for the document, photo or video, 0-200
     *                              characters
     * @param contact               <em>Optional.</em> Message is a shared contact, information about
     *                              the contact
     * @param location              <em>Optional.</em> Message is a shared location, information about
     *                              the location
     * @param venue                 <em>Optional.</em> Message is a venue, information about the venue
     * @param newChatMembers        <em>Optional.</em> New members that were added to the group or supergroup and information about them (the bot itself may be one of these members)
     * @param leftChatMember        <em>Optional.</em> A member was removed from the group,
     *                              information about them (this member may be the bot itself)
     * @param newChatTitle          <em>Optional.</em> A chat title was changed to this value
     * @param newChatPhoto          <em>Optional.</em> A chat photo was change to this value
     * @param deleteChatPhoto       <em>Optional.</em> Service message: the chat photo was deleted
     * @param groupChatCreated      <em>Optional.</em> Service message: the group has been created
     * @param supergroupChatCreated <em>Optional.</em> Service message: the supergroup has been
     *                              created. This field can‘t be received in a message coming through
     *                              updates, because bot can’t be a member of a supergroup when it is
     *                              created. It can only be found in reply_to_message if someone
     *                              replies to a very first message in a directly created supergroup.
     * @param channelChatCreated    <em>Optional.</em> Service message: the channel has been created.
     *                              This field can‘t be received in a message coming through updates,
     *                              because bot can’t be a member of a channel when it is created. It
     *                              can only be found in reply_to_message if someone replies to a very
     *                              first message in a channel.
     * @param migrateToChatId       <em>Optional.</em> The group has been migrated to a supergroup
     *                              with the specified identifier. This number may be greater than 32
     *                              bits and some programming languages may have difficulty/silent
     *                              defects in interpreting it. But it smaller than 52 bits, so a
     *                              signed 64 bit integer or double-precision float type are safe for
     *                              storing this identifier.
     * @param migrateFromChatId     <em>Optional.</em> The supergroup has been migrated from a group
     *                              with the specified identifier. This number may be greater than 32
     *                              bits and some programming languages may have difficulty/silent
     *                              defects in interpreting it. But it smaller than 52 bits, so a
     *                              signed 64 bit integer or double-precision float type are safe for
     *                              storing this identifier.
     * @param pinnedMessage         <em>Optional.</em> Specified message was pinned. Note that the
     *                              Message object in this field will not contain further
     *                              reply_to_message fields even if it is itself a reply.
     * @param invoice               <em>Optional.</em> Message is an invoice for a payment, information about the invoice.
     * @param successfulPayment     <em>Optional.</em> Message is a service message about a successful payment, information about the payment.
     * @param connectedWebsite      <em>Optional.</em> The domain name of the website on which the user has logged in.
     */
    public Message(@JsonProperty(JSON_FIELD_MESSAGE_ID) Integer messageId,
                   @JsonProperty(JSON_FIELD_FROM) User from,
                   @JsonProperty(JSON_FIELD_DATE) Integer date,
                   @JsonProperty(JSON_FIELD_CHAT) Chat chat,
                   @JsonProperty(JSON_FIELD_FORWARD_FROM) User forwardFrom,
                   @JsonProperty(JSON_FIELD_FORWARD_FROM_CHAT) Chat forwardFromChat,
                   @JsonProperty(JSON_FIELD_FORWARD_FROM_MESSAGE_ID) Integer forwardFromMessageID,
                   @JsonProperty(JSON_FIELD_FORWARD_SIGNATURE) String forwardSignature,
                   @JsonProperty(JSON_FIELD_FORWARD_DATE) Integer forwardDate,
                   @JsonProperty(JSON_FIELD_REPLY_TO_MESSAGE) Message replyToMessage,
                   @JsonProperty(JSON_FIELD_EDIT_DATE) Integer editDate,
                   @JsonProperty(JSON_FIELD_AUTHOR_SIGNATURE) String authorSignature,
                   @JsonProperty(JSON_FIELD_TEXT) String text,
                   @JsonProperty(JSON_FIELD_ENTITIES) List<MessageEntity> entities,
                   @JsonProperty(JSON_FIELD_CAPTION_ENTITIES) List<MessageEntity> captionEntities,
                   @JsonProperty(JSON_FIELD_AUDIO) Audio audio,
                   @JsonProperty(JSON_FIELD_DOCUMENT) Document document,
                   @JsonProperty(JSON_FIELD_GAME) Game game,
                   @JsonProperty(JSON_FIELD_PHOTO) List<PhotoSize> photo,
                   @JsonProperty(JSON_FIELD_STICKER) Sticker sticker,
                   @JsonProperty(JSON_FIELD_VIDEO) Video video,
                   @JsonProperty(JSON_FIELD_VOICE) Voice voice,
                   @JsonProperty(JSON_FIELD_VIDEO_NOTE) VideoNote videoNote,
                   @JsonProperty(JSON_FIELD_CAPTION) String caption,
                   @JsonProperty(JSON_FIELD_CONTACT) Contact contact,
                   @JsonProperty(JSON_FIELD_LOCATION) Location location,
                   @JsonProperty(JSON_FIELD_VENUE) Venue venue,
                   @JsonProperty(JSON_FIELD_NEW_CHAT_MEMBERS) List<User> newChatMembers,
                   @JsonProperty(JSON_FIELD_LEFT_CHAT_MEMBER) User leftChatMember,
                   @JsonProperty(JSON_FIELD_NEW_CHAT_TITLE) String newChatTitle,
                   @JsonProperty(JSON_FIELD_NEW_CHAT_PHOTO) List<PhotoSize> newChatPhoto,
                   @JsonProperty(JSON_FIELD_DELETE_CHAT_PHOTO) Boolean deleteChatPhoto,
                   @JsonProperty(JSON_FIELD_GROUP_CHAT_CREATED) Boolean groupChatCreated,
                   @JsonProperty(JSON_FIELD_SUPERGROUP_CHAT_CREATED) Boolean supergroupChatCreated,
                   @JsonProperty(JSON_FIELD_CHANNEL_CHAT_CREATED) Boolean channelChatCreated,
                   @JsonProperty(JSON_FIELD_MIGRATE_TO_CHAT_ID) Integer migrateToChatId,
                   @JsonProperty(JSON_FIELD_MIGRATE_FROM_CHAT_ID) Integer migrateFromChatId,
                   @JsonProperty(JSON_FIELD_PINNED_MESSAGE) Message pinnedMessage,
                   @JsonProperty(JSON_FIELD_INVOICE) Invoice invoice,
                   @JsonProperty(JSON_FIELD_SUCCESSFUL_PAYMENT) SuccessfulPayment successfulPayment,
                   @JsonProperty(JSON_FIELD_CONNECTED_WEBSITE) String connectedWebsite

    ) {
        this.messageId = messageId;
        this.from = from;
        this.date = date;
        this.chat = chat;
        this.forwardFrom = forwardFrom;
        this.forwardFromChat = forwardFromChat;
        this.forwardFromMessageID = forwardFromMessageID;
        this.forwardSignature = forwardSignature;
        this.forwardDate = forwardDate;
        this.replyToMessage = replyToMessage;
        this.editDate = editDate;
        this.authorSignature = authorSignature;
        this.text = text;
        this.entities = entities == null ? null : Collections.unmodifiableList(entities);
        this.captionEntities = captionEntities == null ? null : Collections.unmodifiableList(captionEntities);
        this.audio = audio;
        this.document = document;
        this.game = game;
        this.photo = photo == null ? null : Collections.unmodifiableList(photo);
        this.sticker = sticker;
        this.video = video;
        this.voice = voice;
        this.videoNote = videoNote;
        this.caption = caption;
        this.contact = contact;
        this.location = location;
        this.venue = venue;
        this.newChatMembers = newChatMembers == null ? null : Collections.unmodifiableList(newChatMembers);
        this.leftChatMember = leftChatMember;
        this.newChatTitle = newChatTitle;
        this.newChatPhoto = newChatPhoto == null ? null : Collections.unmodifiableList(newChatPhoto);
        this.deleteChatPhoto = deleteChatPhoto;
        this.groupChatCreated = groupChatCreated;
        this.supergroupChatCreated = supergroupChatCreated;
        this.channelChatCreated = channelChatCreated;
        this.migrateToChatId = migrateToChatId;
        this.migratefromchatId = migrateFromChatId;
        this.pinnedMessage = pinnedMessage;
        this.invoice = invoice;
        this.successfulPayment = successfulPayment;
        this.connectedWebsite = connectedWebsite;
    }

    /**
     * Utility nested class to parse message fields and infer message content
     */
    public static class Util {

        private static final String[] EMPTY_STRING_ARRAY = new String[0];

        public static class BotCommand {

            public final User user;
            public final Chat chat;
            public final String command;
            public final String recipient;
            public final String parameters[];

            private BotCommand(User user, Chat chat, String command, String recipient, String[] parameters) {
                this.user = user;
                this.chat = chat;
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
                    parameters = EMPTY_STRING_ARRAY;

                result = new BotCommand(m.from, m.chat, command, recipient, parameters);
            }

            return result;
        }

        public static boolean isReply(Message m) {
            return m.replyToMessage != null;
        }

        public static boolean hasText(Message m) {
            return m.text != null;
        }

        public static boolean hasAudio(Message m) {
            return m.audio != null;
        }

        public static boolean hasDocument(Message m) {
            return m.document != null;
        }

        public static boolean hasPhoto(Message m) {
            return m.photo != null;
        }

        public static boolean hasSticker(Message m) {
            return m.sticker != null;
        }

        public static boolean hasVideo(Message m) {
            return m.video != null;
        }

        public static boolean hasVoice(Message m) {
            return m.voice != null;
        }

        public static boolean hasVideoNote(Message m) {
            return m.videoNote != null;
        }

        public static boolean hasLocation(Message m) {
            return m.location != null;
        }

        public static boolean hasVenue(Message m) {
            return m.venue != null;
        }

        public static boolean isForward(Message m) {
            return m.forwardFrom != null || m.forwardFromChat != null;
        }

        public static List<String> getEntitiesByType(Message m, MessageEntityType t) {

            List<String> entities = new LinkedList<>();

            if (Util.hasText(m) && m.entities != null && m.entities.size() > 0) {

                for (MessageEntity ent : m.entities) {

                    if (ent.type == t) {
                        entities.add(m.text.substring(ent.offset, ent.offset + ent.length));
                    }
                }
            }

            return entities;
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
        private Integer forwardFromMessageID;
        private String forwardSignature;
        private Integer forwardDate;
        private Message replyToMessage;
        private Integer editDate;
        private String authorSignature;
        private String text;
        private List<MessageEntity> entities;
        private List<MessageEntity> captionEntities;
        private Audio audio;
        private Document document;
        private Game game;
        private List<PhotoSize> photo;
        private Sticker sticker;
        private Video video;
        private Voice voice;
        private VideoNote videoNote;
        private String caption;
        private Contact contact;
        private Location location;
        private Venue venue;
        private List<User> newChatMembers;
        private User leftChatMember;
        private String newChatTitle;
        private List<PhotoSize> newChatPhoto;
        private Boolean deleteChatPhoto;
        private Boolean groupChatCreated;
        private Boolean supergroupChatCreated;
        private Boolean channelChatCreated;
        private Integer migrateToChatId;
        private Integer migrateFromChatId;
        private Message pinnedMessage;
        private Invoice invoice;
        private SuccessfulPayment successfulPayment;
        private String connectedWebsite;

        public Builder() {

        }

        public Message build() {
            return new Message(messageId, from, date, chat, forwardFrom, forwardFromChat, forwardFromMessageID, forwardSignature, forwardDate,
                    replyToMessage, editDate, authorSignature, text, entities, captionEntities, audio, document, game, photo, sticker, video, voice, videoNote, caption,
                    contact, location, venue, newChatMembers, leftChatMember, newChatTitle, newChatPhoto, deleteChatPhoto,
                    groupChatCreated, supergroupChatCreated, channelChatCreated, migrateToChatId, migrateFromChatId,
                    pinnedMessage, invoice, successfulPayment, connectedWebsite);
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

        public Builder setForwardFromMessageId(Integer forwardFromMessageID) {
            this.forwardFromMessageID = forwardFromMessageID;
            return this;
        }

        public Builder setForwardSignature(String forwardSignature) {
            this.forwardSignature = forwardSignature;
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

        public Builder setAuthorSignature(String authorSignature) {
            this.authorSignature = authorSignature;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setEntities(List<MessageEntity> entities) {
            this.entities = entities;
            return this;
        }

        public Builder setCaptionEntities(List<MessageEntity> captionEntities) {
            this.captionEntities = captionEntities;
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

        public Builder setGame(Game game) {
            this.game = game;
            return this;
        }

        public Builder setPhoto(List<PhotoSize> photo) {
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

        public Builder setVideoNote(VideoNote videoNote) {
            this.videoNote = videoNote;
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

        public Builder setNewChatMembers(List<User> newChatMembers) {
            this.newChatMembers = newChatMembers;
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

        public Builder setNewChatPhoto(List<PhotoSize> newChatPhoto) {
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

        public Builder setInvoice(Invoice invoice) {
            this.invoice = invoice;
            return this;
        }

        public Builder setSuccessfulPayment(SuccessfulPayment successfulPayment) {
            this.successfulPayment = successfulPayment;
            return this;
        }

        public Builder setConnectedWebsite(String connectedWebsite) {
            this.connectedWebsite = connectedWebsite;
            return this;
        }
    }

}
