package it.pwned.telegram.bot.api.method.sticker;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;

@ApiMethod("deleteStickerFromSet")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class DeleteStickerFromSet extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("sticker")
    public final String sticker;

    public DeleteStickerFromSet(String sticker) {
        this.sticker = validateSticker(sticker);
    }

    private static String validateSticker(String sticker) {
        if (sticker == null || "".equals(sticker))
            throw new IllegalArgumentException("sticker cannot be null or empty");

        return sticker;
    }

}
