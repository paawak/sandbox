package com.swayam.geektrust.goldencrown.service;

import java.util.Map;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.SoutherosKingdom;

public class Kingdoms {

    private final Map<SoutherosKingdom, Kingdom> availableKingdoms;

    public Kingdoms(Map<SoutherosKingdom, Kingdom> availableKingdoms) {
        this.availableKingdoms = availableKingdoms;
    }

}
