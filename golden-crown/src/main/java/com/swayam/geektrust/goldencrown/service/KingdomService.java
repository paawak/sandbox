package com.swayam.geektrust.goldencrown.service;

import java.util.Optional;
import java.util.Set;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public interface KingdomService {

    Optional<KingdomData> getRuler();

    Set<KingdomData> getAlliesOfRuler();

    Set<KingdomData> getAlliesOfKingdom(Kingdom kingdom);

}
