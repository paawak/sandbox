package com.swayam.geektrust.goldencrown;

import java.util.Map;
import java.util.stream.Collectors;

public class IncomingMessageCheckerImpl implements IncomingMessageChecker {

    @Override
    public boolean messageAccepted(Kingdom kingdom, String rawMessage) {

        return false;
    }

    Map<Character, Integer> getCharacterFrequency(String word) {

        return word.chars().mapToObj((int character) -> (char) character)
                .collect(Collectors.toMap(Character::toLowerCase, character -> 1, (oldValue, newValue) -> oldValue + newValue));

    }

}
