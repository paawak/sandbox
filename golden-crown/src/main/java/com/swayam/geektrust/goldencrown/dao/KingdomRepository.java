package com.swayam.geektrust.goldencrown.dao;

import java.util.Map;
import java.util.Set;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public interface KingdomRepository {

    Map<Kingdom, KingdomData> getAvailableKingdoms();

    void saveSuccessfulMessage(Kingdom from, Kingdom to);

    Set<Kingdom> getSuccessfulMessages(Kingdom from);

    Map<Kingdom, Set<Kingdom>> getSuccessfulMessages();

    KingdomData getKingdomData(Kingdom kingdom);

}