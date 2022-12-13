package src.java.modules.items;

/**
 * Enum of InventoryType with predefined types and sellable control.
 */
public enum InventoryType {
    WEAPON(true), ARMOR(true), SPELL(true), POTION(true);

    private final boolean isSellable;

    InventoryType(boolean isSellable) {
        this.isSellable = isSellable;
    }

    public boolean isSellable() {
        return isSellable;
    }
}
