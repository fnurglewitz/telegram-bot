package it.pwned.telegram.bot.api.method.sticker;

import it.pwned.telegram.bot.api.type.TelegramFile;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;

@ApiMethod("uploadStickerFile")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class UploadStickerFile extends AbstractApiMethod<TelegramFile> {

    @ApiMethodParameter("user_id")
    public final Integer userId;

    @ApiMethodParameter("png_sticker")
    public final Resource pngSticker;

    public UploadStickerFile(Integer userId, Resource pngSticker) {
        this.userId = validateUserId(userId);
        this.pngSticker = validatePngSticker(pngSticker);
    }

    private static Integer validateUserId(Integer userId) {
        if (userId == null)
            throw new IllegalArgumentException("userId cannot be null");

        return userId;
    }

    private static Resource validatePngSticker(Resource pngSticker) {
        if (pngSticker == null)
            throw new IllegalArgumentException("pngSticker cannot be null");

        /**
         * TODO: Png image with the sticker, must be up to 512 kilobytes in size, dimensions must not exceed 512px, and either width or height must be exactly 512px.
         */

        return pngSticker;
    }

}
