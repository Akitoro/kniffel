package de.akitoro.kniffel.cli.format;

public enum Level {
    INFO(Color.NONE, "INFO"),
    HINT(Color.GREEN, "HINT"),
    ERROR(Color.RED, "ERROR"),

    COMMON(Color.WHITE, "COMMON");

    /**
     * Farbe des Ausgabelevels.
     */
    public final Color color;

    /**
     * Name des Ausgabelevels.
     */
    public final String name;

    Level(Color color, String name) {
        this.color = color;
        this.name = name;
    }
}
