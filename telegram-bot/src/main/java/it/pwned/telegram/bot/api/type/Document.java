package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

public final class Document {
	public final String file_id;
	public final PhotoSize thumb;
	public final String file_name;
	public final String mime_type;
	public final Integer file_size;

	public Document(@JsonProperty("file_id") String file_id, @JsonProperty("thumb") PhotoSize thumb,
			@JsonProperty("file_name") String file_name, @JsonProperty("mime_type") String mime_type,
			@JsonProperty("file_size") Integer file_size) {
		this.file_id = file_id;
		this.thumb = thumb;
		this.file_name = file_name;
		this.mime_type = mime_type;
		this.file_size = file_size;
	}
}