package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Update implements Comparable<Update> {

	public final Integer update_id;
	public final Message message;
	public final InlineQuery inline_query;
	public final ChosenInlineResult chosen_inline_result;

	public Update(@JsonProperty("update_id") Integer update_id, @JsonProperty("message") Message message,
			@JsonProperty("inline_query") InlineQuery inline_query,
			@JsonProperty("chosen_inline_result") ChosenInlineResult chosen_inline_result) {
		this.update_id = update_id;
		this.message = message;
		this.inline_query = inline_query;
		this.chosen_inline_result = chosen_inline_result;
	}

	@Override
	public int compareTo(Update other) {
		return this.update_id.compareTo(other.update_id);
	}
}
