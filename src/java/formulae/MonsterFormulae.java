package src.java.formulae;

import src.java.modules.character.monster.Monster;

public class MonsterFormulae {
    /**
     * Calculate monsters' HP based on levels
     *
     * @param level
     * @return HP
     */
    public static int getHP(int level) {
        return level * 100;
    }

    /**
     * Calculate monsters' dodge chance
     *
     * @param monster
     * @return dodge chance
     */
    public static double getDodgeChance(Monster monster) {
        return monster.getDodgeRatio() * 0.0005;
    }

    /**
     * Calculate monster's damage
     *
     * @param monster
     * @return daage
     */
    public static int getDamage(Monster monster) {
        return monster.getDamage();
    }
}
