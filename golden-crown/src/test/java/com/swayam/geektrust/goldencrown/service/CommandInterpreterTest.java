package com.swayam.geektrust.goldencrown.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.model.Command;

public class CommandInterpreterTest {

    @Test
    public void testParseCommand_FIND_RULER_mixed_case() {
	// given
	CommandInterpreter testClass = new CommandInterpreter();

	// when
	Command result = testClass.parseCommand("Who is the ruler of Southeros?");

	// then
	assertEquals(Command.FIND_RULER, result);
    }

    @Test
    public void testParseCommand_FIND_RULER_lower_case() {
	// given
	CommandInterpreter testClass = new CommandInterpreter();

	// when
	Command result = testClass.parseCommand("who is the ruler of southeros?");

	// then
	assertEquals(Command.FIND_RULER, result);
    }

    @Test
    public void testParseCommand_FIND_RULER_upper_case() {
	// given
	CommandInterpreter testClass = new CommandInterpreter();

	// when
	Command result = testClass.parseCommand("WHO IS THE RULER OF SOUTHEROS?");

	// then
	assertEquals(Command.FIND_RULER, result);
    }

    @Test
    public void testParseCommand_FIND_RULER_many_spaces() {
	// given
	CommandInterpreter testClass = new CommandInterpreter();

	// when
	Command result = testClass.parseCommand("Who     is    the    ruler    of    Southeros   ?");

	// then
	assertEquals(Command.FIND_RULER, result);
    }

    @Test
    public void testParseCommand_FIND_ALLIES_OF_RULER() {
	// given
	CommandInterpreter testClass = new CommandInterpreter();

	// when
	Command result = testClass.parseCommand("Allies of Ruler?");

	// then
	assertEquals(Command.FIND_ALLIES_OF_RULER, result);
    }

    @Test
    public void testParseCommand_FIND_ALLIES_OF_KING() {
	// given
	CommandInterpreter testClass = new CommandInterpreter();

	// when
	Command result = testClass.parseCommand("Allies of King Shan?");

	// then
	assertEquals(Command.FIND_ALLIES_OF_KING, result);
    }

}
