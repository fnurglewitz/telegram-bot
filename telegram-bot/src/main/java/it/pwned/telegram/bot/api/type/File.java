package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class File {
	public final String file_id;
	public final Integer file_size;
	public final String file_path;

	public File(@JsonProperty("file_id") String file_id, @JsonProperty("file_size") Integer file_size,
			@JsonProperty("file_path") String file_path) {
		this.file_id = file_id;
		this.file_size = file_size;
		this.file_path = file_path;
	}
}
