package com.swayam.geektrust.goldencrown.service;

import java.util.Map;

import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.model.SoutherosKingdom;

public class Kingdoms {

    private final Map<SoutherosKingdom, KingdomData> availableKingdoms;

    public Kingdoms(Map<SoutherosKingdom, KingdomData> availableKingdoms) {
        this.availableKingdoms = availableKingdoms;
    }

}
