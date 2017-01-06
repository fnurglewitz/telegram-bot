package it.pwned.telegram.bot.api.type;

/**
 * Custom Exception created to wrap API errors
 *
 */
public class TelegramBotApiException extends Exception {

	private Integer errorCode;

	private static final long serialVersionUID = 1L;

	public TelegramBotApiException(String message) {
		super(message);
	}

	public TelegramBotApiException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public TelegramBotApiException(Throwable cause) {
		super(cause);
	}

	public TelegramBotApiException(Throwable cause, Integer errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}

	public TelegramBotApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public TelegramBotApiException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
