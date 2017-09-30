package it.pwned.telegram.bot.api.method.sticker;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;

@ApiMethod("setStickerPositionInSet")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class SetStickerPositionInSet extends AbstractApiMethod<Boolean> {

    @ApiMethodParameter("sticker")
    public final String sticker;

    @ApiMethodParameter("position")
    public final Integer position;

    public SetStickerPositionInSet(String sticker, Integer position) {
        this.sticker = validateSticker(sticker);
        this.position = validatePosition(position);
    }

    private static String validateSticker(String sticker) {
        if (sticker == null || "".equals(sticker))
            throw new IllegalArgumentException("sticker cannot be null or empty");

        return sticker;
    }

    private static Integer validatePosition(Integer position) {
        if (position == null)
            throw new IllegalArgumentException("position cannot be null");

        return position;
    }

}
