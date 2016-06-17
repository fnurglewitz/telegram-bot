package it.pwned.telegram.samplebot.handler;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import it.pwned.telegram.bot.api.TelegramBotApi;
import it.pwned.telegram.bot.api.type.ChatId;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;
import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.Message.Util.BotCommand;
import it.pwned.telegram.bot.handler.UpdateHandler;

public class AoE2Handler implements UpdateHandler, Runnable {

	private final static Long catafrutti = -146940842L;

	private final TelegramBotApi api;
	private final ThreadPoolTaskExecutor executor;
	private final BlockingQueue<Update> messageQueue;
	private final String tauntsPath;
	private final Map<Integer, String> taunts;
	private final JdbcTemplate jdbc;

	private static final Pattern pattern = Pattern.compile("^([0-9]+)((?:@)?([a-z]{2}))?$");

	public AoE2Handler(TelegramBotApi api, BlockingQueue<Update> messageQueue, ThreadPoolTaskExecutor executor,
			String tauntsPath, JdbcTemplate jdbc) {
		this.api = api;
		this.executor = executor;
		this.messageQueue = messageQueue;
		this.tauntsPath = tauntsPath;
		this.taunts = new ConcurrentHashMap<Integer, String>();
		this.jdbc = jdbc;

	}

	@Override
	public boolean submit(Update u) {
		boolean result = true;
		if (catafrutti.equals(u.message.chat.id) || "private".equals(u.message.chat.type)
				|| (Message.Util.isCommand(u.message) && Message.Util.parseCommand(u.message).command.equals("/aoe"))) {
			try {
				this.messageQueue.put(u);
			} catch (InterruptedException e) {
				// shit happens
			}

			if (u.message != null && u.message.newChatMember != null)
				result = false;
		}
		return result;
	}

	private String getTauntPathByNumberAndCulture(int taunt) {
		return getTauntPathByNumberAndCulture(taunt, "en");
	}

	private String getTauntPathByNumberAndCulture(int taunt, String culture) {

		String tauntString = this.taunts.get(taunt);

		if (tauntString == null)
			this.taunts.get(30); // wololo

		if (culture == null || "".equals(culture))
			culture = "en";

		return String.format("%s/%s/%s", tauntsPath, culture, tauntString);

	}

	private void processUpdate(Update u) {

		Message m = u.message;

		executor.submit(() -> {

			if (m != null && Message.Util.isCommand(m)) {

				BotCommand c = Message.Util.parseCommand(m);

				if (c.parameters.length == 2 && c.parameters[0].equals("lang")) {
					String culture = c.parameters[1];

					if (culture != null && culture.length() == 2) {
						jdbc.update("MERGE INTO AOE2.SETTINGS KEY(USER_ID) VALUES ( ?, ? );", new Object[] { m.from.id, culture });
					}
				}

				return;
			}

			if (m != null && m.newChatMember != null) {
				try {
					api.sendVoice(new ChatId(m.chat.id), new FileSystemResource(getTauntPathByNumberAndCulture(8)), null, null, m.messageId,
							null);
				} catch (TelegramBotApiException e) {
				}

				return;
			}

			if ((m != null && m.text != null)) {

				String query = m.text;

				Matcher matcher = pattern.matcher(query);

				if (matcher.find()) {
					int taunt = Integer.parseInt(matcher.group(1));
					String culture = matcher.group(2);
					if (culture != null)
						culture = culture.substring(1);
					else {
						// fetch culture from h2sql
						try {
							Integer userId = m.from.id != null ? m.from.id : u.inlineQuery.from.id;

							culture = jdbc.queryForObject("SELECT FAVORITE_LANG FROM AOE2.SETTINGS WHERE USER_ID = ?;", String.class,
									userId);
						} catch (Exception e) {
							culture = null;
						}
					}

					String tauntUrl = getTauntPathByNumberAndCulture(taunt, culture);

					try {
						api.sendVoice(new ChatId(m.chat.id), new FileSystemResource(tauntUrl), null, null, null, null);
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
		boolean goOn = true;
		while (goOn || !messageQueue.isEmpty()) {
			try {

				processUpdate(messageQueue.take());

				if (Thread.currentThread().isInterrupted())
					throw new InterruptedException();

			} catch (InterruptedException e) {
				goOn = false;
			}
		}
	}

	@Override
	public void loadState() {

		this.taunts.put(1, "01 yes.mp3");
		this.taunts.put(2, "02 no.mp3");
		this.taunts.put(3, "03 food, please.mp3");
		this.taunts.put(4, "04 wood, please.mp3");
		this.taunts.put(5, "05 gold, please.mp3");
		this.taunts.put(6, "06 stone, please.mp3");
		this.taunts.put(7, "07 ahh.mp3");
		this.taunts.put(8, "08 all hail.mp3");
		this.taunts.put(9, "09 oooh.mp3");
		this.taunts.put(10, "10 back to age 1.mp3");
		this.taunts.put(11, "11 herb laugh.mp3");
		this.taunts.put(12, "12 being rushed.mp3");
		this.taunts.put(13, "13 blame your isp.mp3");
		this.taunts.put(14, "14 start the game.mp3");
		this.taunts.put(15, "15 don't point that thing.mp3");
		this.taunts.put(16, "16 enemy sighted.mp3");
		this.taunts.put(17, "17 it is good.mp3");
		this.taunts.put(18, "18 i need a monk.mp3");
		this.taunts.put(19, "19 long time no siege.mp3");
		this.taunts.put(20, "20 my granny.mp3");
		this.taunts.put(21, "21 nice town i'll take it.mp3");
		this.taunts.put(22, "22 quit touchin.mp3");
		this.taunts.put(23, "23 raiding party.mp3");
		this.taunts.put(24, "24 dadgum.mp3");
		this.taunts.put(25, "25 smite me.mp3");
		this.taunts.put(26, "26 the wonder.mp3");
		this.taunts.put(27, "27 you play 2 hours.mp3");
		this.taunts.put(28, "28 you should see the other guy.mp3");
		this.taunts.put(29, "29 roggan.mp3");
		this.taunts.put(30, "30 wololo.mp3");
		this.taunts.put(31, "31 attack an enemy now.mp3");
		this.taunts.put(32, "32 cease creating extra villagers.mp3");
		this.taunts.put(33, "33 create extra villagers.mp3");
		this.taunts.put(34, "34 build a navy.mp3");
		this.taunts.put(35, "35 stop building a navy.mp3");
		this.taunts.put(36, "36 wait for my signal to attack.mp3");
		this.taunts.put(37, "37 build a wonder.mp3");
		this.taunts.put(38, "38 give me your extra resources.mp3");
		this.taunts.put(39, "39 ally.mp3");
		this.taunts.put(40, "40 enemy.mp3");
		this.taunts.put(41, "41 neutral.mp3");
		this.taunts.put(42, "42 what age are you in.mp3");

		// flyway?
		Integer schemaCheck = jdbc
				.queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME='AOE2';", Integer.class);

		if (schemaCheck == null || new Integer(0).compareTo(schemaCheck) == 0) {
			jdbc.execute("CREATE SCHEMA AOE2;");
			jdbc.execute("CREATE TABLE AOE2.SETTINGS ( USER_ID BIGINT PRIMARY KEY, FAVORITE_LANG VARCHAR(50));");
		} else {
			schemaCheck = jdbc.queryForObject(
					"SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='AOE2' AND TABLE_NAME='SETTINGS';",
					Integer.class);

			if (schemaCheck == null || new Integer(0).compareTo(schemaCheck) == 0) {
				jdbc.execute("CREATE TABLE AOE2.SETTINGS ( USER_ID BIGINT PRIMARY KEY, FAVORITE_LANG VARCHAR(50));");
			}
		}

	}

	@Override
	public void saveState() {
	}

	@Override
	public String getName() {
		return "AoE2Handler";
	}

}
