package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This is an utility abstract class used to keep multiple types of inline query
 * results in the same collection
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class InlineQueryResult {

	public InlineQueryResult() {
	}

}
