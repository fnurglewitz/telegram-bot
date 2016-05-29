package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageEntity {

	public final String type;
	public final Integer offset;
	public final Integer length;
	public final String url;
	public final User user;

	public MessageEntity(@JsonProperty("type") String type, @JsonProperty("offset") Integer offset,
			@JsonProperty("length") Integer length, @JsonProperty("url") String url, @JsonProperty("user") User user) {
		this.type = type;
		this.offset = offset;
		this.length = length;
		this.url = url;
		this.user = user;
	}

}
