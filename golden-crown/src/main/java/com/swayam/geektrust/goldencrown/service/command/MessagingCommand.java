package com.swayam.geektrust.goldencrown.service.command;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class MessagingCommand implements Command {

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
	System.out.println("******** " + rawCommand);
	return "";
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
