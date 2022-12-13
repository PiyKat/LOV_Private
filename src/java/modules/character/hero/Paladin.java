package src.java.modules.character.hero;

/**
 * Module of Paladin with its properties/attributes getter and setter.
 */
public class Paladin extends Hero {
    private final static int LOADING = 4;

    public Paladin(String name, int exp, int mp, int strength, int dexterity, int agility, int gold) {
        super(name, exp, mp, strength, dexterity, agility, gold, LOADING);
    }

    @Override
    public String toString() {
        return String.format("%-12s ", "[Paladin]") + super.toString();
    }
}
