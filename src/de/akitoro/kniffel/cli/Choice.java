package de.akitoro.kniffel.cli;

import de.akitoro.kniffel.Main;
import de.akitoro.kniffel.cli.format.Format;
import de.akitoro.kniffel.cli.format.Level;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Utility-Klasse für Entscheidungen.
 */
public final class Choice {

    private Choice() {
    }

    /**
     * Wählt eine Option in Form eines {@link Integer} aus.
     *
     * @param options Liste an Optionen in Textform
     * @return Index der ausgewählten Option
     */
    public static int chooseInt(List<String> options) {
        boolean validOptionSelected = false;
        int selectedInt = -1;
        while (!validOptionSelected) {
            IntStream.range(0, options.size()).forEach(i -> System.out.printf("[%2d] %s%n", i, options.get(i)));

            String inputOption = Main.SCANNER.nextLine();

            try {
                selectedInt = Integer.parseInt(inputOption);
            } catch (NumberFormatException numberFormatException) {
                Format.display(Level.ERROR, String.format("\"%s\" is not a valid Number.%n", inputOption));
            }
            if (0 <= selectedInt && selectedInt < options.size()) {
                validOptionSelected = true;
            } else {
                Format.display(Level.ERROR, String.format("\"%s\" is not an available Choice.%n", inputOption));
            }
        }
        return selectedInt;
    }
}
