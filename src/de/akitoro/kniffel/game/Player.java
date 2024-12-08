package de.akitoro.kniffel.game;

/**
 * Spieler.
 */
public class Player {

    /**
     * Punktekarte.
     */
    private ScoreCard scoreCard;

    /**
     * Name.
     */
    private String name;

    /**
     * Konstruiert einen Spieler.
     *
     * @param scoreCard Punktekarte
     * @param name      Name
     */
    public Player(ScoreCard scoreCard, String name) {
        this.scoreCard = scoreCard;
        this.name = name;
    }

    /**
     * Überprüft, ob die {@link ScoreCard Punktekarte} gefüllt ist.
     *
     * @return ob die {@link ScoreCard Punktekarte} gefüllt ist.
     */
    public boolean isFinished() {
        return scoreCard.isFull();
    }

    /**
     * Gibt den {@link String Namen}.
     *
     * @return Spielername
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt die {@link ScoreCard Punktekarte}.
     *
     * @return Punktekarte
     */
    public ScoreCard getScoreCard() {
        return this.scoreCard;
    }

    /**
     * Gibt den Punktestand des Spielers.
     * @return Punktestand
     */
    public int getScore() {
        return this.scoreCard.total();
    }
}
