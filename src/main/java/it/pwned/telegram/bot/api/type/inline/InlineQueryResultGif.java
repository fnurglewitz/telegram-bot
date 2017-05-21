package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;

/**
 * Represents a link to an animated GIF file. By default, this animated GIF file
 * will be sent by the user with optional caption. Alternatively, you can use
 * {@link InlineQueryResultGif#inputMessageContent inputMessageContent} to send
 * a message with the specified content instead of the animation.
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultGif extends InlineQueryResult {

    private final static String JSON_FIELD_TYPE = "type";
    private final static String JSON_FIELD_ID = "id";
    private final static String JSON_FIELD_GIF_URL = "gif_url";
    private final static String JSON_FIELD_GIF_WIDTH = "gif_width";
    private final static String JSON_FIELD_GIF_HEIGHT = "gif_height";
    private final static String JSON_FIELD_GIF_DURATION = "gif_duration";
    private final static String JSON_FIELD_THUMB_URL = "thumb_url";
    private final static String JSON_FIELD_TITLE = "title";
    private final static String JSON_FIELD_CAPTION = "caption";
    private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
    private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

    /**
     * Type of the result, must be gif
     */
    @JsonProperty(JSON_FIELD_TYPE)
    public final String type = "gif";

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty(JSON_FIELD_ID)
    public final String id;

    /**
     * A valid URL for the GIF file. File size must not exceed 1MB
     */
    @JsonProperty(JSON_FIELD_GIF_URL)
    public final String gifUrl;

    /**
     * <em>Optional.</em> Width of the GIF
     */
    @JsonProperty(JSON_FIELD_GIF_WIDTH)
    public final Integer gifWidth;

    /**
     * <em>Optional.</em> Height of the GIF
     */
    @JsonProperty(JSON_FIELD_GIF_HEIGHT)
    public final Integer gifHeight;

    /**
     * <em>Optional.</em> Duration of the GIF
     */
    @JsonProperty(JSON_FIELD_GIF_DURATION)
    public final Integer gifDuration;

    /**
     * URL of the static thumbnail for the result (jpeg or gif)
     */
    @JsonProperty(JSON_FIELD_THUMB_URL)
    public final String thumbUrl;

    /**
     * <em>Optional.</em> Title for the result
     */
    @JsonProperty(JSON_FIELD_TITLE)
    public final String title;

    /**
     * <em>Optional.</em> Caption of the GIF file to be sent, 0-200 characters
     */
    @JsonProperty(JSON_FIELD_CAPTION)
    public final String caption;

    /**
     * <em>Optional.</em> Inline keyboard attached to the message
     */
    @JsonProperty(JSON_FIELD_REPLY_MARKUP)
    public final InlineKeyboardMarkup replyMarkup;

    /**
     * <em>Optional.</em> Content of the message to be sent instead of the GIF
     * animation
     */
    @JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
    public final InputMessageContent inputMessageContent;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes
     * @param gifUrl              A valid URL for the GIF file. File size must not exceed 1MB
     * @param gifWidth            <em>Optional.</em> Width of the GIF
     * @param gifHeight           <em>Optional.</em> Height of the GIF
     * @param gifDuration         <em>Optional.</em> Duration of the GIF
     * @param thumbUrl            URL of the static thumbnail for the result (jpeg or gif)
     * @param title               <em>Optional.</em> Title for the result
     * @param caption             <em>Optional.</em> Caption of the GIF file to be sent, 0-200
     *                            characters
     * @param replyMarkup         <em>Optional.</em> Inline keyboard attached to the message
     * @param inputMessageContent <em>Optional.</em> Content of the message to be sent instead
     *                            of the GIF animation
     */
    public InlineQueryResultGif(@JsonProperty(JSON_FIELD_ID) String id,
                                @JsonProperty(JSON_FIELD_GIF_URL) String gifUrl,
                                @JsonProperty(JSON_FIELD_GIF_WIDTH) Integer gifWidth,
                                @JsonProperty(JSON_FIELD_GIF_HEIGHT) Integer gifHeight,
                                @JsonProperty(JSON_FIELD_GIF_DURATION) Integer gifDuration,
                                @JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl,
                                @JsonProperty(JSON_FIELD_TITLE) String title,
                                @JsonProperty(JSON_FIELD_CAPTION) String caption,
                                @JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
                                @JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
        this.id = id;
        this.gifUrl = gifUrl;
        this.gifWidth = gifWidth;
        this.gifHeight = gifHeight;
        this.gifDuration = gifDuration;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.caption = caption;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;

    }

}
