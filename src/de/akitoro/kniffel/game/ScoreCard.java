package de.akitoro.kniffel.game;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import de.akitoro.kniffel.combinations.ChanceCombination;
import de.akitoro.kniffel.combinations.Combination;
import de.akitoro.kniffel.combinations.FullHouseCombination;
import de.akitoro.kniffel.combinations.CountCombination;
import de.akitoro.kniffel.combinations.StreetCombination;
import de.akitoro.kniffel.combinations.ClassicCombination;

/**
 * Punktekarte.
 */
public final class ScoreCard {

    /**
     * Klassischer Kniffel Bonus.
     */
    public static final int CLASSIC_BONUS = 35;
    /**
     * Threshold an klassischen Kombinationen um Bonus zu erreichen.
     */
    public static final int CLASSIC_THRESHOLD = 63;
    /**
     * Mapping von Kombination mit erhaltenen Punkten,
     * falls Kombination nicht angekreuzt dann {@code null}.
     */
    private final Map<Combination, Integer> scores;

    /**
     * Erzeugt eine Kniffel-Punktekarte.
     * @return Kniffel-Punktekarte
     */
    public static ScoreCard getKniffelScoreCard() {

        Map<Combination, Integer> kniffelScores = new LinkedHashMap<>();
        // Augenzahlen 1-6 hinzufügen
        for (int i = 1; i <= 6; i++) {
            kniffelScores.put(new ClassicCombination(i), null);
        }
        // 3er, 4er-Pasch und Kniffel hinzufügen
        for (int i = 3; i <= 5; i++) {
            kniffelScores.put(new CountCombination(i), null);
        }
        // Full House
        kniffelScores.put(new FullHouseCombination(), null);
        // Chance
        kniffelScores.put(new ChanceCombination(), null);
        // große und kleine Straße
        for (int i = 4; i <= 5; i++) {
            kniffelScores.put(new StreetCombination(i), null);
        }

        return new ScoreCard(kniffelScores);
    }

    public static ScoreCard getTestScorecard() {
        Map<Combination, Integer> kniffelScores = new LinkedHashMap<>();
        kniffelScores.put(new ChanceCombination(), null);

        return new ScoreCard(kniffelScores);
    }
    /**
     * Erzeugt eine {@link ScoreCard Punktekart} mit angegebenen
     * aufzufüllenden Kombinationen.
     * @param combinations alle Kombinationen.
     */
    public ScoreCard(List<Combination> combinations) {
        this.scores = new HashMap<>();

        for (Combination combination : combinations) {
            scores.put(combination, null);
        }
    }
    /**
     * Konstruiert eine Punktekarte mittels vorgegebenen Kombinationen.
     * @param presets vorgegebene Kombinationen
     */
    public ScoreCard(Map<Combination, Integer> presets) {
        this.scores = presets;
    }

    /**
     * Checkt, ob die Karte voll ist.
     * @return ob die Karte voll ist
     */
    public boolean isFull() {
        return scores.values().stream().allMatch(Objects::nonNull);
    }

    /**
     * Fügt die Combination mit entsprechenden Punkten hinzu.
     *
     * @param combination Kombination
     * @param points Punkte
     */
    public void insert(Combination combination, int points) {
        this.scores.putIfAbsent(combination, points);
    }

    /**
     * Berechnet die gesamte Punktzahl der Karte.
     * Falls Threshold erreicht wird, kommen Bonuspunkte dazu.
     * @return Punktzahl
     */
    public int total() {
        int totalPoints = 0;
        int bonusThreshold = 0;

        for (Entry<Combination, Integer> entry : this.scores.entrySet()) {
            Combination combination = entry.getKey();
            int score = entry.getValue() == null ? 0 : entry.getValue();

            if (combination instanceof ClassicCombination) {
                bonusThreshold += score;
            }
            totalPoints += score;
        }

        if (bonusThreshold >= ScoreCard.CLASSIC_THRESHOLD) {
            totalPoints += ScoreCard.CLASSIC_BONUS;
        }
        return totalPoints;
    }

    /**
     * Gibt Mapping von Kombinationen mit erhaltenen Punkten.
     * @return Mapping
     */
    public Map<Combination, Integer> getScores() {
        return scores;
    }
}
