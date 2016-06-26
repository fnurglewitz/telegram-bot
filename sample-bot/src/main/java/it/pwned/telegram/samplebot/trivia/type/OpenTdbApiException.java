package it.pwned.telegram.samplebot.trivia.type;

public class OpenTdbApiException extends Exception {

	private static final long serialVersionUID = -5332659276082612856L;

	public final OpenTdbResponseCode responseCode;

	public OpenTdbApiException(OpenTdbResponseCode responseCode, String message) {
		super(message);
		this.responseCode = responseCode;
	}

}
