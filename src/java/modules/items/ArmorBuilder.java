package src.java.modules.items;

import src.java.utils.ResourceParser;

import java.util.ArrayList;

/**
 * Load initial data from setting files for armors.
 */
public class ArmorBuilder {
    private final static String ARMOR_SETTING = "./data/items/Armory.txt";

    private static final String NAME_KEY = "Name";
    private static final String LEVEL_KEY = "required level";
    private static final String DEFENSE_KEY = "damage reduction";
    private static final String COST_KEY = "cost";

    private static ArrayList<Inventory> armorList;

    public ArmorBuilder() {
        armorList = new ArrayList<>();
        loadArmor();
    }

    public static ArrayList<Inventory> getArmorList() {
        return armorList;
    }

    private void loadArmor() {
        ResourceParser parser = new ResourceParser(ARMOR_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            armorList.add(new Armor(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, LEVEL_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DEFENSE_KEY)),
                    Integer.parseInt(parser.getRowValue(i, COST_KEY))
            ));
        }
    }
}
