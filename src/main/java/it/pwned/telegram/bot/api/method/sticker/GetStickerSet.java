package it.pwned.telegram.bot.api.method.sticker;

import it.pwned.telegram.bot.api.type.StickerSet;
import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;

@ApiMethod("getStickerSet")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class GetStickerSet extends AbstractApiMethod<StickerSet> {

    @ApiMethodParameter("name")
    public final String name;


    public GetStickerSet(String name) {
        this.name = validateName(name);
    }

    private static String validateName(String name) {
        if (name == null || "".equals(name))
            throw new IllegalArgumentException("name cannot be null or empty");

        return name;
    }

}
