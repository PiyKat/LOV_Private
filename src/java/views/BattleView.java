package src.java.views;

import src.java.modules.character.hero.Hero;
import src.java.modules.character.monster.Monster;
import src.java.modules.items.Spell;
import src.java.utils.SysOut;

import java.util.ArrayList;

import static src.java.views.Messages.*;

/**
 * Define the format to display battle and fighting stages.
 */
public class BattleView {
    public static <T> void promptAttackTarget(String name, ArrayList<T> monsters) {
        SysOut.println(String.format(ATTACK_QUERY, name));
        SelectionView.showList(monsters, 0);
    }

    public static void showDodge(Monster target) {
        SysOut.println(String.format(RESULT_DODGE, target.getName()));
    }

    public static void showDodge(Hero target) {
        SysOut.println(String.format(RESULT_DODGE, target.getName()));
    }

    public static void showHit(Monster target, int damage) {
        SysOut.println(String.format(RESULT_HIT, target.getName(), damage));
    }

    public static void showSpellHit(Monster target, Spell spell) {
        switch (spell.getType()) {
            case ICE:
                SysOut.println(String.format(RESULT_ICE_SPELL_HIT, target.getName(), spell.getDamage()));
                break;
            case FIRE:
                SysOut.println(String.format(RESULT_FIRE_SPELL_HIT, target.getName(), spell.getDamage()));
                break;
            case LIGHTNING:
                SysOut.println(String.format(RESULT_LIGHTING_SPELL_HIT, target.getName(), spell.getDamage()));
                break;
        }
    }

    public static void showHit(Hero target, int damage) {
        SysOut.println(String.format(RESULT_HIT, target.getName(), damage));
    }

    public static void showInvalidAction() {
        SysOut.println(INVALID_ACTION);
    }

    public static void showHeroRespawn(Hero hero) {
        SysOut.println(String.format(HERO_RESPAWN, hero.getName()));
    }

    public static void showHeroDead(Hero hero) {
        SysOut.println(String.format(HERO_DEAD, hero.getName()));
    }

    public static void showMonsterDead(Monster monster) {
        SysOut.println(String.format(MONSTER_DEAD, monster.getName()));
    }

    public static void showMonsterAttack(Monster monster, Hero target) {
        SysOut.println(String.format("%s %s is attacking %s", SpaceViewType.MONSTER, monster.getName(), target.getName()));
    }

    public static void showDeadMonsterReward(int gold, int exp) {
        SysOut.println(String.format("\uD83E\uDE99 Every hero get %d gold and %d experience points.", gold, exp));
    }
}
