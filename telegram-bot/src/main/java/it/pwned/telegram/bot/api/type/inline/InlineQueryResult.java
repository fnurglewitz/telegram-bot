package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class InlineQueryResult {

	public InlineQueryResult() {
	}

}
