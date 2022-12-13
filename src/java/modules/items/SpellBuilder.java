package src.java.modules.items;

import src.java.modules.items.spell.FireSpell;
import src.java.modules.items.spell.IceSpell;
import src.java.modules.items.spell.LightningSpell;
import src.java.utils.ResourceParser;

import java.util.ArrayList;

/**
 * Load initial data from setting files for spells.
 */
public class SpellBuilder {
    private final static String FIRE_SETTING = "./data/items/spells/FireSpells.txt";
    private final static String ICE_SETTING = "./data/items/spells/IceSpells.txt";
    private final static String LIGHTNING_SETTING = "./data/items/spells/LightningSpells.txt";

    private static final String NAME_KEY = "Name";
    private static final String LEVEL_KEY = "required level";
    private static final String DMG_KEY = "damage";
    private static final String MANA_KEY = "mana cost";
    private static final String COST_KEY = "cost";

    private static ArrayList<Inventory> spellList;

    public SpellBuilder() {
        spellList = new ArrayList<>();
        loadFireSpells();
        loadIceSpells();
        loadLightningSpells();
    }

    public static ArrayList<Inventory> getSpellList() {
        return spellList;
    }

    private void loadFireSpells() {
        ResourceParser parser = new ResourceParser(FIRE_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            spellList.add(new FireSpell(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, LEVEL_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DMG_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MANA_KEY)),
                    Integer.parseInt(parser.getRowValue(i, COST_KEY))
            ));
        }
    }

    private void loadIceSpells() {
        ResourceParser parser = new ResourceParser(ICE_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            spellList.add(new IceSpell(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, LEVEL_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DMG_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MANA_KEY)),
                    Integer.parseInt(parser.getRowValue(i, COST_KEY))
            ));
        }
    }

    private void loadLightningSpells() {
        ResourceParser parser = new ResourceParser(LIGHTNING_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            spellList.add(new LightningSpell(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, LEVEL_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DMG_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MANA_KEY)),
                    Integer.parseInt(parser.getRowValue(i, COST_KEY))
            ));
        }
    }
}
