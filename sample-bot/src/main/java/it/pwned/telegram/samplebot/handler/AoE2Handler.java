package it.pwned.telegram.samplebot.handler;

import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.handler.UpdateHandler;

public class AoE2Handler implements UpdateHandler, Runnable {

	private final static Long catafrutti = -146940842L;

	private final TelegramBotApi api;
	private final ThreadPoolTaskExecutor executor;
	private final BlockingQueue<Message> message_queue;
	private final String taunts_path;

	private static final Pattern pattern = Pattern.compile("^([0-9]+)((?:@)?([a-z]{2}))?$");

	public AoE2Handler(TelegramBotApi api, BlockingQueue<Message> message_queue, ThreadPoolTaskExecutor executor,
			String taunts_path) {
		this.api = api;
		this.executor = executor;
		this.message_queue = message_queue;
		this.taunts_path = taunts_path;

	}

	@Override
	public boolean submit(Update u) {
		boolean result = true;
		if (catafrutti.equals(u.message.chat.id) || "private".equals(u.message.chat.type)) {
			try {
				this.message_queue.put(u.message);
			} catch (InterruptedException e) {
				// shit happens
			}

			if (u.message.new_chat_member != null)
				result = false;
		}
		return result;
	}

	private String getTauntPathByNumberAndCulture(int taunt) {
		return getTauntPathByNumberAndCulture(taunt, "en");
	}

	private String getTauntPathByNumberAndCulture(int taunt, String culture) {

		String taunt_string;

		if (culture == null || "".equals(culture))
			culture = "en";

		switch (taunt) {
		case 1:
			taunt_string = "01 yes.mp3";
			break;
		case 2:
			taunt_string = "02 no.mp3";
			break;
		case 3:
			taunt_string = "03 food, please.mp3";
			break;
		case 4:
			taunt_string = "04 wood, please.mp3";
			break;
		case 5:
			taunt_string = "05 gold, please.mp3";
			break;
		case 6:
			taunt_string = "06 stone, please.mp3";
			break;
		case 7:
			taunt_string = "07 ahh.mp3";
			break;
		case 8:
			taunt_string = "08 all hail.mp3";
			break;
		case 9:
			taunt_string = "09 oooh.mp3";
			break;
		case 10:
			taunt_string = "10 back to age 1.mp3";
			break;
		case 11:
			taunt_string = "11 herb laugh.mp3";
			break;
		case 12:
			taunt_string = "12 being rushed.mp3";
			break;
		case 13:
			taunt_string = "13 blame your isp.mp3";
			break;
		case 14:
			taunt_string = "14 start the game.mp3";
			break;
		case 15:
			taunt_string = "15 don't point that thing.mp3";
			break;
		case 16:
			taunt_string = "16 enemy sighted.mp3";
			break;
		case 17:
			taunt_string = "17 it is good.mp3";
			break;
		case 18:
			taunt_string = "18 i need a monk.mp3";
			break;
		case 19:
			taunt_string = "19 long time no siege.mp3";
			break;
		case 20:
			taunt_string = "20 my granny.mp3";
			break;
		case 21:
			taunt_string = "21 nice town i'll take it.mp3";
			break;
		case 22:
			taunt_string = "22 quit touchin.mp3";
			break;
		case 23:
			taunt_string = "23 raiding party.mp3";
			break;
		case 24:
			taunt_string = "24 dadgum.mp3";
			break;
		case 25:
			taunt_string = "25 smite me.mp3";
			break;
		case 26:
			taunt_string = "26 the wonder.mp3";
			break;
		case 27:
			taunt_string = "27 you play 2 hours.mp3";
			break;
		case 28:
			taunt_string = "28 you should see the other guy.mp3";
			break;
		case 29:
			taunt_string = "29 roggan.mp3";
			break;
		case 30:
			taunt_string = "30 wololo.mp3";
			break;
		case 31:
			taunt_string = "31 attack an enemy now.mp3";
			break;
		case 32:
			taunt_string = "32 cease creating extra villagers.mp3";
			break;
		case 33:
			taunt_string = "33 create extra villagers.mp3";
			break;
		case 34:
			taunt_string = "34 build a navy.mp3";
			break;
		case 35:
			taunt_string = "35 stop building a navy.mp3";
			break;
		case 36:
			taunt_string = "36 wait for my signal to attack.mp3";
			break;
		case 37:
			taunt_string = "37 build a wonder.mp3";
			break;
		case 38:
			taunt_string = "38 give me your extra resources.mp3";
			break;
		case 39:
			taunt_string = "39 ally.mp3";
			break;
		case 40:
			taunt_string = "40 enemy.mp3";
			break;
		case 41:
			taunt_string = "41 neutral.mp3";
			break;
		case 42:
			taunt_string = "42 what age are you in.mp3";
			break;
		default:
			taunt_string = "30 wololo.mp3";
		}
		return String.format("%s/%s/%s", taunts_path, culture, taunt_string);

	}

	private void processMessage(Message m) {

		executor.submit(() -> {

			if (m.new_chat_member != null) {
				try {
					api.sendVoice(m.chat.id, new FileSystemResource(getTauntPathByNumberAndCulture(8)), null, null, m.message_id,
							null);
				} catch (TelegramBotApiException e) {
				}
			} else if (m.text != null) {

				Matcher matcher = pattern.matcher(m.text);

				if (matcher.find()) {
					int taunt = Integer.parseInt(matcher.group(1));
					String culture = matcher.group(2);
					if (culture != null)
						culture = culture.substring(1);

					try {
						api.sendVoice(m.chat.id, new FileSystemResource(getTauntPathByNumberAndCulture(taunt, culture)), null, null,
								null, null);
					} catch (TelegramBotApiException e) {
					}
				}

			}
		});

	}

	@Override
	public boolean requiresThread() {
		return true;
	}

	@Override
	public Runnable getRunnable() {
		return this;
	}

	@Override
	public void run() {
		boolean go_on = true;
		while (go_on || !message_queue.isEmpty()) {
			try {

				processMessage(message_queue.take());

				if (Thread.currentThread().isInterrupted())
					throw new InterruptedException();

			} catch (InterruptedException e) {
				go_on = false;
			}
		}
	}

	@Override
	public void loadState() {
	}

	@Override
	public void saveState() {
	}

}
