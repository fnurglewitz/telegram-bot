package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

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
	private String fileId;

	public GetFile(String fileId) {
		super();

		setFileId(fileId);
	}

	public GetFile setFileId(String fileId) {
		if (fileId == null)
			throw new InvalidParameterException("fileId cannot be null");

		this.fileId = fileId;
		return this;
	}

	public String getFileId() {
		return this.fileId;
	}

}
