package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This object describes the position on faces where a mask should be placed by default.
 */
public class MaskPosition {

    private final static String JSON_FIELD_POINT = "point";
    private final static String JSON_FIELD_X_SHIFT = "x_shift";
    private final static String JSON_FIELD_Y_SHIFT = "y_shift";
    private final static String JSON_FIELD_SCALE = "scale";

    /**
     * The part of the face relative to which the mask should be placed. One of “forehead”, “eyes”, “mouth”, or “chin”.
     */
    @JsonProperty(JSON_FIELD_POINT)
    public final String point;

    /**
     * Shift by X-axis measured in widths of the mask scaled to the face size, from left to right. For example, choosing -1.0 will place mask just to the left of the default mask position.
     */
    @JsonProperty(JSON_FIELD_X_SHIFT)
    public final Float xShift;

    /**
     * Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom. For example, 1.0 will place the mask just below the default mask position.
     */
    @JsonProperty(JSON_FIELD_Y_SHIFT)
    public final Float yShift;

    /**
     * Mask scaling coefficient. For example, 2.0 means double size.
     */
    @JsonProperty(JSON_FIELD_SCALE)
    public final Float scale;

    /**
     * @param point  The part of the face relative to which the mask should be placed. One of “forehead”, “eyes”, “mouth”, or “chin”.
     * @param xShift Shift by X-axis measured in widths of the mask scaled to the face size, from left to right. For example, choosing -1.0 will place mask just to the left of the default mask position.
     * @param yShift Shift by Y-axis measured in heights of the mask scaled to the face size, from top to bottom. For example, 1.0 will place the mask just below the default mask position.
     * @param scale  Mask scaling coefficient. For example, 2.0 means double size.
     */
    public MaskPosition(@JsonProperty(JSON_FIELD_POINT) String point, @JsonProperty(JSON_FIELD_X_SHIFT) Float xShift,
                        @JsonProperty(JSON_FIELD_Y_SHIFT) Float yShift, @JsonProperty(JSON_FIELD_SCALE) Float scale) {
        this.point = point;
        this.xShift = xShift;
        this.yShift = yShift;
        this.scale = scale;
    }

}
