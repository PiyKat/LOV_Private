package src.java.modules.inputs;

/**
 * Enum of ActionInputs with predefined types and value comparison.
 */
public enum BattleActionInputs implements IInputs {
    ATTACK("Z"), CAST("X"), EQUIP("C"), DRINK("V");

    private final String value;

    BattleActionInputs(String value) {
        this.value = value.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
