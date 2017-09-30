package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("exportChatInviteLink")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class ExportChatInviteLink extends AbstractApiMethod<String> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    public ExportChatInviteLink(ChatId chatId) {
        this.chatId = validateChatId(chatId);
    }

    private static ChatId validateChatId(ChatId chatId) {
        if (chatId == null)
            throw new IllegalArgumentException("chatId cannot be null");

        return chatId;
    }
}
