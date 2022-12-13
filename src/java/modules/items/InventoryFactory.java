package src.java.modules.items;

/**
 * Factory to control items creation.
 */
public class InventoryFactory {
    // Load files in the constructor.
    private static final WeaponBuilder weaponBuilder = new WeaponBuilder();
    private static final ArmorBuilder armorBuilder = new ArmorBuilder();
    private static final SpellBuilder spellBuilder = new SpellBuilder();
    private static final PotionBuilder potionBuilder = new PotionBuilder();

    public static InventoryList getFullStock() {
        InventoryList inventoryList = new InventoryList();
        inventoryList.set(InventoryType.WEAPON, WeaponBuilder.getWeaponList());
        inventoryList.set(InventoryType.ARMOR, ArmorBuilder.getArmorList());
        inventoryList.set(InventoryType.SPELL, SpellBuilder.getSpellList());
        inventoryList.set(InventoryType.POTION, PotionBuilder.getPotionList());
        return inventoryList;
    }

    public static InventoryList getWeaponry() {
        InventoryList inventoryList = new InventoryList();
        inventoryList.set(InventoryType.WEAPON, WeaponBuilder.getWeaponList());
        return inventoryList;
    }

    public static InventoryList getArmory() {
        InventoryList inventoryList = new InventoryList();
        inventoryList.set(InventoryType.ARMOR, ArmorBuilder.getArmorList());
        return inventoryList;
    }

    public static InventoryList getSpellInventory() {
        InventoryList inventoryList = new InventoryList();
        inventoryList.set(InventoryType.SPELL, SpellBuilder.getSpellList());
        return inventoryList;
    }

    public static InventoryList getPotionInventory() {
        InventoryList inventoryList = new InventoryList();
        inventoryList.set(InventoryType.POTION, PotionBuilder.getPotionList());
        return inventoryList;
    }
}
