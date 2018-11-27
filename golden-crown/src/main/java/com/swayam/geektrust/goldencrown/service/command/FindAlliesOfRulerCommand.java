package com.swayam.geektrust.goldencrown.service.command;

import com.swayam.geektrust.goldencrown.service.KingdomService;

public class FindAlliesOfRulerCommand implements Command {

    private final KingdomService kingdomService;

    public FindAlliesOfRulerCommand(KingdomService kingdomService) {
	this.kingdomService = kingdomService;
    }

    @Override
    public String execute(String rawCommand) {
	// TODO Auto-generated method stub
	return null;
    }

}
