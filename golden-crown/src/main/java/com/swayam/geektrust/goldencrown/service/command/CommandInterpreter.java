package com.swayam.geektrust.goldencrown.service.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.service.KingdomService;

public class CommandInterpreter {

    private static final String FIND_RULER_REGEX = "^who\\s+is\\s+the\\s+ruler\\s+of\\s+southeros\\s*\\?$";
    private static final String FIND_ALLIES_OF_RULER_REGEX = "^allies\\s+of\\s+ruler\\s*\\?$";
    private static final String FIND_ALLIES_OF_KING_REGEX = "^allies\\s+of\\s+king\\s+\\w+\\s*\\?$";

    private static final Pattern SINGLE_DIGIT = Pattern.compile("\\d");

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
	} else if (Pattern.matches(MessagingCommand.START_SENDING_MESSAGES_REGEX, rawCommandInLowerCase)) {
	    Matcher matcher = SINGLE_DIGIT.matcher(rawCommandInLowerCase);
	    if (matcher.find()) {
		int repeatTime = Integer.parseInt(matcher.group());
		String[] tokens = rawCommandInLowerCase.trim().split("\\s");
		String kingName = tokens[tokens.length - 1].trim();
		Kingdom from = kingdomService.getKingdomData(kingName).getKingdom();
		return new MessagingCommand(kingdomService, from, repeatTime);
	    } else {
		// never going to happen
		throw new IllegalArgumentException("Invalid input: Not able to find the no. of messages to be sent");
	    }
	} else {
	    return new InvalidCommand();
	}

    }

}
