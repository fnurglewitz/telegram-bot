package it.pwned.telegram.samplebot.trivia.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Question {

	private final static String JSON_FIELD_CATEGORY = "category";
	private final static String JSON_FIELD_TYPE = "type";
	private final static String JSON_FIELD_DIFFICULTY = "difficulty";
	private final static String JSON_FIELD_QUESTION = "question";
	private final static String JSON_FIELD_CORRECT_ANSWER = "correct_answer";
	private final static String JSON_FIELD_INCORRECT_ANSWERS = "incorrect_answers";

	@JsonProperty(JSON_FIELD_CATEGORY)
	public final QuestionCategory category;

	@JsonProperty(JSON_FIELD_TYPE)
	public final QuestionType type;

	@JsonProperty(JSON_FIELD_DIFFICULTY)
	public final QuestionDifficulty difficulty;

	@JsonProperty(JSON_FIELD_QUESTION)
	public final String question;

	@JsonProperty(JSON_FIELD_CORRECT_ANSWER)
	public final String correctAnswer;

	@JsonProperty(JSON_FIELD_INCORRECT_ANSWERS)
	public final String[] incorrectAnswers;

	public Question(@JsonProperty(JSON_FIELD_CATEGORY) QuestionCategory category,
			@JsonProperty(JSON_FIELD_TYPE) QuestionType type,
			@JsonProperty(JSON_FIELD_DIFFICULTY) QuestionDifficulty difficulty,
			@JsonProperty(JSON_FIELD_QUESTION) String question, @JsonProperty(JSON_FIELD_CORRECT_ANSWER) String correctAnswer,
			@JsonProperty(JSON_FIELD_INCORRECT_ANSWERS) String[] incorrectAnswers) {

		this.category = category;
		this.type = type;
		this.difficulty = difficulty;
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.incorrectAnswers = incorrectAnswers;

	}

}
