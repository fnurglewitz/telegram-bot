package it.pwned.telegram.bot.api.method.sticker;

import it.pwned.telegram.bot.api.type.MaskPosition;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;

@ApiMethod("addStickerToSet")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class AddStickerToSet extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("user_id")
    public final Integer userId;

    @ApiMethodParameter("name")
    public final String name;

    @ApiMethodParameter("png_sticker")
    public final Resource pngSticker;

    @ApiMethodParameter("emojis")
    public final String emojis;

    @ApiMethodParameter("mask_position")
    public final MaskPosition maskPosition;

    public AddStickerToSet(Integer userId, String name, Resource pngSticker, String emojis, MaskPosition maskPosition) {
        this.userId = validateUserId(userId);
        this.name = validateName(name);
        this.pngSticker = validatePngSticker(pngSticker);
        this.emojis = validateEmojis(emojis);
        this.maskPosition = maskPosition;
    }

    private static Integer validateUserId(Integer userId) {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");

        return userId;
    }

    private static String validateName(String name) {
        if (name == null || "".equals(name))
            throw new IllegalArgumentException("name cannot be null or empty");

        return name;
    }

    private static Resource validatePngSticker(Resource pngSticker) {
        if (pngSticker == null)
            throw new IllegalArgumentException("pngSticker cannot be null");

        /**
         * TODO: Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px. Pass a file_id as a String to send a file that already exists on the Telegram servers, pass an HTTP URL as a String for Telegram to get a file from the Internet, or upload a new one using multipart/form-data
         */

        return pngSticker;
    }

    public static String validateEmojis(String emojis) {
        if (emojis == null || "".equals(emojis))
            throw new IllegalArgumentException("emojis cannot be null or empty");

        return emojis;
    }

}
