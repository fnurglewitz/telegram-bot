package it.pwned.telegram.samplebot.trivia.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum QuestionDifficulty {

	ANY("any"), EASY("easy"), MEDIUM("medium"), HARD("hard");

	private final String value;

	private QuestionDifficulty(String value) {
		this.value = value;
	}

	@JsonValue
	@Override
	public String toString() {
		return value;
	}

	@JsonCreator
	public static QuestionDifficulty fromString(String value) {

		switch (value) {

		case "easy":
			return QuestionDifficulty.EASY;

		case "medium":
			return QuestionDifficulty.MEDIUM;

		case "hard":
			return QuestionDifficulty.HARD;

		default:
			return QuestionDifficulty.ANY;
		}

	}

}
