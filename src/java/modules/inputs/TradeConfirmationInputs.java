package src.java.modules.inputs;

/**
 * Enum of TradeConfirmationInputs with predefined types and value comparison.
 */
public enum TradeConfirmationInputs implements IInputs {
    YES("Y"), NO("N");
    private final String value;

    TradeConfirmationInputs(String value) {
        this.value = value.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
