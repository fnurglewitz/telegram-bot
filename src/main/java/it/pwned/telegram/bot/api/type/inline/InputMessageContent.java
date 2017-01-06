package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 
 * This is an utility abstract class used to use multiple types of InputMessageContent inside inline objects
 * 
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class InputMessageContent {
	public InputMessageContent() {
	}
}
