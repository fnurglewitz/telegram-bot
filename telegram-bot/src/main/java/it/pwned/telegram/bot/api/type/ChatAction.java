package it.pwned.telegram.bot.api.type;

public enum ChatAction {
	typing(0),
	upload_photo(1),
	record_video(2),
	upload_video(3),
	record_audio(4),
	upload_audio(5),
	upload_document(6),
	find_location(7);

    private final int value;

    private ChatAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}