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
	public final Integer forward_date;
	public final Message reply_to_message;
	public final String text;
	public final Audio audio;
	public final Document document;
	public final PhotoSize[] photo;
	public final Sticker sticker;
	public final Video video;
	public final Voice voice;
	public final String caption;
	public final Contact contact;
	public final Location location;
	public final User new_chat_participant;
	public final User left_chat_participant;
	public final String new_chat_title;
	public final PhotoSize[] new_chat_photo;
	public final Boolean delete_chat_photo;
	public final Boolean group_chat_created;

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
			@JsonProperty("forward_from") User forward_from, @JsonProperty("forward_date") Integer forward_date,
			@JsonProperty("reply_to_message") Message reply_to_message, @JsonProperty("text") String text,
			@JsonProperty("audio") Audio audio, @JsonProperty("document") Document document,
			@JsonProperty("photo") PhotoSize[] photo, @JsonProperty("sticker") Sticker sticker,
			@JsonProperty("video") Video video, @JsonProperty("voice") Voice voice,
			@JsonProperty("caption") String caption, @JsonProperty("contact") Contact contact,
			@JsonProperty("location") Location location,
			@JsonProperty("new_chat_participant") User new_chat_participant,
			@JsonProperty("left_chat_participant") User left_chat_participant,
			@JsonProperty("new_chat_title") String new_chat_title,
			@JsonProperty("new_chat_photo") PhotoSize[] new_chat_photo,
			@JsonProperty("delete_chat_photo") Boolean delete_chat_photo,
			@JsonProperty("group_chat_created") Boolean group_chat_created) {
		this.message_id = message_id;
		this.from = from;
		this.date = date;
		this.chat = chat;
		this.forward_from = forward_from;
		this.forward_date = forward_date;
		this.reply_to_message = reply_to_message;
		this.text = text;
		this.audio = audio;
		this.document = document;
		this.photo = photo;
		this.sticker = sticker;
		this.video = video;
		this.voice = voice;
		this.caption = caption;
		this.contact = contact;
		this.location = location;
		this.new_chat_participant = new_chat_participant;
		this.left_chat_participant = left_chat_participant;
		this.new_chat_title = new_chat_title;
		this.new_chat_photo = new_chat_photo;
		this.delete_chat_photo = delete_chat_photo;
		this.group_chat_created = group_chat_created;

		// utility fields
		this.is_command = (text != null && text.charAt(0) == '/');
		if (this.is_command) {
			String[] cmd_and_params = text.split(" ");

			if (cmd_and_params[0].indexOf('@') > 0) {
				String[] cmd_and_botname = cmd_and_params[0].split("@");

				this.command = cmd_and_botname[0];
				this.command_recipient = cmd_and_botname[1];
			} else {
				this.command_recipient = null;
				this.command = cmd_and_params[0];

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