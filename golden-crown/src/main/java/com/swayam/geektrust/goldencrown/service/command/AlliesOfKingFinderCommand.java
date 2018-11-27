package com.swayam.geektrust.goldencrown.service.command;

import java.util.Optional;
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
	Set<KingdomData> allies = getKingdomService().getAlliesOfKingdom(getKingdom(getNameOfKing(rawCommand)));
	return formatAllies(allies);
    }

    /* visible for testing */
    String getNameOfKing(String rawCommand) {
	return rawCommand.toLowerCase().split("(king)|\\?")[1].trim();
    }

    /* visible for testing */
    Kingdom getKingdom(String nameOfKing) {
	Set<KingdomData> kingdoms = getKingdomService().getKingdomsInSoutheros();
	Optional<Kingdom> matchingKingdom = kingdoms.stream().filter(kingdomData -> kingdomData.getKing().isPresent()).filter(kingdomData -> kingdomData.getKing().get().equalsIgnoreCase(nameOfKing))
		.map(KingdomData::getKingdom).findFirst();

	if (matchingKingdom.isPresent()) {
	    return matchingKingdom.get();
	}

	throw new IllegalArgumentException("No matching Kingdom found for the King named " + nameOfKing);
    }

}
