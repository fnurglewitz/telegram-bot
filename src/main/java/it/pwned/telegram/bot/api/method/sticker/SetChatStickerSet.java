package it.pwned.telegram.bot.api.method.sticker;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;

@ApiMethod("setChatStickerSet")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class SetChatStickerSet extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("sticker_set_name")
    public final String stickerSetName;

    public SetChatStickerSet(ChatId chatId, String stickerSetName) {
        this.chatId = validateChatId(chatId);
        this.stickerSetName = validateStickerSetName(stickerSetName);
    }

    private static ChatId validateChatId(ChatId chatId) {
        if (chatId == null)
            throw new IllegalArgumentException("chatId cannot be null");

        return chatId;
    }

    private static String validateStickerSetName(String stickerSetName) {
        if (stickerSetName == null || "".equals(stickerSetName))
            throw new IllegalArgumentException("stickerSetName cannot be null or empty");

        return stickerSetName;
    }

}
