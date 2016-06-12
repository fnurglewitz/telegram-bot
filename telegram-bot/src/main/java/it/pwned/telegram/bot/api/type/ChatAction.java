package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Type of action to broadcast when using
 * {@link it.pwned.telegram.bot.api.TelegramBotApi#sendChatAction}
 *
 */
public enum ChatAction {

	TYPING("typing"), 
	UPLOAD_PHOTO("upload_photo"), 
	RECORD_VIDEO("record_video"), 
	UPLOAD_VIDEO("upload_video"), 
	RECORD_AUDIO("record_audio"), 
	UPLOAD_AUDIO("upload_audio"), 
	UPLOAD_DOCUMENT("upload_document"), 
	FIND_LOCATION("find_location");

	private final String value;

	private ChatAction(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	@JsonValue
	public String getAction() {
		return value;
	}
}
