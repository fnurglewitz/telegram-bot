package it.pwned.telegram.bot.api.type;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.Resource;

public class TelegramResource implements Resource {
	
	private final String file_id;
	
	public TelegramResource(String file_id) {
		this.file_id = file_id;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(file_id.getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public boolean isReadable() {
		return true;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public URL getURL() throws IOException {
		return null;
	}

	@Override
	public URI getURI() throws IOException {
		return null;
	}

	@Override
	public File getFile() throws IOException {
		return null;
	}

	@Override
	public long contentLength() throws IOException {
		return file_id.length();
	}

	@Override
	public long lastModified() throws IOException {
		return 0;
	}

	@Override
	public Resource createRelative(String relativePath) throws IOException {
		return null;
	}

	@Override
	public String getFilename() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

}
