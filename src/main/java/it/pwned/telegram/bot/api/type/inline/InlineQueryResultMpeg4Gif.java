package it.pwned.telegram.bot.api.type.inline;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import it.pwned.telegram.bot.api.type.InlineKeyboardMarkup;
import it.pwned.telegram.bot.api.type.ParseMode;

/**
 * Represents a link to a video animation (H.264/MPEG-4 AVC video without
 * sound). By default, this animated MPEG-4 file will be sent by the user with
 * optional caption. Alternatively, you can use
 * {@link InlineQueryResultMpeg4Gif#inputMessageContent inputMessageContent} to
 * send a message with the specified content instead of the animation.
 */
@JsonInclude(Include.NON_NULL)
public class InlineQueryResultMpeg4Gif extends InlineQueryResult {

    private final static String JSON_FIELD_TYPE = "type";
    private final static String JSON_FIELD_ID = "id";
    private final static String JSON_FIELD_MPEG4_URL = "mpeg4_url";
    private final static String JSON_FIELD_MPEG4_WIDTH = "mpeg4_width";
    private final static String JSON_FIELD_MPEG4_HEIGHT = "mpeg4_height";
    private final static String JSON_FIELD_MPEG4_DURATION = "mpeg4_duration";
    private final static String JSON_FIELD_THUMB_URL = "thumb_url";
    private final static String JSON_FIELD_TITLE = "title";
    private final static String JSON_FIELD_CAPTION = "caption";
    private final static String JSON_FIELD_PARSE_MODE = "parse_mode";
    private final static String JSON_FIELD_REPLY_MARKUP = "reply_markup";
    private final static String JSON_FIELD_INPUT_MESSAGE_CONTENT = "input_message_content";

    /**
     * Type of the result, must be mpeg4_gif
     */
    @JsonProperty(JSON_FIELD_TYPE)
    public final String type = "mpeg4_gif";

    /**
     * Unique identifier for this result, 1-64 bytes
     */
    @JsonProperty(JSON_FIELD_ID)
    public final String id;

    /**
     * A valid URL for the MP4 file. File size must not exceed 1MB
     */
    @JsonProperty(JSON_FIELD_MPEG4_URL)
    public final String mpeg4Url;

    /**
     * <em>Optional.</em> Video width
     */
    @JsonProperty(JSON_FIELD_MPEG4_WIDTH)
    public final Integer mpeg4Width;

    /**
     * <em>Optional.</em> Video height
     */
    @JsonProperty(JSON_FIELD_MPEG4_HEIGHT)
    public final Integer mpeg4Height;

    /**
     * <em>Optional.</em> Video duration
     */
    @JsonProperty(JSON_FIELD_MPEG4_DURATION)
    public final Integer mpeg4Duration;

    /**
     * URL of the static thumbnail (jpeg or gif) for the result
     */
    @JsonProperty(JSON_FIELD_THUMB_URL)
    public final String thumbUrl;

    /**
     * <em>Optional.</em> Title for the result
     */
    @JsonProperty(JSON_FIELD_TITLE)
    public final String title;

    /**
     * <em>Optional.</em> Caption of the MPEG-4 file to be sent, 0-200
     * characters
     */
    @JsonProperty(JSON_FIELD_CAPTION)
    public final String caption;

    /**
     * <em>Optional.</em> Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     */
    @JsonProperty(JSON_FIELD_PARSE_MODE)
    public final ParseMode parseMode;

    /**
     * <em>Optional.</em> Inline keyboard attached to the message
     */
    @JsonProperty(JSON_FIELD_REPLY_MARKUP)
    public final InlineKeyboardMarkup replyMarkup;

    /**
     * <em>Optional.</em> Content of the message to be sent instead of the video
     * animation
     */
    @JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT)
    public final InputMessageContent inputMessageContent;

    /**
     * @param id                  Unique identifier for this result, 1-64 bytes
     * @param mpeg4Url            A valid URL for the MP4 file. File size must not exceed 1MB
     * @param mpeg4Width          <em>Optional.</em> Video width
     * @param mpeg4Height         <em>Optional.</em> Video height
     * @param mpeg4Duration       <em>Optional.</em> Video duration
     * @param thumbUrl            URL of the static thumbnail (jpeg or gif) for the result
     * @param title               <em>Optional.</em> Title for the result
     * @param caption             <em>Optional.</em> Caption of the MPEG-4 file to be sent,
     *                            0-200 characters
     * @param parseMode           <em>Optional.</em> Send Markdown or HTML, if you want Telegram apps to show bold, italic, fixed-width text or inline URLs in the media caption.
     * @param replyMarkup         <em>Optional.</em> Inline keyboard attached to the message
     * @param inputMessageContent <em>Optional.</em> Content of the message to be sent instead
     *                            of the video animation
     */
    public InlineQueryResultMpeg4Gif(@JsonProperty(JSON_FIELD_ID) String id,
                                     @JsonProperty(JSON_FIELD_MPEG4_URL) String mpeg4Url,
                                     @JsonProperty(JSON_FIELD_MPEG4_WIDTH) Integer mpeg4Width,
                                     @JsonProperty(JSON_FIELD_MPEG4_HEIGHT) Integer mpeg4Height,
                                     @JsonProperty(JSON_FIELD_MPEG4_DURATION) Integer mpeg4Duration,
                                     @JsonProperty(JSON_FIELD_THUMB_URL) String thumbUrl,
                                     @JsonProperty(JSON_FIELD_TITLE) String title,
                                     @JsonProperty(JSON_FIELD_CAPTION) String caption,
                                     @JsonProperty(JSON_FIELD_PARSE_MODE) ParseMode parseMode,
                                     @JsonProperty(JSON_FIELD_REPLY_MARKUP) InlineKeyboardMarkup replyMarkup,
                                     @JsonProperty(JSON_FIELD_INPUT_MESSAGE_CONTENT) InputMessageContent inputMessageContent) {
        this.id = id;
        this.mpeg4Url = mpeg4Url;
        this.mpeg4Width = mpeg4Width;
        this.mpeg4Height = mpeg4Height;
        this.mpeg4Duration = mpeg4Duration;
        this.thumbUrl = thumbUrl;
        this.title = title;
        this.caption = caption;
        this.parseMode = parseMode;
        this.replyMarkup = replyMarkup;
        this.inputMessageContent = inputMessageContent;

    }

}
