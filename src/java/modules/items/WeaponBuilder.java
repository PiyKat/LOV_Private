package src.java.modules.items;

import src.java.utils.ResourceParser;

import java.util.ArrayList;

/**
 * Load initial data from setting files for weapons.
 */
public class WeaponBuilder {
    private final static String WEAPON_SETTING = "./data/items/Weaponry.txt";

    private static final String NAME_KEY = "Name";
    private static final String LEVEL_KEY = "level";
    private static final String DAMAGE_KEY = "damage";
    private static final String COST_KEY = "cost";
    private static final String OCCUPANCY_KEY = "required hands";

    private static final ArrayList<Inventory> weaponList = new ArrayList<>();

    public WeaponBuilder() {
        loadWeapon();
    }

    public static ArrayList<Inventory> getWeaponList() {
        return weaponList;
    }

    private void loadWeapon() {
        ResourceParser parser = new ResourceParser(WEAPON_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            weaponList.add(new Weapon(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, LEVEL_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DAMAGE_KEY)),
                    Integer.parseInt(parser.getRowValue(i, COST_KEY)),
                    Integer.parseInt(parser.getRowValue(i, OCCUPANCY_KEY))
            ));
        }
    }
}
