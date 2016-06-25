package it.pwned.telegram.samplebot.trivia.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenTdbApiTokenResponse {

	private final static String JSON_FIELD_RESPONSE_CODE = "response_code";
	private final static String JSON_FIELD_RESPONSE_MESSAGE = "response_message";
	private final static String JSON_FIELD_TOKEN = "token";

	@JsonProperty(JSON_FIELD_RESPONSE_CODE)
	public final OpenTdbResponseCode responseCode;

	@JsonProperty(JSON_FIELD_RESPONSE_MESSAGE)
	public final String responseMessage;

	@JsonProperty(JSON_FIELD_TOKEN)
	public final String token;

	public OpenTdbApiTokenResponse(@JsonProperty(JSON_FIELD_RESPONSE_CODE) OpenTdbResponseCode responseCode,
			@JsonProperty(JSON_FIELD_RESPONSE_MESSAGE) String responseMessage, @JsonProperty(JSON_FIELD_TOKEN) String token) {

		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.token = token;

	}

}
