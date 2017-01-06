package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents one size of a photo or a {@link Document} /
 * {@link Sticker} thumbnail.
 *
 */
public final class PhotoSize implements Comparable<PhotoSize> {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_WIDTH = "width";
	private final static String JSON_FIELD_HEIGHT = "height";
	private final static String JSON_FIELD_FILE_SIZE = "file_size";

	/**
	 * Unique identifier for this file
	 */
	@JsonProperty(JSON_FIELD_FILE_ID)
	public final String fileId;

	/**
	 * Photo width
	 */
	@JsonProperty(JSON_FIELD_WIDTH)
	public final Integer width;

	/**
	 * Photo height
	 */
	@JsonProperty(JSON_FIELD_HEIGHT)
	public final Integer height;

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
	 *          Photo width
	 * @param height
	 *          Photo height
	 * @param fileSize
	 *          <em>Optional.</em> File size
	 */
	public PhotoSize(@JsonProperty(JSON_FIELD_FILE_ID) String fileId, @JsonProperty(JSON_FIELD_WIDTH) Integer width,
			@JsonProperty(JSON_FIELD_HEIGHT) Integer height, @JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize) {
		this.fileId = fileId;
		this.width = width;
		this.height = height;
		this.fileSize = fileSize;
	}

	@Override
	public int compareTo(PhotoSize other) {
		Integer thisImage = this.width * this.height;
		Integer thatImage = other.width * other.height;

		return thisImage.compareTo(thatImage);
	}
}
