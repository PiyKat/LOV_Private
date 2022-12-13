package src.java.modules.items;

import src.java.views.ItemView;

/**
 * Module of Weapon with its properties/attributes getter and setter.
 */
public class Weapon extends Inventory implements IWearable, ISellable {
    private final String name;
    private final int level;
    private final int damage;
    private final int occupancy;
    private final int cost;

    public Weapon(String name, int level, int damage, int cost, int occupancy) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.cost = cost;
        this.occupancy = occupancy;
    }

    @Override
    public int getPrice() {
        return this.cost;
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

    public int getOccupancy() {
        return occupancy;
    }

    @Override
    public String toString() {
        return ItemView.toString(this);
    }
}