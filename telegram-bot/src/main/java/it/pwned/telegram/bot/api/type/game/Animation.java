package it.pwned.telegram.bot.api.type.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

/**
 * You can provide an animation for your game so that it looks stylish in chats
 * (check out Lumberjack for an example). This object represents an animation
 * file to be displayed in the message containing a game.
 *
 */
public class Animation {

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
	 * <em>Optional.</em> Animation thumbnail as defined by sender
	 */
	@JsonProperty(JSON_FIELD_THUMB)
	public final PhotoSize thumb;

	/**
	 * <em>Optional.</em> Original animation filename as defined by sender
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
	 *          Unique file identifier
	 * @param thumb
	 *          <em>Optional.</em> Animation thumbnail as defined by sender
	 * @param fileName
	 *          <em>Optional.</em> Original animation filename as defined by
	 *          sender
	 * @param mimeType
	 *          <em>Optional.</em> MIME type of the file as defined by sender
	 * @param fileSize
	 *          <em>Optional.</em> File size
	 */
	public Animation(@JsonProperty(JSON_FIELD_FILE_ID) String fileId, @JsonProperty(JSON_FIELD_THUMB) PhotoSize thumb,
			@JsonProperty(JSON_FIELD_FILE_NAME) String fileName, @JsonProperty(JSON_FIELD_MIME_TYPE) String mimeType,
			@JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize) {

		this.fileId = fileId;
		this.thumb = thumb;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.fileSize = fileSize;

	}

}
