package de.akitoro.kniffel.combinations;

import java.util.Map;
import java.util.Objects;

/**
 * Pasch-Kombination.
 */
public final class CountCombination implements Combination {

    /**
     * Gibt die Anzahl benötigter gleicher Würfel des Paschs an.
     */
    private final int spotCount;

    /**
     * Konstruiert eine Pasch-Kombination.
     *
     * @param spotCount Anzahl benötigter gleicher Würfel
     */
    public CountCombination(int spotCount) {
        this.spotCount = spotCount;
    }

    @Override
    public int points(Map<Integer, Integer> spotCount) {
        return spotCount.entrySet().stream()
                .filter(e -> e.getValue() == this.spotCount)
                .map(e -> e.getKey() * e.getValue())
                .max(Integer::compare)
                .orElse(0);
    }

    @Override
    public String name() {
        return String.format("%s-er Pasch", spotCount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CountCombination other) {
            return this.spotCount == other.spotCount;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotCount);
    }
}
