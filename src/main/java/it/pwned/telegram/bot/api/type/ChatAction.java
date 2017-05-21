package it.pwned.telegram.bot.api.type;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of action to broadcast when using
 * {@link it.pwned.telegram.bot.api.method.SendChatAction}
 */
public enum ChatAction implements MultipartDataEntity {

    TYPING("typing"),
    UPLOAD_PHOTO("upload_photo"),
    RECORD_VIDEO("record_video"),
    UPLOAD_VIDEO("upload_video"),
    RECORD_AUDIO("record_audio"),
    UPLOAD_AUDIO("upload_audio"),
    UPLOAD_DOCUMENT("upload_document"),
    FIND_LOCATION("find_location"),
    RECORD_VIDEO_NOTE("record_video_note"),
    UPLOAD_VIDEO_NOTE("upload_video_note");

    private final String value;

    private ChatAction(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }

    @Override
    public String stringValue() {
        return toString();
    }

    @Override
    public MediaType getContentType() {
        return MediaType.TEXT_PLAIN;
    }

}
