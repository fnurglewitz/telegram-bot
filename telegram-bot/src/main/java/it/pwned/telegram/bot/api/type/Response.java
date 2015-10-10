package it.pwned.telegram.bot.api.type;

public final class Response<T> {
	public Boolean ok;
	public String description;
	public Integer error_code;
	public T result;
	
}