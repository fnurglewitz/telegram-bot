package it.pwned.telegram.bot.api.method.inline;

import java.util.Collections;
import java.util.LinkedList;
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
	public final String inlineQueryId;

	@ApiMethodParameter("results")
	public final List<InlineQueryResult> results;

	@ApiMethodParameter("cache_time")
	public final Integer cacheTime;

	@ApiMethodParameter("is_personal")
	public final Boolean isPersonal;

	@ApiMethodParameter("next_offset")
	public final String nextOffset;

	@ApiMethodParameter("switch_pm_text")
	public final String switchPmText;

	@ApiMethodParameter("switch_pm_parameter")
	public final String switchPmParameter;

	public AnswerInlineQuery(String inlineQueryId, List<InlineQueryResult> results) {
		this(inlineQueryId, results, null, null, null, null, null);
	}

	public AnswerInlineQuery(String inlineQueryId, List<InlineQueryResult> results, Integer cacheTime, Boolean isPersonal,
			String nextOffset, String switchPmText, String switchPmParameter) {
		super();

		this.inlineQueryId = validateInlineQueryId(inlineQueryId);
		this.results = validateResults(results);
		this.cacheTime = cacheTime;
		this.isPersonal = isPersonal;
		this.nextOffset = nextOffset;
		this.switchPmText = switchPmText;
		this.switchPmParameter = switchPmParameter;
	}

	private static String validateInlineQueryId(String inlineQueryId) {
		if (inlineQueryId == null)
			throw new IllegalArgumentException("inlineQueryId cannot be null");

		return inlineQueryId;
	}

	private static List<InlineQueryResult> validateResults(List<InlineQueryResult> results) {
		if (results == null)
			throw new IllegalArgumentException("results cannot be null");

		if (results.size() > 50)
			throw new IllegalArgumentException("max results size: 50");

		return Collections.unmodifiableList(results);
	}

	public static class Builder {

		private String inlineQueryId;

		private List<InlineQueryResult> results;

		private Integer cacheTime;

		private Boolean isPersonal;

		private String nextOffset;

		private String switchPmText;

		private String switchPmParameter;

		public Builder(String inlineQueryId) {
			this.inlineQueryId = validateInlineQueryId(inlineQueryId);
			this.results = new LinkedList<InlineQueryResult>();
		}

		public AnswerInlineQuery build() {
			validateResults(results);

			return new AnswerInlineQuery(inlineQueryId, results, cacheTime, isPersonal, nextOffset, switchPmText,
					switchPmParameter);
		}

		public Builder cacheTime(Integer cacheTime) {
			this.cacheTime = cacheTime;
			return this;
		}

		public Builder isPersonal(Boolean isPersonal) {
			this.isPersonal = isPersonal;
			return this;
		}

		public Builder nextOffset(String nextOffset) {
			this.nextOffset = nextOffset;
			return this;
		}

		public Builder switchPmText(String switchPmText) {
			this.switchPmText = switchPmText;
			return this;
		}

		public Builder switchPmParameter(String switchPmParameter) {
			this.switchPmParameter = switchPmParameter;
			return this;
		}

		public Builder addResult(InlineQueryResult result) {
			if (result == null)
				throw new IllegalArgumentException("result cannot be null");

			if (results.size() >= 50)
				throw new IllegalArgumentException("max results size: 50");

			this.results.add(result);

			return this;
		}
	}
}
