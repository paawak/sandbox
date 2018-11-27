package com.swayam.geektrust.goldencrown.service.command;

import java.util.Set;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

public class FindAlliesOfRulerCommand implements Command {

    private final KingdomService kingdomService;

    public FindAlliesOfRulerCommand(KingdomService kingdomService) {
	this.kingdomService = kingdomService;
    }

    @Override
    public String execute(String rawCommand) {
	Set<KingdomData> allies = kingdomService.getAlliesOfRuler();
	return formatAllies(allies);
    }

    String formatAllies(Set<KingdomData> allies) {
	if (allies.isEmpty()) {
	    return "None";
	}
	return allies.stream().map(KingdomData::getKingdom).map(Kingdom::name).sorted().reduce((first, second) -> first + ", " + second).get();
    }

    KingdomService getKingdomService() {
	return kingdomService;
    }

}
