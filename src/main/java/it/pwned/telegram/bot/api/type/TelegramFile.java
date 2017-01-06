package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class represents a file ready to be downloaded. The file can be
 * downloaded via the link
 * https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt;. It is
 * guaranteed that the link will be valid for at least 1 hour. When the link
 * expires, a new one can be requested by calling
 * {@link it.pwned.telegram.bot.api.TelegramBotApi#getFile
 * TelegramBotApi#getFile}.
 *
 */
public class TelegramFile {

	private final static String JSON_FIELD_FILE_ID = "file_id";
	private final static String JSON_FIELD_FILE_SIZE = "file_size";
	private final static String JSON_FIELD_FILE_PATH = "file_path";

	/**
	 * Unique identifier for this file
	 */
	@JsonProperty(JSON_FIELD_FILE_ID)
	public final String fileId;

	/**
	 * <em>Optional.</em> File size, if known
	 */
	@JsonProperty(JSON_FIELD_FILE_SIZE)
	public final Integer filesize;

	/**
	 * <em>Optional.</em> File path. Use
	 * https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt; to get the
	 * file.
	 */
	@JsonProperty(JSON_FIELD_FILE_PATH)
	public final String filePath;

	/**
	 * 
	 * @param fileId
	 *          Unique identifier for this file
	 * @param fileSize
	 *          <em>Optional.</em> File size, if known
	 * @param filePath
	 *          <em>Optional.</em> File path. Use
	 *          https://api.telegram.org/file/bot&lt;token&gt;/&lt;file_path&gt;
	 *          to get the file.
	 */
	public TelegramFile(@JsonProperty(JSON_FIELD_FILE_ID) String fileId,
			@JsonProperty(JSON_FIELD_FILE_SIZE) Integer fileSize, @JsonProperty(JSON_FIELD_FILE_PATH) String filePath) {
		this.fileId = fileId;
		this.filesize = fileSize;
		this.filePath = filePath;
	}

}
