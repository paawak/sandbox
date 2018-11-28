package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.swayam.geektrust.goldencrown.model.Kingdom;

public class MessagingCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testExecute_initialMessage() {
	// given
	MessagingCommand testClass = new MessagingCommand(null, null, 0);

	// when
	String result = testClass.execute("Messages to 3   Kingdoms   from King Shan");

	// then
	assertEquals("Enter the 3 messages to send", result);
    }

    @Test
    public void testExecute_inputMessage_no() {
	// given
	MessagingCommand testClass = new MessagingCommand(null, null, 0);

	// when, then
	thrown.expect(IllegalArgumentException.class);
	thrown.expectMessage("Invalid message format");
	testClass.execute("abrcadabra");
    }

    @Test
    public void testGetKingdomToSendMessage() {
	// given
	MessagingCommand testClass = new MessagingCommand(null, null, 0);

	// when
	Kingdom result = testClass.getKingdomToSendMessage("Air, \"Let’s swing the sword together\"");

	// then
	assertEquals(Kingdom.AIR, result);
    }

    @Test
    public void testGetMessageToSend() {
	// given
	MessagingCommand testClass = new MessagingCommand(null, null, 0);

	// when
	String result = testClass.getMessageToSend("Air, \"Let’s swing the sword together\"");

	// then
	assertEquals("Let’s swing the sword together", result);
    }

}
