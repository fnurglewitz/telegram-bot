package it.pwned.telegram.bot.api.type.game;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.User;

public class GameHighScore implements Comparable<GameHighScore> {

	private final static String JSON_FIELD_POSITION = "position";
	private final static String JSON_FIELD_USER = "user";
	private final static String JSON_FIELD_SCORE = "score";

	@JsonProperty(JSON_FIELD_POSITION)
	public final Integer position;

	@JsonProperty(JSON_FIELD_USER)
	public final User user;

	@JsonProperty(JSON_FIELD_SCORE)
	public final Integer score;

	public GameHighScore(@JsonProperty(JSON_FIELD_POSITION) Integer position, @JsonProperty(JSON_FIELD_USER) User user,
			@JsonProperty(JSON_FIELD_SCORE) Integer score) {

		this.position = position;
		this.user = user;
		this.score = score;

	}

	/**
	 * This method compares by position
	 */
	@Override
	public int compareTo(GameHighScore other) {
		return this.position.compareTo(other.position);
	}

}
