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

@ApiMethod("sendVenue")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendVenue extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	private ChatId chatId;

	@ApiMethodParameter("latitude")
	private Float latitude;

	@ApiMethodParameter("longitude")
	private Float longitude;

	@ApiMethodParameter("title")
	private String title;

	@ApiMethodParameter("address")
	private String address;

	@ApiMethodParameter("foursquare_id")
	private String foursquareId;

	@ApiMethodParameter("disable_notification")
	private Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	private Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	private AbstractKeyboardMarkup replyMarkup;

	public SendVenue(ChatId chatId, float latitude, float longitude, String title, String address) {
		super();

		setChatId(chatId);
		this.latitude = latitude;
		this.longitude = longitude;
		setTitle(title);
		setAddress(address);
	}

	public SendVenue setChatId(ChatId chatId) {
		if (chatId == null)
			throw new InvalidParameterException("chatId cannot be null");

		this.chatId = chatId;
		return this;
	}

	public SendVenue setLatitude(float latitude) {
		this.latitude = latitude;
		return this;
	}

	public SendVenue setLongitude(float longitude) {
		this.longitude = longitude;
		return this;
	}

	public SendVenue setTitle(String title) {
		if (title == null)
			throw new InvalidParameterException("title cannot be null");

		this.title = title;
		return this;
	}

	public SendVenue setAddress(String address) {
		if (address == null)
			throw new InvalidParameterException("address cannot be null");

		this.address = address;
		return this;
	}

	public SendVenue setFoursquareId(String foursquareId) {
		this.foursquareId = foursquareId;
		return this;
	}

	public SendVenue setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
		return this;
	}

	public SendVenue setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
		return this;
	}

	public SendVenue setReplyMarkup(AbstractKeyboardMarkup replyMarkup) {
		this.replyMarkup = replyMarkup;
		return this;
	}

	public ChatId getChatId() {
		return this.chatId;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAddress() {
		return this.address;
	}

	public String getFoursquareId() {
		return this.foursquareId;
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
