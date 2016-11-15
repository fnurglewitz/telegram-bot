package it.pwned.telegram.bot.api.method.inline;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.http.HttpMethod;

import it.pwned.telegram.bot.api.AbstractApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodContentType;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodHttpMethod;
import it.pwned.telegram.bot.api.method.annotation.ApiMethodParameter;
import it.pwned.telegram.bot.api.method.enums.MethodMediaType;
import it.pwned.telegram.bot.api.type.inline.InlineQueryResult;

@ApiMethod("answerInlineQuery")
@ApiMethodHttpMethod(HttpMethod.POST)
@ApiMethodContentType(MethodMediaType.MULTIPART_FORM_DATA)
public final class AnswerInlineQuery extends AbstractApiMethod<Boolean> {

	@ApiMethodParameter("inline_query_id")
	private String inlineQueryId;

	@ApiMethodParameter("results")
	private List<InlineQueryResult> results;

	@ApiMethodParameter("cache_time")
	private Integer cacheTime;

	@ApiMethodParameter("is_personal")
	private Boolean isPersonal;

	@ApiMethodParameter("next_offset")
	private String nextOffset;

	@ApiMethodParameter("switch_pm_text")
	private String switchPmText;

	@ApiMethodParameter("switch_pm_parameter")
	private String switchPmParameter;

	public AnswerInlineQuery(String inlineQueryId, List<InlineQueryResult> results) {
		super();

		setInlineQueryId(inlineQueryId);
		setResults(results);
	}

	public AnswerInlineQuery setInlineQueryId(String inlineQueryId) {
		if (inlineQueryId == null)
			throw new InvalidParameterException("inlineQueryId cannot be null");

		this.inlineQueryId = inlineQueryId;
		return this;
	}

	public AnswerInlineQuery setResults(List<InlineQueryResult> results) {
		if (results == null)
			throw new InvalidParameterException("results cannot be null");

		if (results.size() > 50)
			throw new InvalidParameterException("max results size: 50");

		this.results = results;
		return this;
	}

	public AnswerInlineQuery setCacheTime(Integer cacheTime) {
		this.cacheTime = cacheTime;
		return this;
	}

	public AnswerInlineQuery setIsPersonal(Boolean isPersonal) {
		this.isPersonal = isPersonal;
		return this;
	}

	public AnswerInlineQuery setNextOffset(String nextOffset) {
		this.nextOffset = nextOffset;
		return this;
	}

	public AnswerInlineQuery setSwitchPmText(String switchPmText) {
		this.switchPmText = switchPmText;
		return this;
	}

	public AnswerInlineQuery setSwitchPmParameter(String switchPmParameter) {
		this.switchPmParameter = switchPmParameter;
		return this;
	}

	public String getInlineQueryId() {
		return this.inlineQueryId;
	}

	public List<InlineQueryResult> getResults() {
		return this.results;
	}

	public Integer getCacheTime() {
		return this.cacheTime;
	}

	public Boolean getIsPersonal() {
		return this.isPersonal;
	}

	public String getNextOffset() {
		return this.nextOffset;
	}

	public String getSwitchPmText() {
		return this.switchPmText;
	}

	public String getSwitchPmParameter() {
		return this.switchPmParameter;
	}

	public void addResult(InlineQueryResult result) {
		if (result == null)
			throw new InvalidParameterException("result cannot be null");

		if (results.size() >= 50)
			throw new InvalidParameterException("max results size: 50");

		this.results.add(result);
	}
}
