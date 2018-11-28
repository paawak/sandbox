package com.swayam.geektrust.goldencrown.service.command;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class MessagingCommand implements Command {

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
	return "";
    }

    @Override
    public int getRepeatTime() {
	return repeatTime;
    }

}
