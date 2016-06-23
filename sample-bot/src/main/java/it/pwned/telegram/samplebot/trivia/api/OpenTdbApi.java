package it.pwned.telegram.samplebot.trivia.api;

import it.pwned.telegram.samplebot.trivia.type.Question;
import it.pwned.telegram.samplebot.trivia.type.QuestionCategory;
import it.pwned.telegram.samplebot.trivia.type.QuestionDifficulty;
import it.pwned.telegram.samplebot.trivia.type.QuestionType;

public interface OpenTdbApi {

	public Question[] getQuestions(int amount, QuestionCategory category, QuestionDifficulty difficulty,
			QuestionType type) throws TriviaApiException;

	public Question[] getQuestions(int amount, QuestionCategory category, QuestionDifficulty difficulty,
			QuestionType type, String token) throws TriviaApiException;

	public String requestToken() throws TriviaApiException;

	public void resetToken(String token) throws TriviaApiException;
}
