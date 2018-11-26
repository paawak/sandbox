package com.swayam.geektrust.goldencrown.model;

import java.util.Optional;

public class KingdomImpl implements Kingdom {

    private final String animalEmblem;
    private final Optional<String> king;

    public KingdomImpl(String animalEmblem) {
        this(animalEmblem, null);
    }

    public KingdomImpl(String animalEmblem, String king) {
        this.animalEmblem = animalEmblem;
        this.king = Optional.ofNullable(king);
    }

    @Override
    public String getAnimalEmblem() {
        return animalEmblem;
    }

    @Override
    public Optional<String> getKing() {
        return king;
    }

}
