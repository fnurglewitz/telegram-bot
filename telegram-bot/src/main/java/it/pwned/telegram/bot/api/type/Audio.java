package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Audio {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_DURATION = "duration";
	private final static String JSON_FIELD_PERFORMER = "performer";
	private final static String JSON_FIELD_TITLE = "title";
	private final static String JSON_FIELD_MIME_TYPE = "mime_type";
	private final static String JSON_FIELD_FILE_SIZE = "file_size";

	@JsonProperty(JSON_FIELD_FILE_ID)
	public final String fileId;

	@JsonProperty(JSON_FIELD_DURATION)
	public final Integer duration;

	@JsonProperty(JSON_FIELD_PERFORMER)
	public final String performer;

	@JsonProperty(JSON_FIELD_TITLE)
	public final String title;

	@JsonProperty(JSON_FIELD_MIME_TYPE)
	public final String mimeType;

	@JsonProperty(JSON_FIELD_FILE_SIZE)
	public final Integer fileSize;

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
