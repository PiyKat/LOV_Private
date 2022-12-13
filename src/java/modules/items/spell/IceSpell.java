package src.java.modules.items.spell;

import src.java.modules.items.Spell;

/**
 * Module of IceSpell with its properties/attributes getter and setter.
 */
public class IceSpell extends Spell {
    public IceSpell(String name, int level, int damage, int mana_cost, int price) {
        super(name, level, damage, mana_cost, price);
    }

    public SpellType getType() {
        return SpellType.ICE;
    }
}