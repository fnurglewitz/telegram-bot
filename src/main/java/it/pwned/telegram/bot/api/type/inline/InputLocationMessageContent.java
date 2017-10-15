package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Represents the content of a location message to be sent as the result of an
 * inline query.
 */
@JsonInclude(Include.NON_NULL)
public class InputLocationMessageContent extends InputMessageContent {

    private final static String JSON_FIELD_LATITUDE = "latitude";
    private final static String JSON_FIELD_LONGITUDE = "longitude";
    private final static String JSON_FIELD_LIVE_PERIOD = "live_period";

    /**
     * Latitude of the location in degrees
     */
    @JsonProperty(JSON_FIELD_LATITUDE)
    public final Float latitude;

    /**
     * Longitude of the location in degrees
     */
    @JsonProperty(JSON_FIELD_LONGITUDE)
    public final Float longitude;

    /**
     * <em>Optional.</em> Period in seconds for which the location can be updated, should be between 60 and 86400.
     */
    @JsonProperty(JSON_FIELD_LIVE_PERIOD)
    public final Integer livePeriod;

    /**
     * @param latitude  Latitude of the location in degrees
     * @param longitude Longitude of the location in degrees
     */
    public InputLocationMessageContent(@JsonProperty(JSON_FIELD_LATITUDE) Float latitude,
                                       @JsonProperty(JSON_FIELD_LONGITUDE) Float longitude,
                                       @JsonProperty(JSON_FIELD_LIVE_PERIOD) Integer livePeriod) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.livePeriod = validateLivePeriod(livePeriod);

    }

    private static Integer validateLivePeriod(Integer livePeriod) {
        if (livePeriod != null && (livePeriod < 60 || livePeriod > 86400))
            throw new IllegalArgumentException("invalid livePeriod value");

        return livePeriod;
    }

}
