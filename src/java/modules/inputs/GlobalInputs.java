package src.java.modules.inputs;

/**
 * Enum of GlobalInputs with predefined types and value comparison.
 */
public enum GlobalInputs implements IInputs {
    QUIT("Q"), INFORMATION("I"), HELP("H"), MAP("P");
    private final String value;

    GlobalInputs(String value) {
        this.value = value.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
