package com.swayam.geektrust.goldencrown.service;

import java.util.regex.Pattern;

import com.swayam.geektrust.goldencrown.model.Command;

public class CommandInterpreter {

    private static final String FIND_RULER_REGEX = "^who\\s+is\\s+the\\s+ruler\\s+of\\s+southeros\\s*\\?$";
    private static final String FIND_ALLIES_OF_RULER_REGEX = "^allies\\s+of\\w+\\?$";

    public Command parseCommand(String rawCommand) {
	String rawCommandInLowerCase = rawCommand.toLowerCase();
	if (Pattern.matches(FIND_RULER_REGEX, rawCommandInLowerCase)) {
	    return Command.FIND_RULER;
	} else if (Pattern.matches(FIND_ALLIES_OF_RULER_REGEX, rawCommandInLowerCase)) {
	    return Command.FIND_ALLIES_OF_RULER;
	} else {
	    return Command.UNKNOWN;
	}

    }

}
