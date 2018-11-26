package com.swayam.geektrust.goldencrown.model;

public enum SoutherosKingdom implements Kingdom {

    SPACE("gorilla"), LAND("panda"), WATER("octopus"), ICE("mammoth"), AIR("owl"), FIRE("dragon");

    private final String animalEmblem;

    private SoutherosKingdom(String animalEmblem) {
        this.animalEmblem = animalEmblem;
    }

    @Override
    public String getAnimalEmblem() {
        return animalEmblem;
    }

}
