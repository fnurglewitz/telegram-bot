package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a response from the Telegram API
 *
 * @param <T>
 *          type T is the type returned by the API endpoint
 */
public final class Response<T> {

	private final static String JSON_FIELD_OK = "ok";
	private final static String JSON_FIELD_DESCRIPTION = "description";
	private final static String JSON_FIELD_ERROR_CODE = "error_code";
	private final static String JSON_FIELD_RESULT = "result";
	private final static String JSON_FIELD_PARAMETERS = "parameters";

	/**
	 * Flag indicating if the request was successful
	 */
	@JsonProperty(JSON_FIELD_OK)
	public final Boolean ok;

	/**
	 * Human-readable description of the result
	 */
	@JsonProperty(JSON_FIELD_DESCRIPTION)
	public final String description;

	/**
	 * Error code
	 */
	@JsonProperty(JSON_FIELD_ERROR_CODE)
	public final Integer errorCode;

	/**
	 * Method result
	 */
	@JsonProperty(JSON_FIELD_RESULT)
	public final T result;

	/**
	 * Additional error infos
	 */
	@JsonProperty(JSON_FIELD_PARAMETERS)
	public final ResponseParameters parameters;

	/**
	 * 
	 * @param ok
	 *          Flag indicating if the request was successful
	 * @param description
	 *          Human-readable description of the result
	 * @param errorCode
	 *          Error code
	 * @param result
	 *          Method result
	 * @param parameters
	 *          Additional error infos
	 */
	public Response(@JsonProperty(JSON_FIELD_OK) Boolean ok, @JsonProperty(JSON_FIELD_DESCRIPTION) String description,
			@JsonProperty(JSON_FIELD_ERROR_CODE) Integer errorCode, @JsonProperty(JSON_FIELD_RESULT) T result,
			@JsonProperty(JSON_FIELD_PARAMETERS) ResponseParameters parameters) {
		this.ok = ok;
		this.description = description;
		this.errorCode = errorCode;
		this.result = result;
		this.parameters = parameters;

	}

}
