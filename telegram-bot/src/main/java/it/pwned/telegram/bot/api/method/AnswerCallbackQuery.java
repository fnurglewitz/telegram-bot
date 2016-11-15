package it.pwned.telegram.bot.api.method;

import java.security.InvalidParameterException;

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
	private String callbackQueryId;

	@ApiMethodParameter("text")
	private String text;

	@ApiMethodParameter("show_alert")
	private Boolean showAlert;

	@ApiMethodParameter("url")
	private String url;

	public AnswerCallbackQuery(String callbackQueryId) {
		super();

		setCallbackQueryId(callbackQueryId);
	}

	public AnswerCallbackQuery setCallbackQueryId(String callbackQueryId) {
		if (callbackQueryId == null)
			throw new InvalidParameterException("callbackQueryId cannot be null");

		this.callbackQueryId = callbackQueryId;
		return this;
	}

	public AnswerCallbackQuery setText(String text) {
		if (text != null && text.length() > 200)
			throw new InvalidParameterException("text length exceeding maximum value (200)");

		this.text = text;
		return this;
	}

	public AnswerCallbackQuery setShowAlert(Boolean showAlert) {
		this.showAlert = showAlert;
		return this;
	}

	public AnswerCallbackQuery setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getCallbackQueryId() {
		return this.callbackQueryId;
	}

	public String getText() {
		return this.text;
	}

	public Boolean getShowAlert() {
		return this.showAlert;
	}

	public String getUrl() {
		return this.url;
	}

}
