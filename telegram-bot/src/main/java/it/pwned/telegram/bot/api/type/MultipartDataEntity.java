package it.pwned.telegram.bot.api.type;

import org.springframework.http.MediaType;

public interface MultipartDataEntity {

	public String stringValue();
	public MediaType getContentType();
	
}
