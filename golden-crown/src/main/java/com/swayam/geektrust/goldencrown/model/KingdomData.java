package com.swayam.geektrust.goldencrown.model;

import java.util.Optional;

public class KingdomData {

    private final Kingdom kingdom;
    private final String animalEmblem;
    private final Optional<String> king;

    public KingdomData(Kingdom kingdom, String animalEmblem, String king) {
        this.kingdom = kingdom;
        this.animalEmblem = animalEmblem;
        this.king = Optional.ofNullable(king);
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public String getAnimalEmblem() {
        return animalEmblem;
    }

    public Optional<String> getKing() {
        return king;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((animalEmblem == null) ? 0 : animalEmblem.hashCode());
        result = prime * result + ((king == null) ? 0 : king.hashCode());
        result = prime * result + ((kingdom == null) ? 0 : kingdom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        KingdomData other = (KingdomData) obj;
        if (animalEmblem == null) {
            if (other.animalEmblem != null)
                return false;
        } else if (!animalEmblem.equals(other.animalEmblem))
            return false;
        if (king == null) {
            if (other.king != null)
                return false;
        } else if (!king.equals(other.king))
            return false;
        if (kingdom != other.kingdom)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("KingdomData [kingdom=");
        builder.append(kingdom);
        builder.append(", animalEmblem=");
        builder.append(animalEmblem);
        builder.append(", king=");
        builder.append(king);
        builder.append("]");
        return builder.toString();
    }

}
