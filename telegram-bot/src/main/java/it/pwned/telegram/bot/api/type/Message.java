package it.pwned.telegram.bot.api.type;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

	public final Integer message_id;
	public final User from;
	public final Integer date;
	public final Chat chat;
	public final User forward_from;
	public final Chat forward_from_chat;
	public final Integer forward_date;
	public final Message reply_to_message;
	public final Integer edit_date;
	public final String text;
	public final MessageEntity[] entities;
	public final Audio audio;
	public final Document document;
	public final PhotoSize[] photo;
	public final Sticker sticker;
	public final Video video;
	public final Voice voice;
	public final String caption;
	public final Contact contact;
	public final Location location;
	public final Venue venue;
	public final User new_chat_member;
	public final User left_chat_member;
	public final String new_chat_title;
	public final PhotoSize[] new_chat_photo;
	public final Boolean delete_chat_photo;
	public final Boolean group_chat_created;
	public final Boolean supergroup_chat_created;
	public final Boolean channel_chat_created;
	public final Integer migrate_to_chat_id;
	public final Integer migrate_from_chat_id;
	public final Message pinned_message;

	// utility fields
	@JsonIgnore
	public final Boolean is_command;
	@JsonIgnore
	public final String command;
	@JsonIgnore
	public final String command_recipient;
	@JsonIgnore
	public final String[] command_parameters;
	@JsonIgnore
	public final Boolean is_reply;

	public Message(@JsonProperty("message_id") Integer message_id, @JsonProperty("from") User from,
			@JsonProperty("date") Integer date, @JsonProperty("chat") Chat chat,
			@JsonProperty("forward_from") User forward_from, @JsonProperty("forward_from_chat") Chat forward_from_chat,
			@JsonProperty("forward_date") Integer forward_date, @JsonProperty("reply_to_message") Message reply_to_message,
			@JsonProperty("edit_date") Integer edit_date, @JsonProperty("text") String text,
			@JsonProperty("entities") MessageEntity[] entities, @JsonProperty("audio") Audio audio,
			@JsonProperty("document") Document document, @JsonProperty("photo") PhotoSize[] photo,
			@JsonProperty("sticker") Sticker sticker, @JsonProperty("video") Video video, @JsonProperty("voice") Voice voice,
			@JsonProperty("caption") String caption, @JsonProperty("contact") Contact contact,
			@JsonProperty("location") Location location, @JsonProperty("venue") Venue venue,
			@JsonProperty("new_chat_member") User new_chat_member, @JsonProperty("left_chat_member") User left_chat_member,
			@JsonProperty("new_chat_title") String new_chat_title, @JsonProperty("new_chat_photo") PhotoSize[] new_chat_photo,
			@JsonProperty("delete_chat_photo") Boolean delete_chat_photo,
			@JsonProperty("group_chat_created") Boolean group_chat_created,
			@JsonProperty("supergroup_chat_created") Boolean supergroup_chat_created,
			@JsonProperty("channel_chat_created") Boolean channel_chat_created,
			@JsonProperty("migrate_to_chat_id") Integer migrate_to_chat_id,
			@JsonProperty("migrate_from_chat_id") Integer migrate_from_chat_id,
			@JsonProperty("pinned_message") Message pinned_message

	) {
		this.message_id = message_id;
		this.from = from;
		this.date = date;
		this.chat = chat;
		this.forward_from = forward_from;
		this.forward_from_chat = forward_from_chat;
		this.forward_date = forward_date;
		this.reply_to_message = reply_to_message;
		this.edit_date = edit_date;
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
		this.new_chat_member = new_chat_member;
		this.left_chat_member = left_chat_member;
		this.new_chat_title = new_chat_title;
		this.new_chat_photo = new_chat_photo;
		this.delete_chat_photo = delete_chat_photo;
		this.group_chat_created = group_chat_created;
		this.supergroup_chat_created = supergroup_chat_created;
		this.channel_chat_created = channel_chat_created;
		this.migrate_to_chat_id = migrate_to_chat_id;
		this.migrate_from_chat_id = migrate_from_chat_id;
		this.pinned_message = pinned_message;

		// utility fields
		this.is_command = (text != null && text.charAt(0) == '/');
		if (this.is_command) {
			String[] cmd_and_params = text.split(" ");

			if (cmd_and_params[0].indexOf('@') > 0) {
				String[] cmd_and_botname = cmd_and_params[0].split("@");

				this.command = cmd_and_botname[0].trim();
				this.command_recipient = cmd_and_botname[1].trim();
			} else {
				this.command_recipient = null;
				this.command = cmd_and_params[0].trim();

			}

			if (cmd_and_params.length > 1)
				this.command_parameters = Arrays.copyOfRange(cmd_and_params, 1, cmd_and_params.length);
			else
				this.command_parameters = null;
		} else {
			this.command = null;
			this.command_recipient = null;
			this.command_parameters = null;
		}

		this.is_reply = reply_to_message != null ? true : false;
	}

	/*
	 * Builder class for test purposes (otherwise creating a message could be a
	 * real pain in the ass
	 */
	public static class Builder {

		private Integer message_id;
		private User from;
		private Integer date;
		private Chat chat;
		private User forward_from;
		private Chat forward_from_chat;
		private Integer forward_date;
		private Message reply_to_message;
		private Integer edit_date;
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
		private User new_chat_member;
		private User left_chat_member;
		private String new_chat_title;
		private PhotoSize[] new_chat_photo;
		private Boolean delete_chat_photo;
		private Boolean group_chat_created;
		private Boolean supergroup_chat_created;
		private Boolean channel_chat_created;
		private Integer migrate_to_chat_id;
		private Integer migrate_from_chat_id;
		private Message pinned_message;

		public Builder() {

		}

		public Message build() {
			return new Message(message_id, from, date, chat, forward_from, forward_from_chat, forward_date, reply_to_message,
					edit_date, text, entities, audio, document, photo, sticker, video, voice, caption, contact, location, venue,
					new_chat_member, left_chat_member, new_chat_title, new_chat_photo, delete_chat_photo, group_chat_created,
					supergroup_chat_created, channel_chat_created, migrate_to_chat_id, migrate_from_chat_id, pinned_message);
		}

		public Builder setmessageId(Integer message_id) {
			this.message_id = message_id;
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

		public Builder setForwardFrom(User forward_from) {
			this.forward_from = forward_from;
			return this;
		}

		public Builder setForwardFromChat(Chat forward_from_chat) {
			this.forward_from_chat = forward_from_chat;
			return this;
		}

		public Builder setForwardDate(Integer forward_date) {
			this.forward_date = forward_date;
			return this;
		}

		public Builder setReplyToMessage(Message reply_to_message) {
			this.reply_to_message = reply_to_message;
			return this;
		}

		public Builder setEditDate(Integer edit_date) {
			this.edit_date = edit_date;
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

		public Builder setNewChatMember(User new_chat_member) {
			this.new_chat_member = new_chat_member;
			return this;
		}

		public Builder setLeftChatMember(User left_chat_member) {
			this.left_chat_member = left_chat_member;
			return this;
		}

		public Builder setNewChatTitle(String new_chat_title) {
			this.new_chat_title = new_chat_title;
			return this;
		}

		public Builder setNewChatPhoto(PhotoSize[] new_chat_photo) {
			this.new_chat_photo = new_chat_photo;
			return this;
		}

		public Builder setDeleteChatPhoto(Boolean delete_chat_photo) {
			this.delete_chat_photo = delete_chat_photo;
			return this;
		}

		public Builder setGroupChatCreated(Boolean group_chat_created) {
			this.group_chat_created = group_chat_created;
			return this;
		}

		public Builder setSupergroupChatCreated(Boolean supergroup_chat_created) {
			this.supergroup_chat_created = supergroup_chat_created;
			return this;
		}

		public Builder setChannelChatCreated(Boolean channel_chat_created) {
			this.channel_chat_created = channel_chat_created;
			return this;
		}

		public Builder setMigrateToChatId(Integer migrate_to_chat_id) {
			this.migrate_to_chat_id = migrate_to_chat_id;
			return this;
		}

		public Builder setMigratefromChatId(Integer migrate_from_chat_id) {
			this.migrate_from_chat_id = migrate_from_chat_id;
			return this;
		}

		public Builder setPinnedMessage(Message pinned_message) {
			this.pinned_message = pinned_message;
			return this;
		}

	}

}
