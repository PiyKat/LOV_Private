package src.java.modules.character.monster;

/**
 * Module of Spirit with its properties/attributes getter and setter.
 */
public class Spirit extends Monster {
    public Spirit(String name, int level, int damage, int defense, int dodgeRatio) {
        super(name, level, damage, defense, dodgeRatio);
    }

    @Override
    public String toString() {
        return String.format("%12s ", "[Spirit]") + super.toString();
    }
}
