package it.pwned.telegram.bot.api.type.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * This object represents one shipping option.
 */
public class ShippingOption {

    private final static String JSON_FIELD_ID = "id";
    private final static String JSON_FIELD_TITLE = "title";
    private final static String JSON_FIELD_PRICES = "prices";

    /**
     * Shipping option identifier
     */
    @JsonProperty(JSON_FIELD_ID)
    public final String id;

    /**
     * Option title
     */
    @JsonProperty(JSON_FIELD_TITLE)
    public final String title;

    /**
     * List of price portions
     */
    @JsonProperty(JSON_FIELD_PRICES)
    public final List<LabeledPrice> prices;

    /**
     * @param id     Shipping option identifier
     * @param title  Option title
     * @param prices List of price portions
     */
    public ShippingOption(
            @JsonProperty(JSON_FIELD_ID) String id,
            @JsonProperty(JSON_FIELD_TITLE) String title,
            @JsonProperty(JSON_FIELD_PRICES) List<LabeledPrice> prices
    ) {
        this.id = id;
        this.title = title;
        this.prices = prices == null ? null : Collections.unmodifiableList(prices);
    }


}
