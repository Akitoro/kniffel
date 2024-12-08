package de.akitoro.kniffel.cli.states;

import de.akitoro.kniffel.cli.Choice;
import de.akitoro.kniffel.cli.State;
import de.akitoro.kniffel.cli.StateMachine;
import de.akitoro.kniffel.cli.format.Format;
import de.akitoro.kniffel.cli.format.Level;
import de.akitoro.kniffel.combinations.Combination;
import de.akitoro.kniffel.dice.Dice;
import de.akitoro.kniffel.game.Game;
import de.akitoro.kniffel.game.Player;
import de.akitoro.kniffel.game.ScoreCard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class GameState extends State {

    private Game game;

    private Dice dice;

    private static final int DEFAULT_RETRIES = 3;
    private static final int DEFAULT_DICE_SIDES = 6;

    private static final int DEFAULT_DICE_COUNT = 5;

    public GameState(StateMachine parent) {
        super(parent);
    }

    @Override
    public void enter() {
        Format.display(Level.INFO, String.format("Spiel wird initialisiert.%n"));

        List<Player> playerList = new ArrayList<>();

        playerList.add(new Player(ScoreCard.getKniffelScoreCard(), "Alice"));
        playerList.add(new Player(ScoreCard.getKniffelScoreCard(), "Bob"));

        game = new Game(playerList, GameState.DEFAULT_RETRIES);
        dice = new Dice(GameState.DEFAULT_DICE_SIDES);
    }

    @Override
    public void run() {
        while (!game.isFinished()) {
            Player current = game.getCurrentPlayer();
            Format.display(Level.HINT, String.format("Runde: %s von %s mit %d Punkten\n", game.getCurrentRound(), current.getName(), current.getScoreCard().total()));

            // Zufällige Würfel Kombination erzeugen
            List<Integer> resultList = dice.use(DEFAULT_DICE_COUNT);
            Map<Integer, Integer> resultMap = Dice.toMap(resultList);

            List<Map.Entry<Combination, Integer>> open = current.getScoreCard().getScores().entrySet().stream()
                    .filter(e -> e.getValue() == null).toList();

            boolean isUserInputValid = false;
            int availableRolls = game.getMaxRetries();

            while (!isUserInputValid) {
                Format.display(Level.HINT, String.format("Optionen für %s \n", resultList));

                // Ausgeben der offenen Kombinationen
                Map<Integer, Integer> finalResultMap = resultMap;
                List<String> combinationOptions = new ArrayList<>(open.stream().map(combinationIntegerEntry -> {
                    Combination combination = combinationIntegerEntry.getKey();

                    String name = combination.name();
                    int points = combination.points(finalResultMap);

                    return String.format("%-12s - %3s", name, points);
                }).toList());
                if (availableRolls > 0) {
                    combinationOptions.add(String.format("%-12s", "Neu werfen"));
                }

                int optionNumber = Choice.chooseInt(combinationOptions);

                if (optionNumber == open.size() && availableRolls > 0) {
                    availableRolls--;

                    // Würfel weglegen
                    this.playerClear(resultList);

                    // Neu würfeln
                    List<Integer> newRolls = dice.use(DEFAULT_DICE_COUNT - resultList.size());
                    resultList.addAll(newRolls);
                    resultMap = Dice.toMap(resultList);

                } else if (0 <= optionNumber && optionNumber < open.size()) {
                    // Kombination auswählen
                    Combination selected = open.get(optionNumber).getKey();

                    current.getScoreCard().insert(selected, selected.points(resultMap));
                    isUserInputValid = true;
                }
            }
            game.nextPlayer();
        }
        // Spieler nach Punktzahl absteigend sortieren
        List<Player> sortedPlayers = game.getPlayers().stream()
                .sorted(Comparator.comparing(p -> -1 * p.getScore())).toList();
        // Ergebnisse ausgeben
        for (Player player : sortedPlayers) {
            Format.display(Level.HINT, String.format("%3d: %s \n", player.getScoreCard().total(), player.getName()));
        }
        // Gewinner ausgeben
        Format.display(Level.HINT, String.format("Gewinner: %s", game.getWinners().stream().map(Player::getName).toList()));
        super.machine.stop();
    }


    /**
     * Neu werfen für Spieler.
     *
     * @param rolls startliste
     */
    private void playerClear(List<Integer> rolls) {
        boolean finishedRolling = false;
        // Solange durchführen bis keine Würfel mehr vorhanden sind oder explizit gestoppt wurde
        while (!rolls.isEmpty() && !finishedRolling) {
            // Nochmals Würfel herausnehmen
            Format.display(Level.HINT, String.format("Folgende Würfel sind noch vorhanden: %s \n", rolls));
            List<String> throwOutOptions = new ArrayList<>(rolls.stream().map(Object::toString).toList());
            throwOutOptions.add("Aufhören");

            int throwOutDice = Choice.chooseInt(throwOutOptions);

            if (throwOutDice == throwOutOptions.size() - 1) {
                finishedRolling = true;
            } else if (rolls.size() > throwOutDice) {
                // Überprüfen ob Würfel überhaupt vorhanden ist
                rolls.remove(throwOutDice);
            }
        }
    }
}
