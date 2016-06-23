package it.pwned.telegram.samplebot.trivia.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TriviaApiQuestionResponse {

	private final static String JSON_FIELD_RESPONSE_CODE = "response_code";
	private final static String JSON_FIELD_RESPONSE_MESSAGE = "response_message";
	private final static String JSON_FIELD_RESULTS = "results";

	@JsonProperty(JSON_FIELD_RESPONSE_CODE)
	public final Integer responseCode;

	@JsonProperty(JSON_FIELD_RESPONSE_MESSAGE)
	public final String responseMessage;

	@JsonProperty(JSON_FIELD_RESULTS)
	public final Question[] results;

	public TriviaApiQuestionResponse(@JsonProperty(JSON_FIELD_RESPONSE_CODE) Integer responseCode,
			@JsonProperty(JSON_FIELD_RESPONSE_MESSAGE) String responseMessage,
			@JsonProperty(JSON_FIELD_RESULTS) Question[] results) {

		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.results = results;

	}

}
