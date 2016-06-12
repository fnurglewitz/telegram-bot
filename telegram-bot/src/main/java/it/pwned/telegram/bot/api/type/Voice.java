package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a voice note.
 *
 */
public class Voice {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_DURATION = "duration";
	private final static String JSON_FIELD_MIME_TYPE = "mime_type";
	private final static String JSON_FIELD_FILE_SIZE = "file_size";

	/**
	 * Unique identifier for this file
	 */
	@JsonProperty(JSON_FIELD_FILE_ID)
	public final String fileId;

	/**
	 * Duration of the audio in seconds as defined by sender
	 */
	@JsonProperty(JSON_FIELD_DURATION)
	public final Integer duration;

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
	 *          Unique identifier for this file
	 * @param duration
	 *          Duration of the audio in seconds as defined by sender
	 * @param mimeType
	 *          <em>Optional.</em> MIME type of the file as defined by sender
	 * @param filesize
	 *          <em>Optional.</em> File size
	 */
	public Voice(@JsonProperty(JSON_FIELD_FILE_ID) String fileId, @JsonProperty(JSON_FIELD_DURATION) Integer duration,
			@JsonProperty(JSON_FIELD_MIME_TYPE) String mimeType, @JsonProperty(JSON_FIELD_FILE_SIZE) Integer filesize) {

		this.fileId = fileId;
		this.duration = duration;
		this.mimeType = mimeType;
		this.fileSize = filesize;
	}

}
