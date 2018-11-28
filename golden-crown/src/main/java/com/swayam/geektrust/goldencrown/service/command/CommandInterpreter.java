package com.swayam.geektrust.goldencrown.service.command;

import java.util.regex.Pattern;

import com.swayam.geektrust.goldencrown.service.KingdomService;

public class CommandInterpreter {

    private static final String FIND_RULER_REGEX = "^who\\s+is\\s+the\\s+ruler\\s+of\\s+southeros\\s*\\?$";
    private static final String FIND_ALLIES_OF_RULER_REGEX = "^allies\\s+of\\s+ruler\\s*\\?$";
    private static final String FIND_ALLIES_OF_KING_REGEX = "^allies\\s+of\\s+king\\s+\\w+\\s*\\?$";

    private final KingdomService kingdomService;

    public CommandInterpreter(KingdomService kingdomService) {
	this.kingdomService = kingdomService;
    }

    public Command parseCommand(String rawCommand) {
	String rawCommandInLowerCase = rawCommand.toLowerCase();
	if (Pattern.matches(FIND_RULER_REGEX, rawCommandInLowerCase)) {
	    return new RulerFinderCommand(kingdomService);
	} else if (Pattern.matches(FIND_ALLIES_OF_RULER_REGEX, rawCommandInLowerCase)) {
	    return new AlliesOfRulerFinderCommand(kingdomService);
	} else if (Pattern.matches(FIND_ALLIES_OF_KING_REGEX, rawCommandInLowerCase)) {
	    return new AlliesOfKingFinderCommand(kingdomService);
	} else {
	    return new InvalidCommand();
	}

    }

}
