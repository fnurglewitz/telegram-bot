package it.pwned.telegram.bot.api.method.payment;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.payment.LabeledPrice;
import org.springframework.http.HttpMethod;

import java.util.List;

@ApiMethod("sendInvoice")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class SendInvoice extends AbstractApiMethod<Message> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("title")
    public final String title;

    @ApiMethodParameter("description")
    public final String description;

    @ApiMethodParameter("payload")
    public final String payload;

    @ApiMethodParameter("provider_token")
    public final String providerToken;

    @ApiMethodParameter("start_parameter")
    public final String startParameter;

    @ApiMethodParameter("currency")
    public final String currency;

    @ApiMethodParameter("prices")
    public final List<LabeledPrice> prices;

    @ApiMethodParameter("provider_data")
    public final String providerData;

    @ApiMethodParameter("photo_url")
    public final String photoUrl;

    @ApiMethodParameter("photo_size")
    public final Integer photoSize;

    @ApiMethodParameter("photo_width")
    public final Integer photoWidth;

    @ApiMethodParameter("photo_height")
    public final Integer photoHeight;

    @ApiMethodParameter("need_name")
    public final Boolean needName;

    @ApiMethodParameter("need_phone_number")
    public final Boolean needPhoneNumber;

    @ApiMethodParameter("need_email")
    public final Boolean needEmail;

    @ApiMethodParameter("need_shipping_address")
    public final Boolean needShippingAddress;

    @ApiMethodParameter("is_flexible")
    public final Boolean isFlexible;

    @ApiMethodParameter("disable_notification")
    public final Boolean disableNotification;

    @ApiMethodParameter("reply_to_message_id")
    public final Integer replyToMessageId;

    @ApiMethodParameter("reply_markup")
    public final AbstractKeyboardMarkup replyMarkup;

    public SendInvoice(ChatId chatId,
                       String title,
                       String description,
                       String payload,
                       String providerToken,
                       String startParameter,
                       String currency,
                       List<LabeledPrice> prices,
                       String providerData,
                       String photoUrl,
                       Integer photoSize,
                       Integer photoWidth,
                       Integer photoHeight,
                       Boolean needName,
                       Boolean needPhoneNumber,
                       Boolean needEmail,
                       Boolean needShippingAddress,
                       Boolean isFlexible,
                       Boolean disableNotification,
                       Integer replyToMessageId,
                       AbstractKeyboardMarkup replyMarkup) {
        this.chatId = validateChatId(chatId);
        this.title = validateTitle(title);
        this.description = validateDescription(description);
        this.payload = validatePayload(payload);
        this.providerToken = validateProviderToken(providerToken);
        this.startParameter = validateStartParameter(startParameter);
        this.currency = validateCurrency(currency);
        this.prices = validatePrices(prices);
        this.providerData = validateProviderData(providerData);
        this.photoUrl = photoUrl;
        this.photoSize = photoSize;
        this.photoWidth = photoWidth;
        this.photoHeight = photoHeight;
        this.needName = needName;
        this.needPhoneNumber = needPhoneNumber;
        this.needEmail = needEmail;
        this.needShippingAddress = needShippingAddress;
        this.isFlexible = isFlexible;
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
        if (title == null || "".equals(title))
            throw new IllegalArgumentException("title cannot be null or empty");

        return title;
    }

    private static String validateDescription(String description) {
        if (description == null || "".equals(description))
            throw new IllegalArgumentException("description cannot be null or empty");

        return description;
    }

    private static String validatePayload(String payload) {
        int payloadBytes = payload.getBytes().length;
        if (payload == null || "".equals(payload) || payloadBytes > 128)
            throw new IllegalArgumentException("payload cannot be null or empty and must be in range [1,128]");

        return payload;
    }

    private static String validateProviderToken(String providerToken) {
        if (providerToken == null || "".equals(providerToken))
            throw new IllegalArgumentException("providerToken cannot be null or empty");

        return providerToken;
    }

    private static String validateStartParameter(String startParameter) {
        if (startParameter == null || "".equals(startParameter))
            throw new IllegalArgumentException("startParameter cannot be null or empty");

        return startParameter;
    }

    private static String validateCurrency(String currency) {
        if (currency == null || "".equals(currency))
            throw new IllegalArgumentException("currency cannot be null or empty");

        return currency;
    }

    private static List<LabeledPrice> validatePrices(List<LabeledPrice> prices) {
        if (prices == null || prices.size() == 0)
            throw new IllegalArgumentException("prices cannot be null or empty");

        return prices;
    }

    private static String validateProviderData(String providerData) {
        // TODO: check that providerData is a valid JSON
        return providerData;
    }

}
