package com.swayam.geektrust.goldencrown.dao;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public class KingdomRepositoryImpl implements KingdomRepository {

    private final String dataFileClasspath;

    private final Map<Kingdom, Set<Kingdom>> successfulMessages = new HashMap<>();

    private Map<Kingdom, KingdomData> availableKingdoms;

    public KingdomRepositoryImpl(String dataFileClasspath) {
	this.dataFileClasspath = dataFileClasspath;
    }

    @Override
    public Map<Kingdom, KingdomData> getAvailableKingdoms() {

	if (availableKingdoms == null) {
	    availableKingdoms = new KingdomDataFileReader().readFileForAvailableKingdoms(new InputStreamReader(KingdomRepositoryImpl.class.getResourceAsStream(dataFileClasspath)));
	}

	return availableKingdoms;
    }

    @Override
    public void saveSuccessfulMessage(Kingdom from, Kingdom to) {
	if (successfulMessages.containsKey(from)) {
	    Set<Kingdom> existingMessages = successfulMessages.get(from);
	    existingMessages.add(to);
	} else {
	    successfulMessages.put(from, new HashSet<>(Arrays.asList(to)));
	}
    }

    @Override
    public Set<Kingdom> getSuccessfulMessages(Kingdom from) {
	return successfulMessages.get(from);
    }

}
