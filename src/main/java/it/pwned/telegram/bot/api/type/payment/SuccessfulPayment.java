package it.pwned.telegram.bot.api.type.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object contains basic information about a successful payment.
 */
public class SuccessfulPayment {

    private final static String JSON_FIELD_CURRENCY = "currency";
    private final static String JSON_FIELD_TOTAL_AMOUNT = "total_amount";
    private final static String JSON_FIELD_INVOICE_PAYLOAD = "invoice_payload";
    private final static String JSON_FIELD_SHIPPING_OPTION_ID = "shipping_option_id";
    private final static String JSON_FIELD_ORDER_INFO = "order_info";
    private final static String JSON_FIELD_TELEGRAM_PAYMENT_CHARGE_ID = "telegram_payment_charge_id";
    private final static String JSON_FIELD_PROVIDER_PAYMENT_CHARGE_ID = "provider_payment_charge_id";

    /**
     * Three-letter ISO 4217 currency code
     */
    @JsonProperty(JSON_FIELD_CURRENCY)
    public final String currency;

    /**
     * Total price in the smallest units of the currency (integer, not float/double).
     * For example, for a price of US$ 1.45 pass amount = 145.
     * See the exp parameter in currencies.json,
     * it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @JsonProperty(JSON_FIELD_TOTAL_AMOUNT)
    public final Integer totalAmount;

    /**
     * Bot specified invoice payload
     */
    @JsonProperty(JSON_FIELD_INVOICE_PAYLOAD)
    public final String invoicePayload;

    /**
     * <em>Optional.</em> Identifier of the shipping option chosen by the user
     */
    @JsonProperty(JSON_FIELD_SHIPPING_OPTION_ID)
    public final String shippingOptionId;

    /**
     * <em>Optional.</em> Order info provided by the user
     */
    @JsonProperty(JSON_FIELD_ORDER_INFO)
    public final OrderInfo orderInfo;

    /**
     * Telegram payment identifier
     */
    @JsonProperty(JSON_FIELD_TELEGRAM_PAYMENT_CHARGE_ID)
    public final String telegramPaymentChargeId;

    /**
     * Provider payment identifier
     */
    @JsonProperty(JSON_FIELD_PROVIDER_PAYMENT_CHARGE_ID)
    public final String providerPaymentChargeId;

    /**
     * @param currency                Three-letter ISO 4217 currency code
     * @param totalAmount             Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     * @param invoicePayload          Bot specified invoice payload
     * @param shippingOptionId        <em>Optional.</em> Identifier of the shipping option chosen by the user
     * @param orderInfo               <em>Optional.</em> Order info provided by the user
     * @param telegramPaymentChargeId Telegram payment identifier
     * @param providerPaymentChargeId Provider payment identifier
     */
    public SuccessfulPayment(
            @JsonProperty(JSON_FIELD_CURRENCY) String currency,
            @JsonProperty(JSON_FIELD_TOTAL_AMOUNT) Integer totalAmount,
            @JsonProperty(JSON_FIELD_INVOICE_PAYLOAD) String invoicePayload,
            @JsonProperty(JSON_FIELD_SHIPPING_OPTION_ID) String shippingOptionId,
            @JsonProperty(JSON_FIELD_ORDER_INFO) OrderInfo orderInfo,
            @JsonProperty(JSON_FIELD_TELEGRAM_PAYMENT_CHARGE_ID) String telegramPaymentChargeId,
            @JsonProperty(JSON_FIELD_PROVIDER_PAYMENT_CHARGE_ID) String providerPaymentChargeId
    ) {
        this.currency = currency;
        this.totalAmount = totalAmount;
        this.invoicePayload = invoicePayload;
        this.shippingOptionId = shippingOptionId;
        this.orderInfo = orderInfo;
        this.telegramPaymentChargeId = telegramPaymentChargeId;
        this.providerPaymentChargeId = providerPaymentChargeId;
    }


}
