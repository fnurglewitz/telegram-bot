package it.pwned.telegram.bot.api.type.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object represents a portion of the price for goods or services.
 */
public class LabeledPrice {

    private final static String JSON_FIELD_LABEL = "label";
    private final static String JSON_FIELD_AMOUNT = "amount";

    /**
     * Portion label
     */
    @JsonProperty(JSON_FIELD_LABEL)
    public String label;

    /**
     * Price of the product in the smallest units of the currency (integer, not float/double).
     * For example, for a price of US$ 1.45 pass amount = 145.
     * See the exp parameter in currencies.json,
     * it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    @JsonProperty(JSON_FIELD_AMOUNT)
    public Integer amount;

    /**
     * @param label  Portion label
     * @param amount Price of the product in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    public LabeledPrice(@JsonProperty(JSON_FIELD_LABEL) String label, @JsonProperty(JSON_FIELD_AMOUNT) Integer amount) {
        this.label = label;
        this.amount = amount;
    }


}
