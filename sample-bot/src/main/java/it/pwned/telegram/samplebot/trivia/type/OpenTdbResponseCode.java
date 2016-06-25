package it.pwned.telegram.samplebot.trivia.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OpenTdbResponseCode {

	SUCCESS(0), NO_RESULTS(1), INVALID_PARAMETER(2), TOKEN_NOT_FOUND(3), TOKEN_EMPTY(4), UNKNOWN_ERROR(-1);

	private final Integer value;

	private OpenTdbResponseCode(Integer value) {
		this.value = value;
	}

	@JsonValue
	@Override
	public String toString() {
		return Integer.toString(value);
	}

	@JsonCreator
	public static OpenTdbResponseCode fromInteger(Integer value) {

		switch (value) {

		case 0:
			return OpenTdbResponseCode.SUCCESS;

		case 1:
			return OpenTdbResponseCode.NO_RESULTS;

		case 2:
			return OpenTdbResponseCode.INVALID_PARAMETER;

		case 3:
			return OpenTdbResponseCode.TOKEN_NOT_FOUND;

		case 4:
			return OpenTdbResponseCode.TOKEN_EMPTY;

		default:
			return OpenTdbResponseCode.UNKNOWN_ERROR;
		}

	}

}
