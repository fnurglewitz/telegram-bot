package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("unbanChatMember")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class UnbanChatMember extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("user_id")
    public final Integer userId;

    public UnbanChatMember(ChatId chatId, Integer userId) {
        super();

        this.chatId = validateChatId(chatId);
        this.userId = validateUserId(userId);
    }

    private static ChatId validateChatId(ChatId chatId) {
        if (chatId == null)
            throw new IllegalArgumentException("chatId cannot be null");

        return chatId;
    }

    private static Integer validateUserId(Integer userId) {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");

        return userId;
    }

}
