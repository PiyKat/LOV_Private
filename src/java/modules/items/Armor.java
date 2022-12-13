package src.java.modules.items;

import src.java.views.ItemView;

/**
 * Module of Armor with its properties/attributes getter and setter.
 */
public class Armor extends Inventory implements IWearable, ISellable {
    private final String name;
    private final int level;
    private final int defense;
    private final int cost;

    public Armor(String name, int level, int defense, int cost) {
        this.name = name;
        this.level = level;
        this.defense = defense;
        this.cost = cost;
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

    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return ItemView.toString(this);
    }
}