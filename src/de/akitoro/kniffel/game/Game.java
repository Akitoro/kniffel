package de.akitoro.kniffel.game;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Spiel.
 */
public class Game {

    /**
     * Spielerliste.
     */
    private final List<Player> players;

    /**
     * Aktuelle Runde.
     */
    private int currentRound;

    /**
     * Maximale Anzahl an Versuchen.
     */
    private final int maxRetries;

    /**
     * Konstruiert ein Spiel.
     *
     * @param players    Spielerliste
     * @param maxRetries Maximale Anzahl an Versuchen
     */
    public Game(List<Player> players, int maxRetries) {
        this.players = players;
        this.currentRound = 0;
        this.maxRetries = maxRetries;
    }

    /**
     * Beendet Zug des aktuellen Spielers.
     */
    public void nextPlayer() {
        if (!this.isFinished()) {
            do {
                this.currentRound++;
            }
            while (this.getCurrentPlayer().isFinished());
        }
    }

    /**
     * Gibt aktuellen Spieler.
     *
     * @return aktueller Spieler.
     */
    public Player getCurrentPlayer() {
        return this.players.get(this.currentRound % this.players.size());
    }

    /**
     * Gibt aktuelle Runde.
     *
     * @return aktuelle Runde
     */
    public int getCurrentRound() {
        return this.currentRound;
    }

    /**
     * Gibt Spielerliste.
     *
     * @return Spielerliste
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Überprüft ob das Spiel beendet ist.
     *
     * @return ob Spiel beendet ist
     */
    public boolean isFinished() {
        return this.players.stream().allMatch(Player::isFinished);
    }

    /**
     * Gibt maximale Anzahl an Versuchen.
     *
     * @return maximale Anzahl an Versuchen
     */
    public int getMaxRetries() {
        return maxRetries;
    }

    /**
     * Gibt die Spieler mit der höchsten Anzahl an Punkten zurück.
     */
    public Set<Player> getWinners() {
        OptionalInt highest = this.players.stream().mapToInt(Player::getScore).max();
        if (highest.isPresent()) {
            return this.players.stream().filter(p -> p.getScore() == highest.getAsInt()).collect(Collectors.toSet());
        }
        return new HashSet<>();
    };
}
