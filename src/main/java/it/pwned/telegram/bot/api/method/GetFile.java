package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.TelegramFile;

@ApiMethod("getFile")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class GetFile extends AbstractApiMethod<TelegramFile> {

	@ApiMethodParameter("file_id")
	public final String fileId;

	public GetFile(String fileId) {
		super();

		this.fileId = validateFileId(fileId);
	}

	private static String validateFileId(String fileId) {
		if (fileId == null || "".equals(fileId))
			throw new IllegalArgumentException("fileId cannot be null or empty");

		return fileId;
	}

}
