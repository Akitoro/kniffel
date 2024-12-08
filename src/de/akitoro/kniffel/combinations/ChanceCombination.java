package de.akitoro.kniffel.combinations;

import java.util.Map;

/**
 * Chance-Kombination eines Kniffel-Spiels..
 */
public final class ChanceCombination implements Combination {

    @Override
    public int points(Map<Integer, Integer> spotCount) {
        // Gibt die Summe aller Würfelaugen zurück
        return spotCount.keySet().stream().mapToInt(key -> key * spotCount.get(key)).sum();
    }

    @Override
    public String name() {
        return "Chance";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof ChanceCombination;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
