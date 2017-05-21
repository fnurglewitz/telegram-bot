package it.pwned.telegram.bot.api.method;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.AbstractKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;

@ApiMethod("sendVideoNote")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class SendVideoNote extends AbstractApiMethod<Message> {

    @ApiMethodParameter("chat_id")
    public final ChatId chatId;

    @ApiMethodParameter("video_note")
    public final Resource videoNote;

    @ApiMethodParameter("duration")
    public final Integer duration;

    @ApiMethodParameter("length")
    public final Integer length;

    @ApiMethodParameter("disable_notification")
    public final Boolean disableNotification;

    @ApiMethodParameter("reply_to_message_id")
    public final Integer replyToMessageId;

    @ApiMethodParameter("reply_markup")
    public final AbstractKeyboardMarkup replyMarkup;

    public SendVideoNote(ChatId chatId, Resource videoNote) {
        this(chatId, videoNote, null, null, null, null, null);
    }

    public SendVideoNote(ChatId chatId, Resource videoNote, Integer duration, Integer length) {
        this(chatId, videoNote, duration, length, null, null, null);
    }

    public SendVideoNote(ChatId chatId, Resource videoNote, Integer duration, Integer length, Boolean disableNotification, Integer replyToMessageId, AbstractKeyboardMarkup replyMarkup) {

        this.chatId = validateChatId(chatId);
        this.videoNote = validateVideoNote(videoNote);
        this.duration = duration;
        this.length = length;
        this.disableNotification = disableNotification;
        this.replyToMessageId = replyToMessageId;
        this.replyMarkup = replyMarkup;
    }

    private static ChatId validateChatId(ChatId chatId) {
        if (chatId == null)
            throw new IllegalArgumentException("chatId cannot be null");

        return chatId;
    }

    private static Resource validateVideoNote(Resource videoNote) {
        if (videoNote == null)
            throw new IllegalArgumentException("videoNote cannot be null");

        return videoNote;
    }


}
