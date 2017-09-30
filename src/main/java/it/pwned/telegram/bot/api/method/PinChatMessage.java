package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("pinChatMessage")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class PinChatMessage  extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("message_id")
    public final Integer messageId;

    @ApiMethodParameter("disable_notification")
    public final Boolean disableNotification;

    public PinChatMessage(ChatId chatId, Integer messageId, Boolean disableNotification) {
        this.chatId = validateChatId(chatId);
        this.messageId = validateMessageId(messageId);
        this.disableNotification = disableNotification;
    }

    public PinChatMessage(ChatId chatId, Integer messageId) {
        this(chatId, messageId, null);
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

}
