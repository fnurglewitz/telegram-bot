package it.pwned.telegram.bot.api.method;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.InputMedia;
import it.pwned.telegram.bot.api.type.Message;
import org.springframework.http.HttpMethod;

import java.util.List;

@ApiMethod("sendMediaGroup")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class SendMediaGroup extends AbstractApiMethod<List<Message>> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("media")
    public final List<InputMedia> media;

    @ApiMethodParameter("disable_notification")
    public final Boolean disableNotification;

    @ApiMethodParameter("reply_to_message_id")
    public final Integer replyToMessageId;

    public SendMediaGroup(ChatId chatId, List<InputMedia> media, Boolean disableNotification, Integer replyToMessageId) {
        this.chatId = validateChatId(chatId);
        this.media = validateMedia(media);
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
    }

    public SendMediaGroup(ChatId chatId, List<InputMedia> media) {
        this(chatId, media, null, null);
    }

    private static ChatId validateChatId(ChatId chatId) {
        if (chatId == null)
            throw new IllegalArgumentException("chatId cannot be null");

        return chatId;
    }

    private static List<InputMedia> validateMedia(List<InputMedia> media) {
        if(media == null || media.size() < 2 || media.size() > 10)
            throw new IllegalArgumentException("media must include from 2 to 10 elements");

        return media;
    }

}
