package de.akitoro.kniffel.dice;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Würfel.
 */
public class Dice {

    /**
     * Zufall.
     */
    private final Random random;

    /**
     * Anzahl der Würfel Seiten.
     */
    private final int sides;

    /**
     * Konstruiert einen Würfel.
     *
     * @param sides Seitenanzahl
     */
    public Dice(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    /**
     * Konstruiert einen Würfel.
     *
     * @param sides Seitenanzahl
     * @param seed  Seed
     */
    public Dice(int sides, long seed) {
        this.sides = sides;
        this.random = new Random(seed);
    }

    /**
     * Würfel n-Mal werfen.
     *
     * @param times Anzahl des Würfel werfens
     * @return Liste an Ergebnissen
     */
    public List<Integer> use(int times) {
        return this.random.ints(times, 1, sides + 1).boxed().collect(Collectors.toList());
    }

    /**
     * Konvertiert eine Liste mit Zufallszahlen in ein Mapping
     * von Zahlen und deren Vorkommen.
     *
     * @param list Liste mit Zufallszahlen
     * @return Mapping von Zahlen und deren Vorkommen.
     */
    public static Map<Integer, Integer> toMap(List<Integer> list) {
        return list.stream().collect(Collectors.toMap(s -> s, s -> 1, Integer::sum));
    }

}
