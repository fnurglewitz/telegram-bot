package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

public final class Video {
	public final String file_id;
	public final Integer width;
	public final Integer height;
	public final Integer duration;
	public final PhotoSize thumb;
	public final String mime_type;
	public final Integer file_size;

	public Video(@JsonProperty("file_id") String file_id, @JsonProperty("width") Integer width,
			@JsonProperty("height") Integer height, @JsonProperty("duration") Integer duration,
			@JsonProperty("thumb") PhotoSize thumb, @JsonProperty("mime_type") String mime_type,
			@JsonProperty("file_size") Integer file_size) {

		this.file_id = file_id;
		this.width = width;
		this.height = height;
		this.duration = duration;
		this.thumb = thumb;
		this.mime_type = mime_type;
		this.file_size = file_size;
	}
}