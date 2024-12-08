package de.akitoro.kniffel.combinations;

import java.util.Map;
import java.util.Objects;

/**
 * Klassische Kniffel-Kombination.
 * Zählt die Summe aller Würfel mit gleichen Augen.
 */
public final class ClassicCombination implements Combination {

    /**
     * Würfelaugen.
     */
    private final int eyeSpots;

    /**
     * Konstruiert eine klassische Kniffel Kombination.
     * @param eyeSpots Würfelaugen
     */
    public ClassicCombination(int eyeSpots) {
        this.eyeSpots = eyeSpots;
    }

    @Override
    public int points(Map<Integer, Integer> spotCount) {
        return spotCount.getOrDefault(eyeSpots, 0) * eyeSpots;
    }

    @Override
    public String name() {
        return String.format("Anzahl %s-er", eyeSpots);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ClassicCombination classic) {
            return this.eyeSpots == classic.eyeSpots;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eyeSpots);
    }
}
