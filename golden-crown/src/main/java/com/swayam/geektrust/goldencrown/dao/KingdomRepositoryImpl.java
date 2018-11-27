package com.swayam.geektrust.goldencrown.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.swayam.geektrust.goldencrown.model.Kingdom;
import com.swayam.geektrust.goldencrown.model.KingdomData;

public class KingdomRepositoryImpl implements KingdomRepository {

    private final Reader dataReader;

    public KingdomRepositoryImpl(Reader dataReader) {
        this.dataReader = dataReader;
    }

    @Override
    public Map<Kingdom, KingdomData> getAvailableKingdoms() {

        try (BufferedReader br = new BufferedReader(dataReader)) {

            Map<Kingdom, KingdomData> availableKingdoms = br.lines().filter(line -> line.trim().length() > 0).map(line -> parseDataLine(line))
                    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

            return Collections.unmodifiableMap(availableKingdoms);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /* Visible for testing */
    Entry<Kingdom, KingdomData> parseDataLine(String line) {
        String[] keyValuePair = line.split("=");
        if (keyValuePair.length != 2) {
            throw new IllegalArgumentException("Wrong input format: expecting the line to be of the format: <Kingdom Name>=<Kingdom Data>");
        }
        Kingdom kingdom = Kingdom.valueOf(keyValuePair[0].trim().toUpperCase());
        String kingdomData = keyValuePair[1].trim();
        String[] kingdomDataParts = kingdomData.split(",");

        String animalEmblem = kingdomDataParts[0].trim();

        if (animalEmblem.length() == 0) {
            throw new IllegalArgumentException("The <Animal Emblem> cannot be empty");
        }

        String kingName = null;

        if (kingdomDataParts.length == 2) {
            kingName = kingdomDataParts[1].trim();
        } else if (kingdomDataParts.length >= 2) {
            throw new UnsupportedOperationException("This <Kingdom Data> format is not yet supported");
        }

        return new SimpleEntry<>(kingdom, new KingdomData(kingdom, animalEmblem, kingName));
    }

}
