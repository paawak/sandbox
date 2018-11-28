package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
	assertTrue(result.length() == 0);
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

}
