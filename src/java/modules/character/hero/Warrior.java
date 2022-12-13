package src.java.modules.character.hero;

/**
 * Module of Warrior with its properties/attributes getter and setter.
 */
public class Warrior extends Hero {

    private final static int LOADING = 4;

    public Warrior(String name, int exp, int mp, int strength, int dexterity, int agility, int gold) {
        super(name, exp, mp, strength, dexterity, agility, gold, LOADING);
    }

    @Override
    public String toString() {
        return String.format("%-12s ", "[Warrior]") + super.toString();
    }
}
