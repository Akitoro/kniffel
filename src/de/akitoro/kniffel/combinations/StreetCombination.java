package de.akitoro.kniffel.combinations;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Straßen-Kombination.
 */
public final class StreetCombination implements Combination {

    /**
     * Länge der Straße.
     */
    private final int length;

    /**
     * Straßen Multiplikator.
     */
    private static final int STREET_MULTIPLIER = 10;

    /**
     * Konstruiert eine Straßen-Kombination.
     *
     * @param length Länge der Straße
     */
    public StreetCombination(int length) {
        this.length = length;
    }

    @Override
    public int points(Map<Integer, Integer> spotCount) {
        // Alle verfügbaren Werte ausfindig machen
        Set<Integer> nonZeroKeys = spotCount.keySet().stream()
                .filter(count -> spotCount.get(count) > 0)
                .collect(Collectors.toSet());

        // Nach aufeinanderfolgenden Werten prüfen
        boolean isFound = nonZeroKeys.stream()
                .anyMatch(start -> IntStream.range(0, this.length)
                        .map(follow -> start + follow)
                        .allMatch(nonZeroKeys::contains));

        if (isFound) {
            return (length - 1) * STREET_MULTIPLIER;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StreetCombination other) {
            return this.length == other.length;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }

    @Override
    public String name() {
        return String.format("%s-er Straße", length);
    }

}
