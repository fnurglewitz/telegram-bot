package it.pwned.telegram.samplebot.handler;

import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import it.pwned.telegram.bot.MessageHandler;
import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.type.ForceReply;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.command.BotCommand;
import net.aksingh.owmjapis.AbstractWeather.Weather;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.OpenWeatherMap.Language;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

@BotCommand(command = "/weather", description = "get current weather at a location")
public final class WeatherHandler extends MessageHandler {

	private OpenWeatherMap owm;

	public WeatherHandler(TelegramBot bot, BlockingQueue<Message> message_queue) {
		super(bot, message_queue);
	}

	@Autowired
	public void setWeatherApi(@Value("${openweather.api-key}") String api_key) {
		this.owm = new OpenWeatherMap(Units.METRIC, Language.ITALIAN, api_key);
	}

	@Override
	protected boolean processMessage(Message m) {

		if (m.is_command) {
			bot.submitToExecutor(() -> {
				Message res;
				try {
					res = bot.api.sendMessage(m.chat.id, "Please send me a location", null, true, m.message_id,
							new ForceReply(true));

					if (res.message_id != null) {
						bot.reserveReply(res.message_id, this);
					}
				} catch (Exception e) {

				}
			});
		} else {

			bot.submitToExecutor(() -> {
				try {
					if (m.location != null) {
						CurrentWeather cw = owm.currentWeatherByCoordinates(m.location.latitude, m.location.longitude);

						bot.api.sendMessage(m.chat.id, getFormattedString(cw), null, null, m.message_id, null);

					} else
						bot.api.sendMessage(m.chat.id, "Send me a location, please", null, null, m.message_id, null);

					bot.releaseReservedReply(m.reply_to_message.message_id);
				} catch (TelegramBotApiException ae) {

				}
			});
		}

		return true;
	}

	private static String getFormattedString(CurrentWeather cw) {

		Weather w = cw.getWeatherInstance(0);

		String location = cw.hasCityName() ? cw.getCityName() : "";
		String weather_desc = w.hasWeatherDescription() ? w.getWeatherDescription() : "";
		Float temp = null;
		Float min_temp = null;
		Float max_temp = null;
		Float pressure = null;
		Float humidity = null;

		if (cw.hasMainInstance()) {
			CurrentWeather.Main cm = cw.getMainInstance();

			temp = cm.hasTemperature() ? cm.getTemperature() : null;
			min_temp = cm.hasMinTemperature() ? cm.getMinTemperature() : null;
			max_temp = cm.hasMaxTemperature() ? cm.getMaxTemperature() : null;
			pressure = cm.hasPressure() ? cm.getPressure() : null;
			humidity = cm.hasHumidity() ? cm.getHumidity() : null;
		}

		return String.format(
				"Current weather in: %s\n%s\nT: %.2f°C\nTmin: %.2f°C\nTmax: %.2f°C\nPressure: %.2f hPa\nHumidity: %.2f%%",
				location, weather_desc, temp, min_temp, max_temp, pressure, humidity);
	}

}
