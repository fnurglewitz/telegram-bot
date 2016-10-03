package it.pwned.telegram.bot.api.type.game;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.MessageEntity;
import it.pwned.telegram.bot.api.type.PhotoSize;

/**
 * This object represents a game. Use BotFather to create and edit games, their
 * short names will act as unique identifiers.
 * 
 */
public class Game {

	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_DESCRIPTION = "description";
	private final static String JSON_FIELD_PHOTO = "photo";
	private final static String JSON_FIELD_TEXT = "text";
	private final static String JSON_FIELD_TEXT_ENTITIES = "text_entities";
	private final static String JSON_FIELD_ANIMATION = "animation";

	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	@JsonProperty(JSON_FIELD_DESCRIPTION)
	public final String description;

	@JsonProperty(JSON_FIELD_PHOTO)
	public final List<PhotoSize> photo;

	@JsonProperty(JSON_FIELD_TEXT)
	public final String text;

	@JsonProperty(JSON_FIELD_TEXT_ENTITIES)
	public final List<MessageEntity> textEntities;

	@JsonProperty(JSON_FIELD_ANIMATION)
	public final Animation animation;

	public Game(@JsonProperty(JSON_FIELD_TITLE) String title, @JsonProperty(JSON_FIELD_DESCRIPTION) String description,
			@JsonProperty(JSON_FIELD_PHOTO) List<PhotoSize> photo, @JsonProperty(JSON_FIELD_TEXT) String text,
			@JsonProperty(JSON_FIELD_TEXT_ENTITIES) List<MessageEntity> textEntities,
			@JsonProperty(JSON_FIELD_ANIMATION) Animation animation) {
		this.title = title;
		this.description = description;
		this.photo = photo == null ? null : Collections.unmodifiableList(photo);
		this.text = text;
		this.textEntities = textEntities == null ? null : Collections.unmodifiableList(textEntities);
		this.animation = animation;
	}

}
