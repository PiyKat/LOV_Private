package src.java.modules.items;

import src.java.utils.ResourceParser;

import java.util.ArrayList;

/**
 * Load initial data from setting files for potions.
 */
public class PotionBuilder {
    private final static String POTION_SETTING = "./data/items/Potions.txt";

    private static final String NAME_KEY = "Name";
    private static final String LEVEL_KEY = "required level";
    private static final String TYPE_KEY = "attribute affected";
    private static final String EFFECT_KEY = "attribute increase";
    private static final String COST_KEY = "cost";

    private static ArrayList<Inventory> effectPotionList;

    public PotionBuilder() {
        effectPotionList = new ArrayList<>();
        loadPotion();
    }

    public static ArrayList<Inventory> getPotionList() {
        return effectPotionList;
    }

    private void loadPotion() {
        ResourceParser parser = new ResourceParser(POTION_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            effectPotionList.add(new EffectPotion(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, LEVEL_KEY)),
                    this.getAttributeType(parser.getRowValue(i, TYPE_KEY)),
                    Integer.parseInt(parser.getRowValue(i, EFFECT_KEY)),
                    Integer.parseInt(parser.getRowValue(i, COST_KEY))
            ));
        }
    }

    private EffectPotion.AttributeType[] getAttributeType(String s) {
        String[] attributes = s.split("/");
        EffectPotion.AttributeType[] result = new EffectPotion.AttributeType[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            String attribute = attributes[i];
            if (attribute.trim().equalsIgnoreCase("Health")) {
                result[i] = EffectPotion.AttributeType.HP;
            } else if (attribute.trim().equalsIgnoreCase("Mana")) {
                result[i] = EffectPotion.AttributeType.MP;
            } else if (attribute.trim().equalsIgnoreCase("Strength")) {
                result[i] = EffectPotion.AttributeType.STR;
            } else if (attribute.trim().equalsIgnoreCase("Agility")) {
                result[i] = EffectPotion.AttributeType.AGI;
            } else if (attribute.trim().equalsIgnoreCase("Dexterity")) {
                result[i] = EffectPotion.AttributeType.DEX;
            } else if (attribute.trim().equalsIgnoreCase("Defense")) {
                result[i] = EffectPotion.AttributeType.DEF;
            } else if (attribute.trim().equalsIgnoreCase("all")) {
                return new EffectPotion.AttributeType[]{
                        EffectPotion.AttributeType.HP, EffectPotion.AttributeType.MP,
                        EffectPotion.AttributeType.STR, EffectPotion.AttributeType.DEX,
                        EffectPotion.AttributeType.DEF, EffectPotion.AttributeType.AGI,
                };
            }
        }
        return result;
    }
}
