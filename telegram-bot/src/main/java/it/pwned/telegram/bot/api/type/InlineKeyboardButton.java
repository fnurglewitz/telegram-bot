package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class represents one button of an inline keyboard. You must use exactly
 * one of the optional fields.
 *
 */
@JsonInclude(Include.NON_NULL)
public class InlineKeyboardButton extends AbstractKeyboardButton {

	private final static String JSON_FIELD_TEXT = "text";
	private final static String JSON_FIELD_URL = "url";
	private final static String JSON_FIELD_CALLBACK_DATA = "callback_data";
	private final static String JSON_FIELD_SWITCH_INLINE_QUERY = "switch_inline_query";

	/**
	 * Label text on the button
	 */
	@JsonProperty(JSON_FIELD_TEXT)
	public final String text;

	/**
	 * <em>Optional.</em> HTTP url to be opened when button is pressed
	 */
	@JsonProperty(JSON_FIELD_URL)
	public final String url;

	/**
	 * <em>Optional.</em> Data to be sent in a callback query to the bot when
	 * button is pressed, 1-64 bytes
	 */
	@JsonProperty(JSON_FIELD_CALLBACK_DATA)
	public final String callbackData;

	/**
	 * <em>Optional.</em> If set, pressing the button will prompt the user to
	 * select one of their chats, open that chat and insert the bot‘s username
	 * and the specified inline query in the input field. Can be empty, in which
	 * case just the bot’s username will be inserted. <b>Note:</b> This offers
	 * an easy way for users to start using your bot in inline mode when they
	 * are currently in a private chat with it. Especially useful when combined
	 * with switch_pm… actions
	 * ({@link it.pwned.telegram.bot.api.TelegramBotApi#answerInlineQuery
	 * TelegramBotApi.answerInlineQuery}) – in this case the user will be
	 * automatically returned to the chat they switched from, skipping the chat
	 * selection screen.
	 */
	@JsonProperty(JSON_FIELD_SWITCH_INLINE_QUERY)
	public final String switchInlineQuery;

	/**
	 * 
	 * @param text
	 *            Label text on the button
	 * @param url
	 *            <em>Optional.</em> HTTP url to be opened when button is
	 *            pressed
	 * @param callbackData
	 *            <em>Optional.</em> Data to be sent in a callback query to the
	 *            bot when button is pressed, 1-64 bytes
	 * @param switchInlineQuery
	 *            <em>Optional.</em> If set, pressing the button will prompt the
	 *            user to select one of their chats, open that chat and insert
	 *            the bot‘s username and the specified inline query in the input
	 *            field. Can be empty, in which case just the bot’s username
	 *            will be inserted. <b>Note:</b> This offers an easy way for
	 *            users to start using your bot in inline mode when they are
	 *            currently in a private chat with it. Especially useful when
	 *            combined with switch_pm… actions
	 *            ({@link it.pwned.telegram.bot.api.TelegramBotApi#answerInlineQuery
	 *            TelegramBotApi.answerInlineQuery}) – in this case the user
	 *            will be automatically returned to the chat they switched from,
	 *            skipping the chat selection screen.
	 */
	public InlineKeyboardButton(@JsonProperty(JSON_FIELD_TEXT) String text, @JsonProperty(JSON_FIELD_URL) String url,
			@JsonProperty(JSON_FIELD_CALLBACK_DATA) String callbackData,
			@JsonProperty(JSON_FIELD_SWITCH_INLINE_QUERY) String switchInlineQuery) {
		this.text = text;
		this.url = url;
		this.callbackData = callbackData;
		this.switchInlineQuery = switchInlineQuery;
	}
}
