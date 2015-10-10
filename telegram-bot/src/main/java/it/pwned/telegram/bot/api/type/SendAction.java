package it.pwned.telegram.bot.api.type;

public enum SendAction {
	Photo(0), Audio(1), Document(2), Sticker(3), Video(4), Voice(5);

	private final int value;

	private SendAction(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static final SendAction getSendActionFromMimeType(String mime_type) {
		switch (mime_type) {

		case "image/jpeg":
		case "image/png":
		case "image/bmp":
			return SendAction.Photo;

		case "video/mp4":
			return SendAction.Video;

		case "audio/mpeg":
		case "audio/mp3":
		case "audio/mpeg3":
		case "audio/x-mpeg-3":
			return SendAction.Audio;

		case "audio/ogg":
			return SendAction.Voice; // TODO : bug for sure, audio/ogg too generic, the file's codec must be OPUS

		default:
			return SendAction.Document;

		}
	}
}