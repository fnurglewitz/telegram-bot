package it.pwned.telegram.samplebot.trivia.api;

import java.util.List;

import it.pwned.telegram.samplebot.trivia.type.OpenTdbApiException;
import it.pwned.telegram.samplebot.trivia.type.Question;
import it.pwned.telegram.samplebot.trivia.type.QuestionCategory;
import it.pwned.telegram.samplebot.trivia.type.QuestionDifficulty;
import it.pwned.telegram.samplebot.trivia.type.QuestionType;

public interface OpenTdbApi {

	public List<Question> getQuestions(int amount, QuestionCategory category, QuestionDifficulty difficulty,
			QuestionType type) throws OpenTdbApiException;

	public List<Question> getQuestions(int amount, QuestionCategory category, QuestionDifficulty difficulty,
			QuestionType type, String token) throws OpenTdbApiException;

	public String requestToken() throws OpenTdbApiException;

	public void resetToken(String token) throws OpenTdbApiException;
}
