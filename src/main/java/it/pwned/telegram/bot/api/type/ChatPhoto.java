package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a chat photo.
 */
public class ChatPhoto {

    private final static String JSON_FIELD_SMALL_FILE_ID = "small_file_id";
    private final static String JSON_FIELD_BIG_FILE_ID = "big_file_id";

    /**
     * Unique file identifier of small (160x160) chat photo. This file_id can be used only for photo download.
     */
    @JsonProperty(JSON_FIELD_SMALL_FILE_ID)
    public final String smallFileId;

    /**
     * Unique file identifier of big (640x640) chat photo. This file_id can be used only for photo download.
     */
    @JsonProperty(JSON_FIELD_BIG_FILE_ID)
    public final String bigFileId;

    /**
     * @param smallFileId Unique file identifier of small (160x160) chat photo. This file_id can be used only for photo download.
     * @param bigFileId   Unique file identifier of big (640x640) chat photo. This file_id can be used only for photo download.
     */
    public ChatPhoto(@JsonProperty(JSON_FIELD_SMALL_FILE_ID) String smallFileId,
                     @JsonProperty(JSON_FIELD_BIG_FILE_ID) String bigFileId) {
        this.smallFileId = smallFileId;
        this.bigFileId = bigFileId;
    }

}
