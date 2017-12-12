package it.pwned.telegram.bot.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.pwned.telegram.bot.api.method.*;
import it.pwned.telegram.bot.api.method.payment.AnswerPreCheckoutQuery;
import it.pwned.telegram.bot.api.method.payment.AnswerShippingQuery;
import it.pwned.telegram.bot.api.method.payment.SendInvoice;
import it.pwned.telegram.bot.api.method.sticker.*;
import it.pwned.telegram.bot.api.method.webhook.DeleteWebhook;
import it.pwned.telegram.bot.api.method.webhook.GetWebhookInfo;
import it.pwned.telegram.bot.api.method.webhook.SetWebhook;
import it.pwned.telegram.bot.api.type.*;
import org.springframework.core.ParameterizedTypeReference;

import it.pwned.telegram.bot.api.method.game.GetGameHighScores;
import it.pwned.telegram.bot.api.method.game.SendGame;
import it.pwned.telegram.bot.api.method.game.SetGameScore;
import it.pwned.telegram.bot.api.method.inline.AnswerInlineQuery;
import it.pwned.telegram.bot.api.method.update.EditMessageCaption;
import it.pwned.telegram.bot.api.method.update.EditMessageReplyMarkup;
import it.pwned.telegram.bot.api.method.update.EditMessageText;
import it.pwned.telegram.bot.api.type.game.GameHighScore;

public final class ApiMethodReturnTypes {

    /*
     * For anyone wondering why this map exists:
     * http://stackoverflow.com/questions/21987295/using-spring-resttemplate-in-
     * generic-method-with-generic-parameter
     *
     * ParameterizedTypeReference uses Class#getGenericSuperclass which states:
     * Returns the Type representing the direct superclass of the entity (class,
     * interface, primitive type or void) represented by this Class. If the
     * superclass is a parameterized type, the Type object returned must
     * accurately reflect the actual type parameters used in the source code.
     */
    @SuppressWarnings("rawtypes")
    public static final Map<Class, ParameterizedTypeReference> typeRefs = initializeParameterizedTypeReferences();

    @SuppressWarnings("rawtypes")
    private static Map<Class, ParameterizedTypeReference> initializeParameterizedTypeReferences() {

        Map<Class<?>, ParameterizedTypeReference> tmp = new HashMap<Class<?>, ParameterizedTypeReference>();

        tmp.put(GetUpdates.class, new ParameterizedTypeReference<Response<List<Update>>>() {
        });

        tmp.put(GetMe.class, new ParameterizedTypeReference<Response<User>>() {
        });

        tmp.put(SendMessage.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(ForwardMessage.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendPhoto.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendAudio.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendDocument.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendSticker.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendVideo.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendVoice.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendLocation.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendVenue.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendContact.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SendChatAction.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(GetUserProfilePhotos.class, new ParameterizedTypeReference<Response<UserProfilePhotos>>() {
        });

        tmp.put(GetFile.class, new ParameterizedTypeReference<Response<TelegramFile>>() {
        });

        tmp.put(KickChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(LeaveChat.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(UnbanChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(GetChat.class, new ParameterizedTypeReference<Response<Chat>>() {
        });

        tmp.put(GetChatAdministrators.class, new ParameterizedTypeReference<Response<List<ChatMember>>>() {
        });

        tmp.put(GetChatMembersCount.class, new ParameterizedTypeReference<Response<Integer>>() {
        });

        tmp.put(GetChatMember.class, new ParameterizedTypeReference<Response<ChatMember>>() {
        });

        tmp.put(AnswerCallbackQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(EditMessageText.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
        });

        tmp.put(EditMessageCaption.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
        });

        tmp.put(EditMessageReplyMarkup.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
        });

        tmp.put(AnswerInlineQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(SendGame.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(SetGameScore.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
        });

        tmp.put(GetGameHighScores.class, new ParameterizedTypeReference<Response<List<GameHighScore>>>() {
        });

        tmp.put(SetWebhook.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(DeleteWebhook.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(GetWebhookInfo.class, new ParameterizedTypeReference<Response<WebhookInfo>>() {
        });

        tmp.put(SendVideoNote.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(DeleteMessage.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(SendInvoice.class, new ParameterizedTypeReference<Response<Message>>() {
        });

        tmp.put(AnswerShippingQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(AnswerPreCheckoutQuery.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(RestrictChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(PromoteChatMember.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(ExportChatInviteLink.class, new ParameterizedTypeReference<Response<String>>() {
        });

        tmp.put(SetChatPhoto.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(DeleteChatPhoto.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(SetChatTitle.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(SetChatDescription.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(PinChatMessage.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(UnpinChatMessage.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(GetStickerSet.class, new ParameterizedTypeReference<Response<StickerSet>>() {
        });

        tmp.put(UploadStickerFile.class, new ParameterizedTypeReference<Response<TelegramFile>>() {
        });

        tmp.put(CreateNewStickerSet.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(AddStickerToSet.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(SetStickerPositionInSet.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(DeleteStickerFromSet.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(EditMessageLiveLocation.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
        });

        tmp.put(StopMessageLiveLocation.class, new ParameterizedTypeReference<Response<BooleanOrMessage>>() {
        });

        tmp.put(SetChatStickerSet.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(DeleteChatStickerSet.class, new ParameterizedTypeReference<Response<Boolean>>() {
        });

        tmp.put(SendMediaGroup.class, new ParameterizedTypeReference<Response<List<Message>>>() {
        });

        return Collections.unmodifiableMap(tmp);
    }

}
