package it.pwned.telegram.bot.api.type;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This is an utility abstract class used to pass every kind of keyboard objects
 * to send* methods ({@link it.pwned.telegram.bot.api.TelegramBotApi
 * TelegramBotApi})
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class AbstractKeyboardMarkup {

	protected static <T extends AbstractKeyboardButton> List<List<T>> createKeyboardFromButtonList(List<T> buttons,
			int buttonsPerRow) {

		List<List<T>> keyboard = new ArrayList<List<T>>();

		int rows = 0;

		rows = buttons.size() / buttonsPerRow;

		if (buttons.size() % buttonsPerRow > 0)
			++rows;

		for (int row = 0; row < rows; row++) {

			List<T> tmpRow = new ArrayList<T>(buttonsPerRow);

			for (int btnIdx = 0; btnIdx < buttonsPerRow; btnIdx++) {

				int currentButtonIndex = ((row) * buttonsPerRow) + btnIdx;

				if (currentButtonIndex < buttons.size())
					tmpRow.add(buttons.get(currentButtonIndex));

			}

			keyboard.add(tmpRow);

		}

		return keyboard;
	}

}
