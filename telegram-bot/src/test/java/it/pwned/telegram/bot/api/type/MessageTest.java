package it.pwned.telegram.bot.api.type;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
