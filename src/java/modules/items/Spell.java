package src.java.modules.items;

import src.java.views.ItemView;

/**
 * Module of Spell with its properties/attributes getter and setter.
 */
public abstract class Spell extends Inventory implements ISellable {
    private final String name;
    private final int level;
    private final int damage;
    private final int mana_cost;
    private final int price;

    public Spell(String name, int level, int damage, int mana_cost, int price) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.mana_cost = mana_cost;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return mana_cost;
    }

    abstract public SpellType getType();

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return ItemView.toString(this);
    }

    public enum SpellType {
        FIRE("FireSpell"), ICE("IceSpell"), LIGHTNING("LightningSpell");
        private final String name;

        SpellType(String name) {
            this.name = name;
        }

        public String getName() {
            return "[" + name + "]";
        }
    }
}