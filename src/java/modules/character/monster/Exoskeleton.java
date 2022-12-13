package src.java.modules.character.monster;

/**
 * Module of Exoskeleton with its properties/attributes getter and setter.
 */
public class Exoskeleton extends Monster {
    public Exoskeleton(String name, int level, int damage, int defense, int dodgeRatio) {
        super(name, level, damage, defense, dodgeRatio);
    }

    @Override
    public String toString() {
        return String.format("%12s ", "[Exoskeleton]") + super.toString();
    }
}