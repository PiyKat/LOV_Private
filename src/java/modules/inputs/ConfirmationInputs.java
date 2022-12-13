package src.java.modules.inputs;

/**
 * Enum of ConfirmationInputs with predefined types and value comparison.
 */
public enum ConfirmationInputs implements IInputs {
    YES("Y"), NO("N");
    private final String value;

    ConfirmationInputs(String value) {
        this.value = value.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
