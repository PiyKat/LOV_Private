package src.java.modules.items.spell;

import src.java.modules.items.Spell;

/**
 * Module of FireSpell with its properties/attributes getter and setter.
 */
public class FireSpell extends Spell {
    public FireSpell(String name, int level, int damage, int mana_cost, int price) {
        super(name, level, damage, mana_cost, price);
    }

    public SpellType getType() {
        return SpellType.FIRE;
    }
}