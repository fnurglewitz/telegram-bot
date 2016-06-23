package it.pwned.telegram.samplebot.trivia.api;

import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import it.pwned.telegram.samplebot.trivia.type.Question;
import it.pwned.telegram.samplebot.trivia.type.QuestionCategory;
import it.pwned.telegram.samplebot.trivia.type.QuestionDifficulty;
import it.pwned.telegram.samplebot.trivia.type.QuestionType;
import it.pwned.telegram.samplebot.trivia.type.TriviaApiQuestionResponse;
import it.pwned.telegram.samplebot.trivia.type.TriviaApiTokenResponse;

public class OpenTdbRestApi implements OpenTdbApi {

	private static final String API_URL = "http://www.opentdb.com/api.php";

	private final RestTemplate restTemplate;

	public OpenTdbRestApi(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public Question[] getQuestions(int amount, QuestionCategory category, QuestionDifficulty difficulty,
			QuestionType type) throws TriviaApiException {
		return getQuestions(amount, category, difficulty, type, null);
	}

	@Override
	public Question[] getQuestions(int amount, QuestionCategory category, QuestionDifficulty difficulty,
			QuestionType type, String token) throws TriviaApiException {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

		params.add("amount", Integer.toString(amount));

		if (category != null && category != QuestionCategory.ANY)
			params.add("category", category.getCode());

		if (difficulty != null && difficulty != QuestionDifficulty.ANY)
			params.add("difficulty", difficulty.toString());

		if (type != null && type != QuestionType.ANY)
			params.add("type", type.toString());

		if (token != null)
			params.add("token", token);

		final String uriString = UriComponentsBuilder.fromHttpUrl(API_URL).queryParams(params).build().toUriString();

		TriviaApiQuestionResponse res = restTemplate
				.exchange(uriString, HttpMethod.GET, null, TriviaApiQuestionResponse.class).getBody();

		return res.results;
	}

	@Override
	public String requestToken() throws TriviaApiException {

		TriviaApiTokenResponse res = restTemplate.exchange("http://www.opentdb.com/api_token.php?command=request",
				HttpMethod.GET, null, TriviaApiTokenResponse.class).getBody();

		return res.token;
	}

	@Override
	public void resetToken(String token) throws TriviaApiException {
		restTemplate.exchange("http://www.opentdb.com/api_token.php?command=reset&token=" + token, HttpMethod.GET, null,
				String.class).getBody();
	}

}
