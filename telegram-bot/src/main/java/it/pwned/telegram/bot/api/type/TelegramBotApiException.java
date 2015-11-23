package it.pwned.telegram.bot.api.type;

public class TelegramBotApiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelegramBotApiException() {
	}

	public TelegramBotApiException(String message) {
		super(message);
	}

	public TelegramBotApiException(Throwable cause) {
		super(cause);
	}

	public TelegramBotApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public TelegramBotApiException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
