package it.pwned.telegram.bot.api.type;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ParseMode.ParseModeSerializer.class)
public enum ParseMode {
	MARKDOWN("Markdown"), HTML("HTML"), PLAIN("");

	private final String value;

	private ParseMode(String value) {
		this.value = value;
	}
	
	public static ParseMode fromString(String mode) {
		switch(mode) {
		case "Markdown":
			return ParseMode.MARKDOWN;
		case "HTML":
			return ParseMode.HTML;
		default:
			return ParseMode.PLAIN;
		}
	}

	@JsonValue
	@Override
	public String toString() {
		return value;
	}
	
	public static class ParseModeSerializer extends JsonSerializer<ParseMode> {

		@Override
		public void serialize(ParseMode value, JsonGenerator jgen, SerializerProvider arg2)
				throws IOException, JsonProcessingException {
      	jgen.writeRaw(value.toString());
		}
		
	}

}
