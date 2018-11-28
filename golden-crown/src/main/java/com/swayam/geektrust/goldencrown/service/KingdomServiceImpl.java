package com.swayam.geektrust.goldencrown.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.swayam.geektrust.goldencrown.dao.KingdomRepository;
import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public class KingdomServiceImpl implements KingdomService {

    private final KingdomRepository kingdomRepository;
    private final IncomingMessageChecker incomingMessageChecker;

    public KingdomServiceImpl(KingdomRepository kingdomRepository, IncomingMessageChecker incomingMessageChecker) {
	this.kingdomRepository = kingdomRepository;
	this.incomingMessageChecker = incomingMessageChecker;
    }

    @Override
    public Optional<KingdomData> getRuler() {
	Map<Kingdom, Set<Kingdom>> successfulMessages = kingdomRepository.getSuccessfulMessages();
	List<Kingdom> rulers = successfulMessages.entrySet().stream().filter(entry -> entry.getValue().size() >= 3).map(entry -> entry.getKey()).collect(Collectors.toList());

	if (rulers.isEmpty()) {
	    return Optional.empty();
	} else if (rulers.size() == 1) {
	    return Optional.of(kingdomRepository.getKingdomData(rulers.get(0)));
	}

	throw new IllegalArgumentException("More than 1 Rulers found!");
    }

    @Override
    public Set<KingdomData> getAlliesOfRuler() {
	Optional<KingdomData> currentRuler = getRuler();
	if (currentRuler.isPresent()) {
	    return toKingdomData(kingdomRepository.getSuccessfulMessages(currentRuler.get().getKingdom()));
	}
	return Collections.emptySet();
    }

    @Override
    public Set<KingdomData> getAlliesOfKingdom(Kingdom kingdom) {
	return toKingdomData(kingdomRepository.getSuccessfulMessages(kingdom));
    }

    @Override
    public Set<KingdomData> getKingdomsInSoutheros() {
	return Collections.unmodifiableSet(new HashSet<>(kingdomRepository.getAvailableKingdoms().values()));
    }

    @Override
    public KingdomData getKingdomData(String kingName) {
	return kingdomRepository.getAvailableKingdoms().values().stream().filter(kingdomData -> kingdomData.getKing().isPresent())
		.filter(kingdomData -> kingdomData.getKing().get().equalsIgnoreCase(kingName)).findFirst().orElseThrow(() -> {
		    return new IllegalArgumentException("Invalid King name: " + kingName);
		});
    }

    @Override
    public boolean sendMessage(Kingdom from, Kingdom to, String message) {
	KingdomData toData = kingdomRepository.getKingdomData(to);
	if (!incomingMessageChecker.isMessageAccepted(toData, message)) {
	    return false;
	}

	kingdomRepository.saveSuccessfulMessage(from, to);
	return true;
    }

    private Set<KingdomData> toKingdomData(Set<Kingdom> kingdoms) {
	return Collections.unmodifiableSet(kingdoms.stream().map(kingdomRepository::getKingdomData).collect(Collectors.toSet()));
    }

}
