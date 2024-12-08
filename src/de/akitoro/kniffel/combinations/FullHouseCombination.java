package de.akitoro.kniffel.combinations;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Full House Kombination.
 */
public final class FullHouseCombination implements Combination {

    /**
     * Punktzahl für einen Full House.
     */
    public static final int FULL_HOUSE_SCORE = 25;

    /**
     * Punktzahl für die niedrigere Anzahl an Full House Werten.
     */
    public static final int FULL_HOUSE_LOWER = 2;

    /**
     * Punktzahl für die höhere Anzahl an Full House Werten.
     */
    public static final int FULL_HOUSE_HIGHER = 3;

    @Override
    public int points(Map<Integer, Integer> spotCount) {
        Set<Integer> threeCountKeys = spotCount.keySet().stream()
                .filter(key -> spotCount.get(key) == FULL_HOUSE_HIGHER).collect(Collectors.toSet());
        Set<Integer> twoCountKeys = spotCount.keySet().stream()
                .filter(key -> spotCount.get(key) == FULL_HOUSE_LOWER).collect(Collectors.toSet());

        // Überprüfe, ob mindestens ein 3-er & 2-er Paar vorhanden ist
        if (!threeCountKeys.isEmpty() && !twoCountKeys.isEmpty()) {
            return FullHouseCombination.FULL_HOUSE_SCORE;
        }
        return 0;
    }
    @Override
    public String name() {
        return "Full House";
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
