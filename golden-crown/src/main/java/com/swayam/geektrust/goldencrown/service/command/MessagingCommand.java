package com.swayam.geektrust.goldencrown.service.command;

import java.util.regex.Pattern;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class MessagingCommand implements Command {

    static final String START_SENDING_MESSAGES_REGEX = "^messages\\s+to\\s+\\d\\s+kingdoms\\s+from\\s+king\\s+shan\\s*$";

    private static final String INPUT_MESSAGE_REGEX = "^\\w+\\,\\s+\".+\"$";

    private final KingdomService kingdomService;
    private final Kingdom from;
    private final int repeatTime;

    public MessagingCommand(KingdomService kingdomService, Kingdom from, int repeatTime) {
	this.kingdomService = kingdomService;
	this.from = from;
	this.repeatTime = repeatTime;
    }

    @Override
    public String execute(String rawCommand) {

	String rawCommandInLowerCase = rawCommand.toLowerCase();
	if (Pattern.matches(START_SENDING_MESSAGES_REGEX, rawCommandInLowerCase)) {
	    // ignore silently
	    return "";
	} else if (!Pattern.matches(INPUT_MESSAGE_REGEX, rawCommandInLowerCase)) {
	    throw new IllegalArgumentException("Invalid message format");
	}

	return "Message sent to " + "" + " successfully";
    }

    @Override
    public int getRepeatTime() {
	return repeatTime;
    }

    /* added for testing */
    Kingdom getFrom() {
	return from;
    }

}
