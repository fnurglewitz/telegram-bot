package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

/**
 * This class represents a video file.
 *
 */
public final class Video {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_WIDTH = "width";
	private final static String JSON_FIELD_HEIGHT = "height";
	private final static String JSON_FIELD_DURATION = "duration";
	private final static String JSON_FIELD_THUMB = "thumb";
	private final static String JSON_FIELD_MIME_TYPE = "mime_type";
	private final static String JSON_FIELD_FILE_SIZE = "file_size";

	/**
	 * Unique identifier for this file
	 */
	@JsonProperty(JSON_FIELD_FILE_ID)
	public final String fileId;

	/**
	 * Video width as defined by sender
	 */
	@JsonProperty(JSON_FIELD_WIDTH)
	public final Integer width;

	/**
	 * Video height as defined by sender
	 */
	@JsonProperty(JSON_FIELD_HEIGHT)
	public final Integer height;

	/**
	 * Duration of the video in seconds as defined by sender
	 */
	@JsonProperty(JSON_FIELD_DURATION)
	public final Integer duration;

	/**
	 * <em>Optional.</em> Video thumbnail
	 */
	@JsonProperty(JSON_FIELD_THUMB)
	public final PhotoSize thumb;

	/**
	 * <em>Optional.</em> Mime type of a file as defined by sender
	 */
	@JsonProperty(JSON_FIELD_MIME_TYPE)
	public final String mimeType;

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
	 *          Video width as defined by sender
	 * @param height
	 *          Video height as defined by sender
	 * @param duration
	 *          Duration of the video in seconds as defined by sender
	 * @param thumb
	 *          <em>Optional.</em> Video thumbnail
	 * @param mimeType
	 *          <em>Optional.</em> Mime type of a file as defined by sender
	 * @param fileSize
	 *          <em>Optional.</em> File size
	 */
	public Video(@JsonProperty(JSON_FIELD_FILE_ID) String fileId, @JsonProperty(JSON_FIELD_WIDTH) Integer width,
			@JsonProperty(JSON_FIELD_HEIGHT) Integer height, @JsonProperty(JSON_FIELD_DURATION) Integer duration,
			@JsonProperty(JSON_FIELD_THUMB) PhotoSize thumb, @JsonProperty(JSON_FIELD_MIME_TYPE) String mimeType,
			@JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize) {

		this.fileId = fileId;
		this.width = width;
		this.height = height;
		this.duration = duration;
		this.thumb = thumb;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}

}
