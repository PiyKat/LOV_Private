package src.java.modules.character.hero;

/**
 * Module of Sorcerer with its properties/attributes getter and setter.
 */
public class Sorcerer extends Hero {
    private final static int LOADING = 2;

    public Sorcerer(String name, int exp, int mp, int strength, int dexterity, int agility, int gold) {
        super(name, exp, mp, strength, dexterity, agility, gold, LOADING);
    }

    @Override
    public String toString() {
        return String.format("%-12s ", "[Sorcerer]") + super.toString();
    }
}
