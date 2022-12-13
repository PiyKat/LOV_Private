package src.java.modules.inputs;

/**
 * Enum of DirectionInputs with predefined types and value comparison.
 */
public enum DirectionInputs implements IInputs {
    UP("W"), DOWN("S"), LEFT("A"), RIGHT("D"),
    MARKET("M"), RECALL("R"), TELEPORT("T");
    private final String value;

    DirectionInputs(String value) {
        this.value = value.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
