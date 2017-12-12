package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.core.io.Resource;

/**
 * Represents a photo to be sent.
 */
public class InputMediaPhoto extends InputMedia {

    private final static String JSON_FIELD_TYPE = "type";
    private final static String JSON_FIELD_MEDIA = "media";
    private final static String JSON_FIELD_CAPTION = "caption";

    /**
     * Type of the result, must be photo
     */
    @JsonProperty(JSON_FIELD_TYPE)
    public final String type = "photo";

    /**
     * File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended),
     * pass an HTTP URL for Telegram to get a file from the Internet, or pass "attach://<file_attach_name>"
     * to upload a new one using multipart/form-data under <file_attach_name> name.
     */
    @JsonProperty(JSON_FIELD_MEDIA)
    public final Resource media;

    /**
     * <em>Optional.</em> Caption of the photo to be sent, 0-200 characters
     */
    @JsonProperty(JSON_FIELD_CAPTION)
    public final String caption;

    /**
     * Represents a photo to be sent.
     *
     * @param media   File to send. Pass a file_id to send a file that exists on the Telegram servers (recommended),
     *                pass an HTTP URL for Telegram to get a file from the Internet, or pass "attach://<file_attach_name>"
     *                to upload a new one using multipart/form-data under <file_attach_name> name.
     * @param caption <em>Optional.</em> Caption of the photo to be sent, 0-200 characters
     */
    public InputMediaPhoto(@JsonProperty(JSON_FIELD_MEDIA) Resource media, @JsonProperty(JSON_FIELD_CAPTION) String caption) {
        this.media = media;
        this.caption = validateCaption(caption);
    }

    private static String validateCaption(String caption) {
        if (caption != null && caption.length() > 200)
            throw new IllegalArgumentException("caption length cannot be > 200");

        return caption;
    }

}
