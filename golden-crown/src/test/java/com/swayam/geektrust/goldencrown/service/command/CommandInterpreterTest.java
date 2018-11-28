package com.swayam.geektrust.goldencrown.service.command;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.swayam.geektrust.goldencrown.service.command.AlliesOfKingFinderCommand;
import com.swayam.geektrust.goldencrown.service.command.AlliesOfRulerFinderCommand;
import com.swayam.geektrust.goldencrown.service.command.Command;
import com.swayam.geektrust.goldencrown.service.command.CommandInterpreter;
import com.swayam.geektrust.goldencrown.service.command.RulerFinderCommand;

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

}
