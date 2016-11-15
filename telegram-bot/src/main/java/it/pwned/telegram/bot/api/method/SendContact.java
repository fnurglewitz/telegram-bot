package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

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
	private ChatId chatId;

	@ApiMethodParameter("phone_number")
	private String phoneNumber;

	@ApiMethodParameter("first_name")
	private String firstName;

	@ApiMethodParameter("last_name")
	private String lastName;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendContact(ChatId chatId, String phoneNumber, String firstName) {
		super();

		setChatId(chatId);
		setPhoneNumber(phoneNumber);
		setFirstName(firstName);
	}

	public SendContact setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendContact setPhoneNumber(String phoneNumber) {
		if (phoneNumber == null)
			throw new InvalidParameterException("phoneNumber cannot be null");

		this.phoneNumber = phoneNumber;
		return this;
	}

	public SendContact setFirstName(String firstName) {
		if (firstName == null)
			throw new InvalidParameterException("firstName cannot be null");

		this.firstName = firstName;
		return this;
	}

	public SendContact setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public SendContact setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendContact setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendContact setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Boolean getDisableNotification() {
		return this.disableNotification;
	}

	public Integer getReplyToMessageId() {
		return this.replyToMessageId;
	}

	public AbstractKeyboardMarkup getReplyMarkup() {
		return this.replyMarkup;
	}

}
