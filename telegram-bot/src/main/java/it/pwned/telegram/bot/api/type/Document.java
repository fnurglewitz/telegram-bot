package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

/**
 * This class represents a general file (as opposed to photos, voice messages
 * and audio files).
 *
 */
public final class Document {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_THUMB = "thumb";
	private final static String JSON_FIELD_FILE_NAME = "file_name";
	private final static String JSON_FIELD_MIME_TYPE = "mime_type";
	private final static String JSON_FIELD_FILE_SIZE = "file_size";

	/**
	 * Unique file identifier
	 */
	@JsonProperty(JSON_FIELD_FILE_ID)
	public final String fileId;

	/**
	 * <em>Optional.</em> Document thumbnail as defined by sender
	 */
	@JsonProperty(JSON_FIELD_THUMB)
	public final PhotoSize thumb;

	/**
	 * <em>Optional.</em> Original filename as defined by sender
	 */
	@JsonProperty(JSON_FIELD_FILE_NAME)
	public final String fileName;

	/**
	 * <em>Optional.</em> MIME type of the file as defined by sender
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
	 *            Unique file identifier
	 * @param thumb
	 *            <em>Optional.</em> Document thumbnail as defined by sender
	 * @param fileName
	 *            <em>Optional.</em> Original filename as defined by sender
	 * @param mimeType
	 *            <em>Optional.</em> MIME type of the file as defined by sender
	 * @param fileSize
	 *            <em>Optional.</em> File size
	 */
	public Document(@JsonProperty(JSON_FIELD_FILE_ID) String fileId, @JsonProperty(JSON_FIELD_THUMB) PhotoSize thumb,
			@JsonProperty(JSON_FIELD_FILE_NAME) String fileName, @JsonProperty(JSON_FIELD_MIME_TYPE) String mimeType,
			@JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize) {
		this.fileId = fileId;
		this.thumb = thumb;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}
}