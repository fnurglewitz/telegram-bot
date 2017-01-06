package it.pwned.telegram.bot.api.type;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class MultipartDataEntityHttpMessageConverter implements HttpMessageConverter<MultipartDataEntity> {

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {

		if (clazz == ChatId.class)
			return true;

		if (clazz == ParseMode.class)
			return true;
		
		if (clazz == ChatAction.class)
			return true;
		
		return false;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return null;
	}

	@Override
	public MultipartDataEntity read(Class<? extends MultipartDataEntity> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		return null;
	}

	@Override
	public void write(MultipartDataEntity t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		if (t.getContentType() != null)
			outputMessage.getHeaders().setContentType(t.getContentType());

		outputMessage.getBody().write(t.stringValue().getBytes());

	}

}
