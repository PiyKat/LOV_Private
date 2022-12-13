package src.java.formulae;

import src.java.modules.character.hero.Hero;
import src.java.modules.character.monster.Monster;

public class BattleFormulae {
    /**
     * Sample a monster dodge event
     *
     * @param monster
     * @return
     */
    public static boolean isDodged(Monster monster) {
        return Math.random() < MonsterFormulae.getDodgeChance(monster);
    }

    /**
     * Sample a hero dodge event
     *
     * @param hero
     * @return
     */
    public static boolean isHeroDodged(Hero hero) {
        return Math.random() < HeroFormulae.getDodgeChance(hero);
    }

    /**
     * Calculate the gold that a hero can get when a monster is killed
     *
     * @param monster
     * @return the gold that a hero can get when a monster is killed
     */
    public static int getMonsterGold(Monster monster) {
        return monster.getLevel() * 500;
    }

    /**
     * Calculate the experience that a hero can get when a monster is killed
     *
     * @param monster
     * @return the experience that a hero can get when a monster is killed
     */
    public static int getMonsterExp(Monster monster) {
        return monster.getLevel() * 2;
    }
}
