package it.pwned.telegram.bot.api.type;

import static org.junit.Assert.*;

import org.junit.Test;

import it.pwned.telegram.bot.api.type.Message.Util.BotCommand;

public class MessageTest {

	@Test
	public void testHasProperties() {
		Message.Builder builder = new Message.Builder();
		Message m = null;
		
		// reply
		builder.setReplyToMessage(builder.build());
		m = builder.build();
		builder.setReplyToMessage(null);
		assertEquals(true, Message.Util.isReply(m));
		
		// audio
		builder.setAudio(new Audio(null, null, null, null, null, null));
		m = builder.build();
		builder.setAudio(null);
		assertEquals(true, Message.Util.hasAudio(m));
		
		// text
		builder.setText("hello world");
		m = builder.build();
		builder.setText(null);
		assertEquals(true, Message.Util.hasText(m));
		
	}
	
	@Test
	public void testCommandParsing() {
		Message.Builder builder = new Message.Builder();
		Message m = null;
		BotCommand command = null;
		
		builder.setText("/test");
		m = builder.build();
		assertEquals(true, Message.Util.isCommand(m));
		command = Message.Util.parseCommand(m);
		assertEquals("/test", command.command);
		assertEquals(0, command.parameters.length);
		
		builder.setText("/test param1 param2 param3");
		m = builder.build();
		assertEquals(true, Message.Util.isCommand(m));
		command = Message.Util.parseCommand(m);
		assertEquals("/test", command.command);
		assertEquals(3, command.parameters.length);
		assertEquals("param1", command.parameters[0]);
		assertEquals("param2", command.parameters[1]);
		assertEquals("param3", command.parameters[2]);
		
		builder.setText("/test@TestBot param");
		m = builder.build();
		assertEquals(true, Message.Util.isCommand(m));
		
		command = Message.Util.parseCommand(m);
		assertEquals("/test", command.command);
		assertEquals("TestBot", command.recipient);
		assertEquals(1, command.parameters.length);
		assertEquals("param", command.parameters[0]);		
		
	}

}
