package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoNote {

    private final static String JSON_FIELD_FILE_ID = "file_id";
    private final static String JSON_FIELD_LENGTH = "length";
    private final static String JSON_FIELD_DURATION = "duration";
    private final static String JSON_FIELD_THUMB = "thumb";
    private final static String JSON_FIELD_FILE_SIZE = "file_size";

    /**
     * Unique identifier for this file
     */
    @JsonProperty(JSON_FIELD_FILE_ID)
    public final String fileId;

    /**
     * Video width and height as defined by sender
     */
    @JsonProperty(JSON_FIELD_LENGTH)
    public final Integer length;

    /**
     * Duration of the video in seconds as defined by sender
     */
    @JsonProperty(JSON_FIELD_DURATION)
    public final Integer duration;

    /**
     * <em>Optional.</em> Video thumbnail
     */
    @JsonProperty(JSON_FIELD_THUMB)
    public final PhotoSize thumb;

    /**
     * <em>Optional.</em> File size
     */
    @JsonProperty(JSON_FIELD_FILE_SIZE)
    public final Integer fileSize;

    /**
     * @param fileId   Unique identifier for this file
     * @param length   Video width and height as defined by sender
     * @param duration Duration of the video in seconds as defined by sender
     * @param thumb    <em>Optional.</em> Video thumbnail
     * @param fileSize <em>Optional.</em> File size
     */
    public VideoNote(
            @JsonProperty(JSON_FIELD_FILE_ID) String fileId,
            @JsonProperty(JSON_FIELD_LENGTH) Integer length,
            @JsonProperty(JSON_FIELD_DURATION) Integer duration,
            @JsonProperty(JSON_FIELD_THUMB) PhotoSize thumb,
            @JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize) {

        this.fileId = fileId;
        this.length = length;
        this.duration = duration;
        this.thumb = thumb;
        this.fileSize = fileSize;
    }


}
