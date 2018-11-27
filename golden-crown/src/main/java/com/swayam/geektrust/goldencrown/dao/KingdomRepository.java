package com.swayam.geektrust.goldencrown.dao;

import java.util.Map;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public interface KingdomRepository {

    Map<Kingdom, KingdomData> getAvailableKingdoms();

    void markSuccessfulMessage(Kingdom from, Kingdom to);

}