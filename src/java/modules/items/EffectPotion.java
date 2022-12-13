package src.java.modules.items;

import src.java.modules.character.hero.Hero;
import src.java.utils.SysOut;
import src.java.views.ItemView;

/**
 * Module of EffectPotion with its properties/attributes getter and setter.
 */
public class EffectPotion extends Inventory implements IConsumable, ISellable {
    private final String name;

    private final int level;
    private final AttributeType[] types;
    private final int effect;
    private final int cost;
    private boolean isEmpty;

    public EffectPotion(String name, int level, AttributeType[] types, int effect, int cost) {
        this.name = name;
        this.level = level;
        this.types = types;
        this.effect = effect;
        this.cost = cost;
        this.isEmpty = false;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public AttributeType[] getTypes() {
        return types;
    }

    public int getEffect() {
        return effect;
    }

    @Override
    public void drink(Hero target) {
        if (target.getLevel() < this.level) {
            return;
        }
        for (AttributeType attr : this.types) {
            switch (attr) {
                case HP:
                    target.setHP(target.getHP() + this.effect);
                    SysOut.println(target.getName() + " drink " + this.name + " and gets " + this.effect + " more HPs.");
                    break;
                case MP:
                    target.setMP(target.getMP() + this.effect);
                    SysOut.println(target.getName() + " drink " + this.name + " and gets " + this.effect + " more MPs.");
                    break;
                case STR:
                    target.addStrength(this.effect);
                    SysOut.println(target.getName() + " drink " + this.name + " and gets " + this.effect + " more strength.");
                    break;
                case AGI:
                    target.addAgility(this.effect);
                    SysOut.println(target.getName() + " drink " + this.name + " and gets " + this.effect + " more agility.");
                    break;
                case DEX:
                    target.addDexterity(this.effect);
                    SysOut.println(target.getName() + " drink " + this.name + " and gets " + this.effect + " more dexterity.");
                    break;
                case DEF:
                    target.addDefense(this.effect);
                    SysOut.println(target.getName() + " drink " + this.name + " and gets " + this.effect + " more defense.");
                    break;
            }
        }
        this.isEmpty = true;
    }

    @Override
    public int getPrice() {
        if (this.isEmpty) {
            return 0;
        }
        return this.cost;
    }

    @Override
    public String toString() {
        return ItemView.toString(this);
    }

    public enum AttributeType {HP, MP, STR, AGI, DEX, DEF}
}
