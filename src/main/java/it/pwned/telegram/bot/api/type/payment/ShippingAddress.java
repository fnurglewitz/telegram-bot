package it.pwned.telegram.bot.api.type.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a shipping address.
 */
public class ShippingAddress {

    private final static String JSON_FIELD_COUNTRY_CODE = "country_code";
    private final static String JSON_FIELD_STATE = "state";
    private final static String JSON_FIELD_CITY = "city";
    private final static String JSON_FIELD_STREET_LINE1 = "street_line1";
    private final static String JSON_FIELD_STREET_LINE2 = "street_line2";
    private final static String JSON_FIELD_POST_CODE = "post_code";

    /**
     * ISO 3166-1 alpha-2 country code
     */
    @JsonProperty(JSON_FIELD_COUNTRY_CODE)
    public final String countryCode;

    /**
     * State, if applicable
     */
    @JsonProperty(JSON_FIELD_STATE)
    public final String state;

    /**
     * City
     */
    @JsonProperty(JSON_FIELD_CITY)
    public final String city;

    /**
     * First line for the address
     */
    @JsonProperty(JSON_FIELD_STREET_LINE1)
    public final String streetLine1;

    /**
     * Second line for the address
     */
    @JsonProperty(JSON_FIELD_STREET_LINE2)
    public final String streetLine2;

    /**
     * Address post code
     */
    @JsonProperty(JSON_FIELD_POST_CODE)
    public final String postCode;

    /**
     * @param countryCode ISO 3166-1 alpha-2 country code
     * @param state       State, if applicable
     * @param city        City
     * @param streetLine1 First line for the address
     * @param streetLine2 Second line for the address
     * @param postCode    Address post code
     */
    public ShippingAddress(
            @JsonProperty(JSON_FIELD_COUNTRY_CODE) String countryCode,
            @JsonProperty(JSON_FIELD_STATE) String state,
            @JsonProperty(JSON_FIELD_CITY) String city,
            @JsonProperty(JSON_FIELD_STREET_LINE1) String streetLine1,
            @JsonProperty(JSON_FIELD_STREET_LINE2) String streetLine2,
            @JsonProperty(JSON_FIELD_POST_CODE) String postCode
    ) {
        this.countryCode = countryCode;
        this.state = state;
        this.city = city;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.postCode = postCode;
    }

}
