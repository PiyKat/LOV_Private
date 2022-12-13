package src.java.views;

/**
 * Define types of space and strings to display.
 */
public enum SpaceViewType {
    HERO("\uD83E\uDD20️"),
    MONSTER("\uD83D\uDC7E"),
    INACCESSIBLE("\uD83C\uDF32"),
    NEXUS("\uD83C\uDFE0"),
    EMPTY("\uD83D\uDEAB"), // Ensure no null space in the map
    PLAIN("  "),
    BUSH("\uD83C\uDF33"),
    CAVE("⛰️"),
    KOULOU("✨");

    private final String icon;

    SpaceViewType(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return icon;
    }
}
