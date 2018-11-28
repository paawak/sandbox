package com.swayam.geektrust.goldencrown.service.command;

import java.util.Map;
import java.util.Optional;

import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

class RulerFinderCommand extends AbstractPatternMatchingCommand {

    private static final String FIND_RULER_REGEX = "^who\\s+is\\s+the\\s+ruler\\s+of\\s+southeros\\s*\\?$";

    private final KingdomService kingdomService;

    public RulerFinderCommand(KingdomService kingdomService) {
        this.kingdomService = kingdomService;
    }

    @Override
    public String execute(Map<String, Object> context, String rawCommand) {
        Optional<KingdomData> ruler = kingdomService.getRuler();
        if (ruler.isPresent()) {
            String kingDesc;
            if (ruler.get().getKing().isPresent()) {
                kingDesc = ruler.get().getKing().get();
            } else {
                kingDesc = "of " + ruler.get().getKingdom().name();
            }

            return "King " + kingDesc;
        }
        return "None";
    }

    @Override
    String getRegexPattern() {
        return FIND_RULER_REGEX;
    }

}
