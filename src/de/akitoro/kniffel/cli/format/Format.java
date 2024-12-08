package de.akitoro.kniffel.cli.format;

public class Format {
    public static void display(Level level, String text) {
        System.out.print(level.color);
        System.out.printf("[%s] %s", level.name, text);
        System.out.print(Color.RESET);
    }
}
