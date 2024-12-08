package de.akitoro.kniffel.combinations;

import java.util.Map;

/**
 * Definiert eine Kniffel-Kombination.
 */
public interface Combination {

    /**
     * Berechnet verfügbare Punkte.
     *
     * @param spotCount Mapping von Würfelaugen zu deren Anzahl
     * @return verfügbare Punkte
     */
    int points(Map<Integer, Integer> spotCount);

    /**
     * Name der Kombination.
     *
     * @return name
     */
    String name();
}
