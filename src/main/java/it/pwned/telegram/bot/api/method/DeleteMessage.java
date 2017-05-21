package it.pwned.telegram.bot.api.method;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;
import org.springframework.http.HttpMethod;

@ApiMethod("deleteMessage")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class DeleteMessage extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("message_id")
    public final Integer messageId;

    public DeleteMessage(ChatId chatId, Integer messageId) {
        super();

        this.chatId = validateChatId(chatId);
        this.messageId = validateMessageId(messageId);
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
