package it.pwned.telegram.bot.api.method.sticker;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("deleteChatStickerSet")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class DeleteChatStickerSet extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    public DeleteChatStickerSet(ChatId chatId, String stickerSetName) {
        this.chatId = validateChatId(chatId);
    }

    private static ChatId validateChatId(ChatId chatId) {
        if (chatId == null)
            throw new IllegalArgumentException("chatId cannot be null");

        return chatId;
    }

}
