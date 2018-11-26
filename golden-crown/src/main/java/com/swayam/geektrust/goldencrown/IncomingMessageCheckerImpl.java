package com.swayam.geektrust.goldencrown;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class IncomingMessageCheckerImpl implements IncomingMessageChecker {

    @Override
    public boolean messageAccepted(Kingdom kingdom, String rawMessage) {

        Map<Character, Integer> animalEmblemWordFrequency = getCharacterFrequency(kingdom.getAnimalEmblem());
        Map<Character, Integer> rawMessageWordFrequency = getCharacterFrequency(rawMessage);

        long noMatchCharacterCount =
                animalEmblemWordFrequency.entrySet().stream().filter((Entry<Character, Integer> entry) -> !rawMessageWordFrequency.containsKey(entry.getKey())
                        || (rawMessageWordFrequency.get(entry.getKey()) < entry.getValue())).count();

        return noMatchCharacterCount == 0;
    }

    Map<Character, Integer> getCharacterFrequency(String word) {

        Map<Character, Integer> characterFrequency = word.chars().mapToObj((int character) -> (char) character)
                .collect(Collectors.toMap(Character::toLowerCase, character -> 1, (oldValue, newValue) -> oldValue + newValue));

        return Collections.unmodifiableMap(characterFrequency);

    }

}
