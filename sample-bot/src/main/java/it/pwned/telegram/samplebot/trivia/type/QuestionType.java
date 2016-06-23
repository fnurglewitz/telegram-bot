package it.pwned.telegram.samplebot.trivia.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestionType {

	ANY("any"), MULTIPLE("multiple"), BOOLEAN("boolean");

	private final String value;

	private QuestionType(String value) {
		this.value = value;
	}

	@JsonValue
	@Override
	public String toString() {
		return value;
	}

	@JsonCreator
	public static QuestionType fromString(String value) {

		switch (value) {

		case "multiple":
			return QuestionType.MULTIPLE;

		case "boolean":
			return QuestionType.BOOLEAN;

		default:
			return QuestionType.ANY;
		}

	}
	
	public String toUrlParam() {
		return (value == "any") ? "" : "&type=" + value;
	}

}
