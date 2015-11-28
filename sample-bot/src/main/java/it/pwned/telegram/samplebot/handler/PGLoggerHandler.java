package it.pwned.telegram.samplebot.handler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.pwned.telegram.bot.MessageHandler;
import it.pwned.telegram.bot.TelegramBot;
import it.pwned.telegram.bot.api.type.File;
import it.pwned.telegram.bot.api.type.Message;
import it.pwned.telegram.bot.api.type.TelegramBotApiException;

@Order(value=2)
public class PGLoggerHandler extends MessageHandler {

	private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

	private JdbcTemplate jdbc;
	private boolean save_files;
	private ObjectMapper mapper;

	public PGLoggerHandler(TelegramBot bot, BlockingQueue<Message> message_queue, ObjectMapper mapper, JdbcTemplate jdbc,
			boolean save_files) {
		super(bot, message_queue);
		this.mapper = mapper;
		this.jdbc = jdbc;
		this.save_files = save_files;
	}

	@Autowired(required = false)
	public void setJdbcTemplate(JdbcTemplate jdbc, @Value("${bot.save-files:#{false}}") Boolean save_files) {
		this.jdbc = jdbc;
		this.save_files = save_files;
	}

	@Override
	protected boolean processMessage(Message m) {
		bot.submitToExecutor(() -> {
			try {
				final String strMsg = mapper.writeValueAsString(m);

				// @formatter:off
				Resource r = null;
				if(save_files) {
					String file_id = null;
					
					if(m.audio!=null)	file_id = m.audio.file_id;
					if(m.document!=null) file_id = m.document.file_id;
					if(m.photo!=null) file_id = m.photo[m.photo.length-1].file_id;;
					if(m.sticker!=null) file_id = m.sticker.file_id;
					if(m.video!=null) file_id = m.video.file_id;
					if(m.voice!=null) file_id = m.voice.file_id;
					if(m.new_chat_photo!=null) file_id = m.new_chat_photo[m.new_chat_photo.length-1].file_id;
					
					if(file_id!=null) {
						File file = bot.api.getFile(file_id);

						r = bot.api.getResourceFromTelegramFile(file);
						
					}
				}
				
				final InputStream is;
				if(r != null)
					is = r.getInputStream();
				else
					is = null;
				
				jdbc.update(
						"insert into message (ts,message,blob) values (current_timestamp,?::json,?);",
						new PreparedStatementSetter() {
					public void setValues(PreparedStatement preparedStatement)
							throws SQLException {
						preparedStatement.setString(1, strMsg);
						if(is==null)
							preparedStatement.setNull(2, java.sql.Types.LONGVARBINARY);
						else
							preparedStatement.setBinaryStream(2, is);
					}
				});
				// @formatter:on
			} catch (IOException | DataAccessException | TelegramBotApiException e) {
				log.error("Error while logging message to DB", e);
			}
		});

		return true;
	}

}
