package src.java.modules.inputs;

/**
 * Enum of EquipInputs with predefined types and value comparison.
 */
public enum EquipInputs implements IInputs {
    ADD("A"), REMOVE("R"), ARMOR("M"), WEAPON("N"), EXIT("E");

    private final String value;

    EquipInputs(String value) {
        this.value = value.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
    }
}
