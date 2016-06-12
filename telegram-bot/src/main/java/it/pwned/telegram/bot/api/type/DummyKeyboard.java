package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This is an utility abstract class used to pass every kind of keyboard objects
 * to send* methods ({@link it.pwned.telegram.bot.api.TelegramBotApi
 * TelegramBotApi})
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class DummyKeyboard {

}