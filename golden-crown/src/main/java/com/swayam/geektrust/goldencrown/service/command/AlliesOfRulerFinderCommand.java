package com.swayam.geektrust.goldencrown.service.command;

import java.util.Map;
import java.util.Set;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class AlliesOfRulerFinderCommand extends AbstractPatternMatchingCommand {

    private static final String FIND_ALLIES_OF_RULER_REGEX = "^allies\\s+of\\s+ruler\\s*\\?$";

    private final KingdomService kingdomService;

    public AlliesOfRulerFinderCommand(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
    }

    @Override
    public String execute(Map<String, Object> context, String rawCommand) {
        Set<KingdomData> allies = kingdomService.getAlliesOfRuler();
        return formatAllies(allies);
    }

    @Override
    String getRegexPattern() {
        return FIND_ALLIES_OF_RULER_REGEX;
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
