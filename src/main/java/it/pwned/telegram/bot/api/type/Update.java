package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.inline.ChosenInlineResult;
import it.pwned.telegram.bot.api.type.inline.InlineQuery;
import it.pwned.telegram.bot.api.type.payment.PreCheckoutQuery;
import it.pwned.telegram.bot.api.type.payment.ShippingQuery;

/**
 * This class represents an incoming update. Only <b>one</b> of the optional
 * parameters can be present in any given update.
 */
public final class Update implements Comparable<Update> {

    private final static String JSON_FIELD_UPDATE_ID = "update_id";
    private final static String JSON_FIELD_MESSAGE = "message";
    private final static String JSON_FIELD_EDITED_MESSAGE = "edited_message";
    private final static String JSON_FIELD_CHANNEL_POST = "channel_post";
    private final static String JSON_FIELD_EDITED_CHANNEL_POST = "edited_channel_post";
    private final static String JSON_FIELD_INLINE_QUERY = "inline_query";
    private final static String JSON_FIELD_CHOSEN_INLINE_RESULT = "chosen_inline_result";
    private final static String JSON_FIELD_CALLBACK_QUERY = "callback_query";
    private final static String JSON_FIELD_SHIPPING_QUERY = "shipping_query";
    private final static String JSON_FIELD_PRE_CHECKOUT_QUERY = "pre_checkout_query";

    /**
     * The update's unique identifier. Update identifiers start from a certain
     * positive number and increase sequentially.
     */
    @JsonProperty(JSON_FIELD_UPDATE_ID)
    public final Integer updateId;

    /**
     * <em>Optional.</em> New incoming message of any kind — text, photo, sticker,
     * etc.
     */
    @JsonProperty(JSON_FIELD_MESSAGE)
    public final Message message;

    /**
     * <em>Optional.</em> New version of a message that is known to the bot and
     * was edited
     */
    @JsonProperty(JSON_FIELD_EDITED_MESSAGE)
    public final Message editedMessage;

    /**
     * <em>Optional.</em> New incoming channel post of any kind — text, photo,
     * sticker, etc.
     */
    @JsonProperty(JSON_FIELD_CHANNEL_POST)
    public final Message channelPost;

    /**
     * <em>Optional.</em> New version of a channel post that is known to the bot
     * and was edited
     */
    @JsonProperty(JSON_FIELD_EDITED_CHANNEL_POST)
    public final Message editedChannelPost;

    /**
     * <em>Optional.</em> New incoming inline query
     */
    @JsonProperty(JSON_FIELD_INLINE_QUERY)
    public final InlineQuery inlineQuery;

    /**
     * <em>Optional.</em> The result of an inline query that was chosen by a user
     * and sent to their chat partner.
     */
    @JsonProperty(JSON_FIELD_CHOSEN_INLINE_RESULT)
    public final ChosenInlineResult chosenInlineResult;

    /**
     * <em>Optional.</em> New incoming callback query
     */
    @JsonProperty(JSON_FIELD_CALLBACK_QUERY)
    public final CallbackQuery callbackQuery;

    /**
     * <em>Optional.</em> New incoming shipping query. Only for invoices with flexible price
     */
    @JsonProperty(JSON_FIELD_SHIPPING_QUERY)
    public final ShippingQuery shippingQuery;

    /**
     * <em>Optional.</em> New incoming pre-checkout query. Contains full information about checkout
     */
    @JsonProperty(JSON_FIELD_PRE_CHECKOUT_QUERY)
    public final PreCheckoutQuery preCheckoutQuery;

    /**
     * @param updateId           The update's unique identifier. Update identifiers start from a
     *                           certain positive number and increase sequentially.
     * @param message            <em>Optional.</em> New incoming message of any kind — text, photo,
     *                           sticker, etc.
     * @param editedMessage      <em>Optional.</em> New version of a message that is known to the
     *                           bot and was edited
     * @param inlineQuery        <em>Optional.</em> New incoming inline query
     * @param chosenInlineResult <em>Optional.</em> The result of an inline query that was chosen
     *                           by a user and sent to their chat partner.
     * @param callbackQuery      <em>Optional.</em> New incoming callback query
     * @param shippingQuery      <em>Optional.</em> New incoming shipping query. Only for invoices with flexible price
     * @param preCheckoutQuery   <em>Optional.</em> New incoming pre-checkout query. Contains full information about checkout
     */
    public Update(@JsonProperty(JSON_FIELD_UPDATE_ID) Integer updateId, @JsonProperty(JSON_FIELD_MESSAGE) Message message,
                  @JsonProperty(JSON_FIELD_EDITED_MESSAGE) Message editedMessage,
                  @JsonProperty(JSON_FIELD_CHANNEL_POST) Message channelPost,
                  @JsonProperty(JSON_FIELD_EDITED_CHANNEL_POST) Message editedChannelPost,
                  @JsonProperty(JSON_FIELD_INLINE_QUERY) InlineQuery inlineQuery,
                  @JsonProperty(JSON_FIELD_CHOSEN_INLINE_RESULT) ChosenInlineResult chosenInlineResult,
                  @JsonProperty(JSON_FIELD_CALLBACK_QUERY) CallbackQuery callbackQuery,
                  @JsonProperty(JSON_FIELD_SHIPPING_QUERY) ShippingQuery shippingQuery,
                  @JsonProperty(JSON_FIELD_PRE_CHECKOUT_QUERY) PreCheckoutQuery preCheckoutQuery
    ) {
        this.updateId = updateId;
        this.message = message;
        this.editedMessage = editedMessage;
        this.channelPost = channelPost;
        this.editedChannelPost = editedChannelPost;
        this.inlineQuery = inlineQuery;
        this.chosenInlineResult = chosenInlineResult;
        this.callbackQuery = callbackQuery;
        this.shippingQuery = shippingQuery;
        this.preCheckoutQuery = preCheckoutQuery;
    }

    @Override
    public int compareTo(Update other) {
        return this.updateId.compareTo(other.updateId);
    }

    public static class Util {

        public static boolean isMessage(Update u) {
            return u.message != null;
        }

        public static boolean isInline(Update u) {
            return (u.inlineQuery != null || u.chosenInlineResult != null || u.callbackQuery != null);
        }

        public static boolean hasInlineQuery(Update u) {
            return u.inlineQuery != null;
        }

        public static boolean hasInlineResult(Update u) {
            return u.chosenInlineResult != null;
        }

        public static boolean hasCallbackQuery(Update u) {
            return u.callbackQuery != null;
        }

    }

    public static class Builder {

        private Integer updateId;
        private Message message;
        private Message editedMessage;
        private Message channelPost;
        private Message editedChannelPost;
        private InlineQuery inlineQuery;
        private ChosenInlineResult chosenInlineResult;
        private CallbackQuery callbackQuery;
        private ShippingQuery shippingQuery;
        private PreCheckoutQuery preCheckoutQuery;

        public Builder() {
        }

        public Update build() {
            return new Update(updateId, message, editedMessage, channelPost, editedChannelPost, inlineQuery,
                    chosenInlineResult, callbackQuery, shippingQuery, preCheckoutQuery);
        }

        public Builder setUpdateId(Integer updateId) {
            this.updateId = updateId;
            return this;
        }

        public Builder setMessage(Message message) {
            this.message = message;
            return this;
        }

        public Builder setEditedMessage(Message editedMessage) {
            this.editedMessage = editedMessage;
            return this;
        }

        public Builder setChannelPost(Message channelPost) {
            this.channelPost = channelPost;
            return this;
        }

        public Builder setEditedChannelPost(Message editedChannelPost) {
            this.editedChannelPost = editedChannelPost;
            return this;
        }

        public Builder setInlineQuery(InlineQuery inlineQuery) {
            this.inlineQuery = inlineQuery;
            return this;
        }

        public Builder setChosenInlineResult(ChosenInlineResult chosenInlineResult) {
            this.chosenInlineResult = chosenInlineResult;
            return this;
        }

        public Builder setCallbackQuery(CallbackQuery callbackQuery) {
            this.callbackQuery = callbackQuery;
            return this;
        }

        public Builder setShippingQuery(ShippingQuery shippingQuery) {
            this.shippingQuery = shippingQuery;
            return this;
        }

        public Builder setPreCheckoutQuery(PreCheckoutQuery preCheckoutQuery) {
            this.preCheckoutQuery = preCheckoutQuery;
            return this;
        }

    }
}
