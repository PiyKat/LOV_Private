package src.java.formulae;

import src.java.modules.character.hero.Hero;
import src.java.modules.items.Weapon;

public class HeroFormulae {
    /**
     * Calculate HP based on levels
     *
     * @param level: new level
     * @return HP
     */
    public static int levelUpHP(int level) {
        return level * 100;
    }

    /**
     * Calculate MP based on original MP
     *
     * @param origMP: original MP
     * @return HP
     */
    public static int levelUpMP(int origMP) {
        return (int) (origMP * 1.1);
    }

    /**
     * Get hero's damage with weapons
     *
     * @param hero
     * @return damage
     */
    public static int getDamageWeaponList(Hero hero) {
        int weaponDamage = 0;
        for (Weapon weapon : hero.getWeaponsOnHand()) {
            weaponDamage += weapon.getDamage();
        }
        return (int) ((hero.getStrength() + weaponDamage * 0.05));
    }

    /**
     * Get dodge chance of a hero
     *
     * @param hero
     * @return dodge chance
     */
    public static double getDodgeChance(Hero hero) {
        return hero.getAgility() * 0.002 * 0.1;
    }

    /**
     * Get experience for level up
     *
     * @param level
     * @return experience
     */
    public static int getExpToLevelUp(int level) {
        return level * 10;
    }

}
