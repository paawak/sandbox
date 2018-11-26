package com.swayam.geektrust.goldencrown;

import java.util.Map;
import java.util.stream.Collectors;

public class IncomingMessageCheckerImpl implements IncomingMessageChecker {

    @Override
    public boolean messageAccepted(Kingdom kingdom, String rawMessage) {

        return false;
    }

    Map<Character, Integer> getCharacterFrequency(String word) {

        return word.chars().mapToObj((int n) -> (char) n).collect(Collectors.toMap(Character::toLowerCase, (Character c) -> 1, (Integer val1, Integer val2) -> {
            return val1 + val2;
        }));

    }

}
