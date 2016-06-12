package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.PhotoSize;

/**
 * This class represent a user's profile pictures.
 *
 */
public final class UserProfilePhotos {

	private final static String JSON_FIELD_TOTAL_COUNT = "total_count";
	private final static String JSON_FIELD_PHOTOS = "photos";

	/**
	 * Total number of profile pictures the target user has
	 */
	@JsonProperty(JSON_FIELD_TOTAL_COUNT)
	public final Integer totalCount;

	/**
	 * Requested profile pictures (in up to 4 sizes each)
	 */
	@JsonProperty(JSON_FIELD_PHOTOS)
	public final PhotoSize[][] photos;

	/**
	 * 
	 * @param totalCount
	 *          Total number of profile pictures the target user has
	 * @param photos
	 *          Requested profile pictures (in up to 4 sizes each)
	 */
	public UserProfilePhotos(@JsonProperty(JSON_FIELD_TOTAL_COUNT) Integer totalCount,
			@JsonProperty(JSON_FIELD_PHOTOS) PhotoSize[][] photos) {
		this.totalCount = totalCount;
		this.photos = photos;
	}

}
