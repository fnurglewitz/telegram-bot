package it.pwned.telegram.bot.api.type.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents information about an order.
 */
public class OrderInfo {

    private final static String JSON_FIELD_NAME = "name";
    private final static String JSON_FIELD_PHONE_NUMBER = "phone_number";
    private final static String JSON_FIELD_EMAIL = "email";
    private final static String JSON_FIELD_SHIPPING_ADDRESS = "shipping_address";

    /**
     * <em>Optional.</em> User name
     */
    @JsonProperty(JSON_FIELD_NAME)
    public final String name;

    /**
     * <em>Optional.</em> User's phone number
     */
    @JsonProperty(JSON_FIELD_PHONE_NUMBER)
    public final String phoneNumber;

    /**
     * <em>Optional.</em> User email
     */
    @JsonProperty(JSON_FIELD_EMAIL)
    public final String email;

    /**
     * <em>Optional.</em> User shipping address
     */
    @JsonProperty(JSON_FIELD_SHIPPING_ADDRESS)
    public final ShippingAddress shippingAddress;

    /**
     * @param name            <em>Optional.</em> User name
     * @param phoneNumber     <em>Optional.</em> User's phone number
     * @param email           <em>Optional.</em> User email
     * @param shippingAddress <em>Optional.</em> User shipping address
     */
    public OrderInfo(
            @JsonProperty(JSON_FIELD_NAME) String name,
            @JsonProperty(JSON_FIELD_PHONE_NUMBER) String phoneNumber,
            @JsonProperty(JSON_FIELD_EMAIL) String email,
            @JsonProperty(JSON_FIELD_SHIPPING_ADDRESS) ShippingAddress shippingAddress
    ) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.shippingAddress = shippingAddress;
    }

}
