package it.pwned.telegram.bot.api.method;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("restrictChatMember")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class RestrictChatMember extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("user_id")
    public final Integer userId;

    @ApiMethodParameter("until_date")
    public final Integer untilDate;

    @ApiMethodParameter("can_send_messages")
    public final Boolean canSendMessages;

    @ApiMethodParameter("can_send_media_messages")
    public final Boolean canSendMediaMessages;

    @ApiMethodParameter("can_send_other_messages")
    public final Boolean canSendOtherMessages;

    @ApiMethodParameter("can_add_web_page_previews")
    public final Boolean canAddWebPagePreviews;


    public RestrictChatMember(ChatId chatId, Integer userId, Integer untilDate, Boolean canSendMessages, Boolean canSendMediaMessages, Boolean canSendOtherMessages, Boolean canAddWebPagePreviews) {
        super();

        this.chatId = validateChatId(chatId);
        this.userId = validateUserId(userId);
        this.untilDate = untilDate;
        this.canSendMessages = canSendMessages;
        this.canSendMediaMessages = canSendMediaMessages;
        this.canSendOtherMessages = canSendOtherMessages;
        this.canAddWebPagePreviews = canAddWebPagePreviews;
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
