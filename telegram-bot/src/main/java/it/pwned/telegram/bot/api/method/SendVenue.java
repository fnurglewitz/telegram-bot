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

@ApiMethod("sendVenue")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class SendVenue extends AbstractApiMethod<Message> {

	@ApiMethodParameter("chat_id")
	public final ChatId chatId;

	@ApiMethodParameter("latitude")
	public final Float latitude;

	@ApiMethodParameter("longitude")
	public final Float longitude;

	@ApiMethodParameter("title")
	public final String title;

	@ApiMethodParameter("address")
	public final String address;

	@ApiMethodParameter("foursquare_id")
	public final String foursquareId;

	@ApiMethodParameter("disable_notification")
	public final Boolean disableNotification;

	@ApiMethodParameter("reply_to_message_id")
	public final Integer replyToMessageId;

	@ApiMethodParameter("reply_markup")
	public final AbstractKeyboardMarkup replyMarkup;

	public SendVenue(ChatId chatId, float latitude, float longitude, String title, String address) {
		this(chatId, latitude, longitude, title, address, null, null, null, null);
	}

	public SendVenue(ChatId chatId, float latitude, float longitude, String title, String address, String foursquareId,
			Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {
		super();

		this.chatId = validateChatId(chatId);
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = validateTitle(title);
		this.address = validateAddress(address);
		this.foursquareId = foursquareId;
		this.disableNotification = disableNotification;
		this.replyToMessageId = replyToMessageId;
		this.replyMarkup = replyMarkup;
	}

	private static ChatId validateChatId(ChatId chatId) {
		if (chatId == null)
			throw new IllegalArgumentException("chatId cannot be null");

		return chatId;
	}

	private static String validateTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("title cannot be null");

		return title;
	}

	private static String validateAddress(String address) {
		if (address == null)
			throw new IllegalArgumentException("address cannot be null");

		return address;
	}

	public static class Builder {

		private ChatId chatId;

		private Float latitude;

		private Float longitude;

		private String title;

		private String address;

		private String foursquareId;

		private Boolean disableNotification;

		private Integer replyToMessageId;

		private AbstractKeyboardMarkup replyMarkup;

		public Builder(ChatId chatId, float latitude, float longitude, String title, String address) {
			this.chatId = validateChatId(chatId);
			this.latitude = latitude;
			this.longitude = longitude;
			this.title = validateTitle(title);
			this.address = validateAddress(address);
		}

		public SendVenue build() {
			return new SendVenue(chatId, latitude, longitude, title, address, foursquareId, disableNotification,
					replyToMessageId, replyMarkup);
		}

		public Builder foursquareId(String foursquareId) {
			this.foursquareId = foursquareId;
			return this;
		}

		public Builder disableNotification(Boolean disableNotification) {
			this.disableNotification = disableNotification;
			return this;
		}

		public Builder replyToMessageId(Integer replyToMessageId) {
			this.replyToMessageId = replyToMessageId;
			return this;
		}

		public Builder replyMarkup(AbstractKeyboardMarkup replyMarkup) {
			this.replyMarkup = replyMarkup;
			return this;
		}

	}

}
