package it.pwned.telegram.bot.api;

import org.springframework.core.io.Resource;

import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.TelegramFile;

public interface ApiClient {

	<T> T call(AbstractApiMethod<T> m) throws TelegramBotApiException;

	Resource getResourceFromTelegramFile(TelegramFile file);

}
