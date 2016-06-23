package it.pwned.telegram.samplebot.handler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.jdbc.core.JdbcTemplate;

import it.pwned.telegram.bot.api.type.Update;
import it.pwned.telegram.bot.api.type.User;
import it.pwned.telegram.bot.handler.UpdateHandler;

public class UserDataHandler implements UpdateHandler, Runnable {

	private final JdbcTemplate jdbc;
	private final BlockingQueue<User> userQueue;

	private volatile boolean goOn = true;

	public UserDataHandler(JdbcTemplate jdbc, BlockingQueue<User> userQueue) {
		this.jdbc = jdbc;
		this.userQueue = userQueue;
	}

	@Override
	public boolean submit(Update u) {

		if (Update.Util.isMessage(u)) {
			userQueue.add(u.message.from);
		}

		if (Update.Util.hasInlineQuery(u)) {
			userQueue.add(u.inlineQuery.from);
		}

		return true;
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
	public String getName() {
		return "UserDataHandler";
	}

	@Override
	public void loadState() {
	}

	@Override
	public void saveState() {
	}

	@Override
	public void run() {

		while (goOn) {

			try {
				final User u = userQueue.take();
				
				int count = 0;

				count = jdbc.update("UPDATE PUBLIC.USER SET FIRST_NAME = ?, LAST_NAME = ?, USERNAME = ? WHERE USER_ID = ? ;",
						new Object[] { u.firstName, u.lastName, u.username, u.id }, new int[] { java.sql.Types.VARCHAR,
								java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.BIGINT });
				
				if(count <= 0) {
					// insert
					jdbc.update("INSERT INTO PUBLIC.USER ( USER_ID, FIRST_NAME, LAST_NAME, USERNAME ) VALUES ( ?, ?, ?, ? );",
							new Object[] { u.id, u.firstName, u.lastName, u.username },
							new int[] { java.sql.Types.BIGINT, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR  }
							);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
