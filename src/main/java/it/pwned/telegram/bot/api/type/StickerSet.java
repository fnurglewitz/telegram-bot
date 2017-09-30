package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * This object represents a sticker set.
 */
public class StickerSet {

    private final static String JSON_FIELD_NAME = "name";
    private final static String JSON_FIELD_TITLE = "title";
    private final static String JSON_FIELD_CONTAINS_MASKS = "contains_masks";
    private final static String JSON_FIELD_STICKERS = "stickers";

    /**
     * Sticker set name
     */
    @JsonProperty(JSON_FIELD_NAME)
    public final String name;

    /**
     * Sticker set title
     */
    @JsonProperty(JSON_FIELD_TITLE)
    public final String title;

    /**
     * True, if the sticker set contains masks
     */
    @JsonProperty(JSON_FIELD_CONTAINS_MASKS)
    public final Boolean containsMasks;

    /**
     * List of all set stickers
     */
    @JsonProperty(JSON_FIELD_STICKERS)
    public final List<Sticker> stickers;

    /**
     * @param name          Sticker set name
     * @param title         Sticker set title
     * @param containsMasks True, if the sticker set contains masks
     * @param stickers      List of all set stickers
     */
    public StickerSet(@JsonProperty(JSON_FIELD_NAME) String name, @JsonProperty(JSON_FIELD_TITLE) String title,
                      @JsonProperty(JSON_FIELD_CONTAINS_MASKS) Boolean containsMasks, @JsonProperty(JSON_FIELD_STICKERS) List<Sticker> stickers) {
        this.name = name;
        this.title = title;
        this.containsMasks = containsMasks;
        this.stickers = stickers == null ? null : Collections.unmodifiableList(stickers);
    }

}
