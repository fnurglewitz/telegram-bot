package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

public final class UserProfilePhotos {
	public final Integer total_count;
	public final PhotoSize[][] photos;

	public UserProfilePhotos(@JsonProperty("total_count") Integer total_count,
			@JsonProperty("photos") PhotoSize[][] photos) {
		this.total_count = total_count;
		this.photos = photos;
	}
}