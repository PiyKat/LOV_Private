package src.java.modules.character.vendor;

import src.java.modules.items.InventoryFactory;

/**
 * Factory to generate new vendor.
 */
public class VendorFactory {
    public static Vendor generateNewVendor() {
        int type_i = (int) (Math.random() * VendorTypes.values().length);
        switch (type_i) {
            case 0:
                return new Vendor(InventoryFactory.getWeaponry());
            case 1:
                return new Vendor(InventoryFactory.getArmory());
            case 2:
                return new Vendor(InventoryFactory.getPotionInventory());
            case 3:
                return new Vendor(InventoryFactory.getSpellInventory());
        }
        return new Vendor(InventoryFactory.getFullStock());
    }

    private enum VendorTypes {WEAPONRY, ARMORY, POTIONS, SPELLS}
}
