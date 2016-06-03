package it.pwned.telegram.bot.api.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.pwned.telegram.bot.api.type.inline.ChosenInlineResult;
import it.pwned.telegram.bot.api.type.inline.InlineQuery;

public final class Update implements Comparable<Update> {

	public final Integer update_id;
	public final Message message;
	public final Message edited_message;
	public final InlineQuery inline_query;
	public final ChosenInlineResult chosen_inline_result;
	public final CallbackQuery callback_query;

	@JsonIgnore
	public final Boolean is_inline;

	public Update(@JsonProperty("update_id") Integer update_id, @JsonProperty("message") Message message,
			@JsonProperty("edited_message") Message edited_message, @JsonProperty("inline_query") InlineQuery inline_query,
			@JsonProperty("chosen_inline_result") ChosenInlineResult chosen_inline_result,
			@JsonProperty("callback_query") CallbackQuery callback_query) {
		this.update_id = update_id;
		this.message = message;
		this.edited_message = edited_message;
		this.inline_query = inline_query;
		this.chosen_inline_result = chosen_inline_result;
		this.callback_query = callback_query;

		this.is_inline = (inline_query != null || chosen_inline_result != null) ? true : false;

	}

	@Override
	public int compareTo(Update other) {
		return this.update_id.compareTo(other.update_id);
	}

	public static class Builder {

		private Integer update_id;
		private Message message;
		private Message edited_message;
		private InlineQuery inline_query;
		private ChosenInlineResult chosen_inline_result;
		private CallbackQuery callback_query;

		public Builder() {
		}

		public Update build() {
			return new Update(update_id, message, edited_message, inline_query, chosen_inline_result, callback_query);
		}

		public Builder setUpdateId(Integer update_id) {
			this.update_id = update_id;
			return this;
		}

		public Builder setMessage(Message message) {
			this.message = message;
			return this;
		}

		public Builder setEditedMessage(Message edited_message) {
			this.edited_message = edited_message;
			return this;
		}

		public Builder setInlineQuery(InlineQuery inline_query) {
			this.inline_query = inline_query;
			return this;
		}

		public Builder setChosenInlineResult(ChosenInlineResult chosen_inline_result) {
			this.chosen_inline_result = chosen_inline_result;
			return this;
		}

		public Builder setCallbackQuery(CallbackQuery callback_query) {
			this.callback_query = callback_query;
			return this;
		}

	}
}
