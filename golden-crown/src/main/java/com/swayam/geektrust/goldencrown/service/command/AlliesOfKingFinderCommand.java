package com.swayam.geektrust.goldencrown.service.command;

import java.util.Set;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

public class AlliesOfKingFinderCommand extends AlliesOfRulerFinderCommand {

    public AlliesOfKingFinderCommand(KingdomService kingdomService) {
	super(kingdomService);
    }

    @Override
    public String execute(String rawCommand) {
	Set<KingdomData> allies = getKingdomService().getAlliesOfKingdom(getKingdomFromRawCommand(rawCommand));
	return formatAllies(allies);
    }

    /* visible for testing */
    Kingdom getKingdomFromRawCommand(String rawCommand) {
	return null;
    }

}
