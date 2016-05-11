package it.pwned.telegram.bot.api.type;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonDeserialize(using = MessageDeserializer.class)
public class Message {

	public final Integer message_id;
	public final User from;
	public final Integer date;
	public final Chat chat;
	public final User forward_from;
	public final Chat forward_from_chat;
	public final Integer forward_date;
	public final Message reply_to_message;
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
			@JsonProperty("text") String text, @JsonProperty("entities") MessageEntity[] entities,
			@JsonProperty("audio") Audio audio, @JsonProperty("document") Document document,
			@JsonProperty("photo") PhotoSize[] photo, @JsonProperty("sticker") Sticker sticker,
			@JsonProperty("video") Video video, @JsonProperty("voice") Voice voice, @JsonProperty("caption") String caption,
			@JsonProperty("contact") Contact contact, @JsonProperty("location") Location location,
			@JsonProperty("venue") Venue venue, @JsonProperty("new_chat_member") User new_chat_member,
			@JsonProperty("left_chat_member") User left_chat_member, @JsonProperty("new_chat_title") String new_chat_title,
			@JsonProperty("new_chat_photo") PhotoSize[] new_chat_photo,
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
}
