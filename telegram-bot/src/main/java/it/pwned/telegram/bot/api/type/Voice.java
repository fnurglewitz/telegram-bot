package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Voice {
	public final String file_id;
	public final Integer duration;
	public final String mime_type;
	public final Integer file_size;

	public Voice(@JsonProperty("file_id") String file_id, @JsonProperty("duration") Integer duration,
			@JsonProperty("mime_type") String mime_type, @JsonProperty("file_size") Integer file_size) {

		this.file_id = file_id;
		this.duration = duration;
		this.mime_type = mime_type;
		this.file_size = file_size;
	}
}
