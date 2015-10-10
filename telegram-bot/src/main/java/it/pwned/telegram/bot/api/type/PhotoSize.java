package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PhotoSize {
	public final String file_id;
	public final Integer width;
	public final Integer height;
	public final Integer file_size;

	public PhotoSize(@JsonProperty("file_id") String file_id, @JsonProperty("width") Integer width,
			@JsonProperty("height") Integer height, @JsonProperty("file_size") Integer file_size) {
		this.file_id = file_id;
		this.width = width;
		this.height = height;
		this.file_size = file_size;
	}
}