package com.swayam.geektrust.goldencrown.service.command;

import java.util.Optional;

import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.service.KingdomService;

public class RulerFinderCommand implements Command {

    private final KingdomService kingdomService;

    public RulerFinderCommand(KingdomService kingdomService) {
	this.kingdomService = kingdomService;
    }

    @Override
    public String execute(String rawCommand) {
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

}
