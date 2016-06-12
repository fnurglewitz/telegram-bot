package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents an audio file to be treated as music by the Telegram
 * clients.
 * 
 */
public final class Audio {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_DURATION = "duration";
	private final static String JSON_FIELD_PERFORMER = "performer";
	private final static String JSON_FIELD_TITLE = "title";
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
	 * <em>Optional.</em> Performer of the audio as defined by sender or by
	 * audio tags
	 */
	@JsonProperty(JSON_FIELD_PERFORMER)
	public final String performer;

	/**
	 * <em>Optional.</em> Title of the audio as defined by sender or by audio
	 * tags
	 */
	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

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
	 *            Unique identifier for this file
	 * @param duration
	 *            Duration of the audio in seconds as defined by sender
	 * @param performer
	 *            <em>Optional.</em> Performer of the audio as defined by sender
	 *            or by audio tags
	 * @param title
	 *            <em>Optional.</em> Title of the audio as defined by sender or
	 *            by audio tags
	 * @param mimeType
	 *            <em>Optional.</em> MIME type of the file as defined by sender
	 * @param fileSize
	 *            <em>Optional.</em> File size
	 * 
	 */
	public Audio(@JsonProperty(JSON_FIELD_FILE_ID) String fileId, @JsonProperty(JSON_FIELD_DURATION) Integer duration,
			@JsonProperty(JSON_FIELD_PERFORMER) String performer, @JsonProperty(JSON_FIELD_TITLE) String title,
			@JsonProperty(JSON_FIELD_MIME_TYPE) String mimeType, @JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize) {
		this.fileId = fileId;
		this.duration = duration;
		this.performer = performer;
		this.title = title;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}

}
