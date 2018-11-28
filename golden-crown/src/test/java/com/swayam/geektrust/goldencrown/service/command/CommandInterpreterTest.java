package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

public class CommandInterpreterTest {

    @Test
    public void testParseCommand_FIND_RULER_mixed_case() {
	// given
	CommandInterpreter testClass = new CommandInterpreter(null);

	// when
	Command result = testClass.parseCommand("Who is the ruler of Southeros?");

	// then
	assertTrue(result instanceof RulerFinderCommand);
    }

    @Test
    public void testParseCommand_FIND_RULER_lower_case() {
	// given
	CommandInterpreter testClass = new CommandInterpreter(null);

	// when
	Command result = testClass.parseCommand("who is the ruler of southeros?");

	// then
	assertTrue(result instanceof RulerFinderCommand);
    }

    @Test
    public void testParseCommand_FIND_RULER_upper_case() {
	// given
	CommandInterpreter testClass = new CommandInterpreter(null);

	// when
	Command result = testClass.parseCommand("WHO IS THE RULER OF SOUTHEROS?");

	// then
	assertTrue(result instanceof RulerFinderCommand);
    }

    @Test
    public void testParseCommand_FIND_RULER_many_spaces() {
	// given
	CommandInterpreter testClass = new CommandInterpreter(null);

	// when
	Command result = testClass.parseCommand("Who     is    the    ruler    of    Southeros   ?");

	// then
	assertTrue(result instanceof RulerFinderCommand);
    }

    @Test
    public void testParseCommand_FIND_ALLIES_OF_RULER() {
	// given
	CommandInterpreter testClass = new CommandInterpreter(null);

	// when
	Command result = testClass.parseCommand("Allies of Ruler?");

	// then
	assertTrue(result instanceof AlliesOfRulerFinderCommand);
    }

    @Test
    public void testParseCommand_FIND_ALLIES_OF_KING() {
	// given
	CommandInterpreter testClass = new CommandInterpreter(null);

	// when
	Command result = testClass.parseCommand("Allies of King Shan?");

	// then
	assertTrue(result instanceof AlliesOfKingFinderCommand);
    }

    @Test
    public void testParseCommand_START_SENDING_MESSAGES_REGEX() {

	// given
	KingdomService kingdomService = mock(KingdomService.class);
	when(kingdomService.getKingdomData("shan")).thenReturn(new KingdomData(Kingdom.AIR, "aaa", "Shan"));

	CommandInterpreter testClass = new CommandInterpreter(kingdomService);

	// when
	Command result = testClass.parseCommand("MessaGes   to 3  Kingdoms from King Shan");

	// then
	assertTrue(result instanceof MessagingCommand);

	MessagingCommand messagingCommand = (MessagingCommand) result;

	assertEquals(3, messagingCommand.getRepeatTime());
	assertEquals(Kingdom.AIR, messagingCommand.getFrom());
    }

}
