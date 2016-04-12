package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageEntity {

	public final String type;
	public final Integer offset;
	public final Integer length;
	public final String url;

	public MessageEntity(@JsonProperty("type") String type, @JsonProperty("offset") Integer offset,
			@JsonProperty("length") Integer length, @JsonProperty("url") String url) {
		this.type = type;
		this.offset = offset;
		this.length = length;
		this.url = url;
	}

}
