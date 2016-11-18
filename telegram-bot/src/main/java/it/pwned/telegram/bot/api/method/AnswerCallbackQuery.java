package it.pwned.telegram.bot.api.method;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;

@ApiMethod("answerCallbackQuery")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public class AnswerCallbackQuery extends AbstractApiMethod<Boolean> {

	@ApiMethodParameter("callback_query_id")
	public final String callbackQueryId;

	@ApiMethodParameter("text")
	public final String text;

	@ApiMethodParameter("show_alert")
	public final Boolean showAlert;

	@ApiMethodParameter("url")
	public final String url;

	public AnswerCallbackQuery(String callbackQueryId, String text, Boolean showAlert, String url) {
		super();

		this.callbackQueryId = validateCallbackQueryId(callbackQueryId);
		this.text = validateText(text);
		this.showAlert = showAlert;
		this.url = url;
	}

	private static String validateCallbackQueryId(String callbackQueryId) {
		if (callbackQueryId == null)
			throw new IllegalArgumentException("callbackQueryId cannot be null");

		return callbackQueryId;
	}

	public static String validateText(String text) {
		if (text != null && text.length() > 200)
			throw new IllegalArgumentException("text length exceeding maximum value (200)");

		return text;
	}

	public static class Builder {

		private String callbackQueryId;

		private String text;

		private Boolean showAlert;

		private String url;

		public Builder(String callbackQueryId) {
			this.callbackQueryId = validateCallbackQueryId(callbackQueryId);
		}

		public AnswerCallbackQuery build() {
			return new AnswerCallbackQuery(callbackQueryId, text, showAlert, url);
		}

		public Builder text(String text) {
			this.text = validateText(text);
			return this;
		}

		public Builder showAlert(Boolean showAlert) {
			this.showAlert = showAlert;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

	}

}
