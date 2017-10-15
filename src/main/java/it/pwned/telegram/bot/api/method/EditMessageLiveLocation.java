package it.pwned.telegram.bot.api.method;

import it.pwned.telegram.bot.api.type.BooleanOrMessage;
import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("editMessageLiveLocation")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class EditMessageLiveLocation extends AbstractApiMethod<BooleanOrMessage> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("message_id")
    public final Integer messageId;

    @ApiMethodParameter("inline_message_id")
    private final String inlineMessageId;

    @ApiMethodParameter("latitude")
    public final Float latitude;

    @ApiMethodParameter("longitude")
    public final Float longitude;

    @ApiMethodParameter("reply_markup")
    public final AbstractKeyboardMarkup replyMarkup;

    public EditMessageLiveLocation(ChatId chatId, Integer messageId, float latitude, float longitude, AbstractKeyboardMarkup replyMarkup) {
        super();

        this.chatId = validateChatId(chatId);
        this.messageId = validateMessageId(messageId);
        this.inlineMessageId = null;
        this.latitude = latitude;
        this.longitude = longitude;
        this.replyMarkup = replyMarkup;
    }

    public EditMessageLiveLocation(String inlineMessageId, float latitude, float longitude, AbstractKeyboardMarkup replyMarkup) {
        super();

        this.chatId = null;
        this.messageId = null;
        this.inlineMessageId = validateInlineMessageId(inlineMessageId);
        this.latitude = latitude;
        this.longitude = longitude;
        this.replyMarkup = replyMarkup;
    }

    private static ChatId validateChatId(ChatId chatId) {
        if (chatId == null)
            throw new IllegalArgumentException("chatId cannot be null");

        return chatId;
    }

    private static Integer validateMessageId(Integer messageId) {
        if (messageId == null)
            throw new IllegalArgumentException("messageId cannot be null");

        return messageId;
    }

    private static String validateInlineMessageId(String inlineMessageId) {
        if (inlineMessageId == null || "".equals(inlineMessageId))
            throw new IllegalArgumentException("inline  MessageId cannot be null or empty");

        return inlineMessageId;
    }

}
