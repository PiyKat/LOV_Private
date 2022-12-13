package src.java.modules.items.spell;

import src.java.modules.items.Spell;

/**
 * Module of LightningSpell with its properties/attributes getter and setter.
 */
public class LightningSpell extends Spell {
    public LightningSpell(String name, int level, int damage, int mana_cost, int price) {
        super(name, level, damage, mana_cost, price);
    }

    public SpellType getType() {
        return SpellType.LIGHTNING;
    }
}