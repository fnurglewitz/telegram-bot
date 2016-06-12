package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

/**
 * This class represents a sticker.
 *
 */
public final class Sticker {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_WIDTH = "width";
	private final static String JSON_FIELD_HEIGHT = "height";
	private final static String JSON_FIELD_THUMB = "thumb";
	private final static String JSON_FIELD_EMOJI = "emoji";
	private final static String JSON_FIELD_FILE_SIZE = "file_size";

	/**
	 * Unique identifier for this file
	 */
	@JsonProperty(JSON_FIELD_FILE_ID)
	public final String fileId;

	/**
	 * Sticker width
	 */
	@JsonProperty(JSON_FIELD_WIDTH)
	public final Integer width;

	/**
	 * Sticker height
	 */
	@JsonProperty(JSON_FIELD_HEIGHT)
	public final Integer height;

	/**
	 * <em>Optional.</em> Sticker thumbnail in .webp or .jpg format
	 */
	@JsonProperty(JSON_FIELD_THUMB)
	public final PhotoSize thumb;

	/**
	 * <em>Optional.</em> Emoji associated with the sticker
	 */
	@JsonProperty(JSON_FIELD_EMOJI)
	public final String emoji;

	/**
	 * <em>Optional.</em> File size
	 */
	@JsonProperty(JSON_FIELD_FILE_SIZE)
	public final Integer fileSize;

	/**
	 * 
	 * @param fileId
	 *          Unique identifier for this file
	 * @param width
	 *          Sticker width
	 * @param height
	 *          Sticker height
	 * @param thumb
	 *          <em>Optional.</em> Sticker thumbnail in .webp or .jpg format
	 * @param emoji
	 *          <em>Optional.</em> Emoji associated with the sticker
	 * @param fileSize
	 *          <em>Optional.</em> File size
	 */
	public Sticker(@JsonProperty(JSON_FIELD_FILE_ID) String fileId, @JsonProperty(JSON_FIELD_WIDTH) Integer width,
			@JsonProperty(JSON_FIELD_HEIGHT) Integer height, @JsonProperty(JSON_FIELD_THUMB) PhotoSize thumb,
			@JsonProperty(JSON_FIELD_EMOJI) String emoji, @JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize) {
		this.fileId = fileId;
		this.width = width;
		this.height = height;
		this.thumb = thumb;
		this.emoji = emoji;
		this.fileSize = fileSize;
	}

}
