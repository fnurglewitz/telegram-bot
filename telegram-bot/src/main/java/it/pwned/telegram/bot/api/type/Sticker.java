package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

public final class Sticker {
	public final String file_id;
	public final Integer width;
	public final Integer height;
	public final PhotoSize thumb;
	public final Integer file_size;

	public Sticker(@JsonProperty("file_id") String file_id, @JsonProperty("width") Integer width,
			@JsonProperty("height") Integer height, @JsonProperty("thumb") PhotoSize thumb,
			@JsonProperty("file_size") Integer file_size) {
		this.file_id = file_id;
		this.width = width;
		this.height = height;
		this.thumb = thumb;
		this.file_size = file_size;
	}
}