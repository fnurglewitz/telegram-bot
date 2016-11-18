package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;

@ApiMethod("sendContact")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendContact extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("phone_number")
	public final String phoneNumber;

	@ApiMethodParameter("first_name")
	public final String firstName;

	@ApiMethodParameter("last_name")
	public final String lastName;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendContact(ChatId chatId, String phoneNumber, String firstName) {
		this(chatId, phoneNumber, firstName, null, null, null, null);
	}

	public SendContact(ChatId chatId, String phoneNumber, String firstName, String lastName,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.phoneNumber = validatePhoneNumber(phoneNumber);
		this.firstName = validateFirstName(firstName);
		this.lastName = lastName;
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static String validatePhoneNumber(String phoneNumber) {
		if (phoneNumber == null)
			throw new IllegalArgumentException("phoneNumber cannot be null");

		return phoneNumber;
	}

	private static String validateFirstName(String firstName) {
		if (firstName == null)
			throw new IllegalArgumentException("firstName cannot be null");

		return firstName;
	}

	public static class Builder {

		private ChatId chatId;

		private String phoneNumber;

		private String firstName;

		private String lastName;

		private Boolean disableNotification;

		private Integer replyToMessageId;

		private AbstractKeyboardMarkup replyMarkup;

		public Builder(ChatId chatId, String phoneNumber, String firstName) {
			this.chatId = validateChatId(chatId);
			this.phoneNumber = validatePhoneNumber(phoneNumber);
			this.firstName = validateFirstName(firstName);
		}

		public SendContact build() {
			return new SendContact(chatId, phoneNumber, firstName, lastName, disableNotification, replyToMessageId,
					replyMarkup);
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setDisableNotification(Boolean disableNotification) {
			this.disableNotification = disableNotification;
			return this;
		}

		public Builder setReplyToMessageId(Integer replyToMessageId) {
			this.replyToMessageId = replyToMessageId;
			return this;
		}

		public Builder setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
			this.replyMarkup = replyMarkup;
			return this;
		}

	}

}
