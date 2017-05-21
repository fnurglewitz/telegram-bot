package it.pwned.telegram.bot.api.type.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import it.pwned.telegram.bot.api.type.User;

/**
 * This object contains information about an incoming shipping query.
 */
public class ShippingQuery {

    private final static String JSON_FIELD_ID = "id";
    private final static String JSON_FIELD_FROM = "from";
    private final static String JSON_FIELD_INVOICE_PAYLOAD = "invoice_payload";
    private final static String JSON_FIELD_SHIPPING_ADDRESS = "shipping_address";

    /**
     * Unique query identifier
     */
    @JsonProperty(JSON_FIELD_ID)
    public final String id;

    /**
     * User who sent the query
     */
    @JsonProperty(JSON_FIELD_FROM)
    public final User from;

    /**
     * Bot specified invoice payload
     */
    @JsonProperty(JSON_FIELD_INVOICE_PAYLOAD)
    public final String invoicePayload;

    /**
     * User specified shipping address
     */
    @JsonProperty(JSON_FIELD_SHIPPING_ADDRESS)
    public final ShippingAddress shippingAddress;

    /**
     * @param id              Unique query identifier
     * @param from            User who sent the query
     * @param invoicePayload  Bot specified invoice payload
     * @param shippingAddress User specified shipping address
     */
    public ShippingQuery(
            @JsonProperty(JSON_FIELD_ID) String id,
            @JsonProperty(JSON_FIELD_FROM) User from,
            @JsonProperty(JSON_FIELD_INVOICE_PAYLOAD) String invoicePayload,
            @JsonProperty(JSON_FIELD_SHIPPING_ADDRESS) ShippingAddress shippingAddress
    ) {
        this.id = id;
        this.from = from;
        this.invoicePayload = invoicePayload;
        this.shippingAddress = shippingAddress;
    }

}
