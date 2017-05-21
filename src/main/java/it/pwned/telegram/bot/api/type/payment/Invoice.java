package it.pwned.telegram.bot.api.type.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object contains basic information about an invoice.
 */
public class Invoice {

    private final static String JSON_FIELD_TITLE = "title";
    private final static String JSON_FIELD_DESCRIPTION = "description";
    private final static String JSON_FIELD_START_PARAMETER = "start_parameter";
    private final static String JSON_FIELD_CURRENCY = "currency";
    private final static String JSON_FIELD_TOTAL_AMOUNT = "total_amount";

    /**
     * Product name
     */
    @JsonProperty(JSON_FIELD_TITLE)
    public final String title;

    /**
     * Product description
     */
    @JsonProperty(JSON_FIELD_DESCRIPTION)
    public final String description;

    /**
     * Unique bot deep-linking parameter that can be used to generate this invoice
     */
    @JsonProperty(JSON_FIELD_START_PARAMETER)
    public final String startParameter;

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
     * @param title          Product name
     * @param description    Product description
     * @param startParameter Unique bot deep-linking parameter that can be used to generate this invoice
     * @param currency       Three-letter ISO 4217 currency code
     * @param totalAmount    Total price in the smallest units of the currency (integer, not float/double). For example, for a price of US$ 1.45 pass amount = 145. See the exp parameter in currencies.json, it shows the number of digits past the decimal point for each currency (2 for the majority of currencies).
     */
    public Invoice(@JsonProperty(JSON_FIELD_TITLE) String title, @JsonProperty(JSON_FIELD_DESCRIPTION) String description,
                   @JsonProperty(JSON_FIELD_START_PARAMETER) String startParameter, @JsonProperty(JSON_FIELD_CURRENCY) String currency,
                   @JsonProperty(JSON_FIELD_TOTAL_AMOUNT) Integer totalAmount) {
        this.title = title;
        this.description = description;
        this.startParameter = startParameter;
        this.currency = currency;
        this.totalAmount = totalAmount;
    }

}

