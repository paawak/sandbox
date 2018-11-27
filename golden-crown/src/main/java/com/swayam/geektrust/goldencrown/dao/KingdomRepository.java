package com.swayam.geektrust.goldencrown.dao;

import java.util.Map;

import com.swayam.geektrust.goldencrown.model.KingdomData;
import com.swayam.geektrust.goldencrown.model.SoutherosKingdom;

public interface KingdomRepository {

    Map<SoutherosKingdom, KingdomData> getAvailableKingdoms();

}