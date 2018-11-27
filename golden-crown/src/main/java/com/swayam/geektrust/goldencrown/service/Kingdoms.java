package com.swayam.geektrust.goldencrown.service;

import java.util.Map;

import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.model.Kingdom;

public class Kingdoms {

    private final Map<Kingdom, KingdomData> availableKingdoms;

    public Kingdoms(Map<Kingdom, KingdomData> availableKingdoms) {
        this.availableKingdoms = availableKingdoms;
    }

}
