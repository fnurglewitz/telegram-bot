package it.pwned.telegram.bot.api.method;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("promoteChatMember")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class PromoteChatMember extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("user_id")
    public final Integer userId;

    @ApiMethodParameter("can_change_info")
    public final Boolean canChangeInfo;

    @ApiMethodParameter("can_post_messages")
    public final Boolean canPostMessages;

    @ApiMethodParameter("can_edit_messages")
    public final Boolean canEditMessages;

    @ApiMethodParameter("can_delete_messages")
    public final Boolean canDeleteMessages;

    @ApiMethodParameter("can_invite_users")
    public final Boolean canInviteUsers;

    @ApiMethodParameter("can_restrict_members")
    public final Boolean canRestrictMembers;

    @ApiMethodParameter("can_pin_messages")
    public final Boolean canPinMessages;

    @ApiMethodParameter("can_promote_members")
    public final Boolean canPromoteMembers;

    public PromoteChatMember(ChatId chatId, Integer userId, Boolean canChangeInfo, Boolean canPostMessages, Boolean canEditMessages, Boolean canDeleteMessages,
                             Boolean canInviteUsers, Boolean canRestrictMembers, Boolean canPinMessages, Boolean canPromoteMembers) {
        super();

        this.chatId = validateChatId(chatId);
        this.userId = validateUserId(userId);
        this.canChangeInfo = canChangeInfo;
        this.canPostMessages = canPostMessages;
        this.canEditMessages = canEditMessages;
        this.canDeleteMessages = canDeleteMessages;
        this.canInviteUsers = canInviteUsers;
        this.canRestrictMembers = canRestrictMembers;
        this.canPinMessages = canPinMessages;
        this.canPromoteMembers = canPromoteMembers;
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
