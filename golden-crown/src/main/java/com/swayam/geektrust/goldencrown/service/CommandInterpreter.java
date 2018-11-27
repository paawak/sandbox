package com.swayam.geektrust.goldencrown.service;

import java.util.regex.Pattern;

import com.swayam.geektrust.goldencrown.model.Action;

public class CommandInterpreter {

    private static final String FIND_RULER_REGEX = "^who\\s+is\\s+the\\s+ruler\\s+of\\s+southeros\\s*\\?$";
    private static final String FIND_ALLIES_OF_RULER_REGEX = "^allies\\s+of\\s+ruler\\s*\\?$";
    private static final String FIND_ALLIES_OF_KING_REGEX = "^allies\\s+of\\s+king\\s+\\w+\\s*\\?$";

    public Action parseCommand(String rawCommand) {
	String rawCommandInLowerCase = rawCommand.toLowerCase();
	if (Pattern.matches(FIND_RULER_REGEX, rawCommandInLowerCase)) {
	    return Action.FIND_RULER;
	} else if (Pattern.matches(FIND_ALLIES_OF_RULER_REGEX, rawCommandInLowerCase)) {
	    return Action.FIND_ALLIES_OF_RULER;
	} else if (Pattern.matches(FIND_ALLIES_OF_KING_REGEX, rawCommandInLowerCase)) {
	    return Action.FIND_ALLIES_OF_KING;
	} else {
	    return Action.UNKNOWN;
	}

    }

}
