package it.pwned.telegram.bot.api.method.enums;

import org.springframework.http.MediaType;

public enum MethodMediaType {
	NONE, MULTIPART_FORM_DATA;

	public MediaType get() {

		switch (this) {
		case NONE:
			return null;

		case MULTIPART_FORM_DATA:
			return MediaType.MULTIPART_FORM_DATA;

		default:
			return null;
		}

	}
}
