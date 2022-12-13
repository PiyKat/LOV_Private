package src.java.controllers;

import src.java.exceptions.InvalidActionException;
import src.java.formulae.BattleFormulae;
import src.java.formulae.HeroFormulae;
import src.java.formulae.MonsterFormulae;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.monster.Monster;
import src.java.modules.inputs.BattleActionInputs;
import src.java.modules.items.Inventory;
import src.java.modules.items.InventoryType;
import src.java.modules.items.Spell;
import src.java.modules.map.Map;
import src.java.modules.map.Position;
import src.java.utils.Logger;
import src.java.utils.SysOut;
import src.java.views.BattleView;
import src.java.views.HeroView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Controller class for battle logic for both heroes and monster
 */

public class BattleController {
    /**
     * Handle the interaction between heroes and monsters.
     */
    private static final double ATTACK_RANGE = Math.sqrt(2);
    private static final double REGAIN_RATIO = 1.1;
    private static final ArrayList<Hero> faintedHeros = new ArrayList<>();
    private static ArrayList<Monster> monsters;
    private static ArrayList<Hero> heros;
    private static Map map;

    public static void battle(Hero hero, BattleActionInputs action) {
        switch (action) {
            case ATTACK:
                attackMonster(hero);
                break;
            case CAST:
                castSpell(hero);
                break;
            case DRINK:
                EquipmentController.drinkPotion(hero);
                break;
            case EQUIP:
                EquipmentController.enterEquipmentMenu(hero);
                break;
            default:
                throw new InvalidActionException();
        }
    }

    private static Monster getTargetMonster(Hero hero) {
        ArrayList<Monster> candidates = getMonstersInRange(hero.getCurrentPosition());
        if (candidates.size() == 0) {
            throw new InvalidActionException();
        }

        if (candidates.size() == 1) {
            return candidates.get(0);
        }

        BattleView.promptAttackTarget(hero.getName(), candidates);
        int targetMonsterIndex = SelectController.getSelection(0, candidates.size());
        return candidates.get(targetMonsterIndex);
    }

    private static Hero getTargetHero(Monster monster) {
        ArrayList<Hero> candidates = getHerosInRange(monster.getCurrentPosition());
        if (candidates.size() == 0) {
            throw new InvalidActionException();
        }

        Collections.shuffle(candidates);
        for (Hero targetHero : candidates) {
            if (!targetHero.isDead()) {
                return targetHero;
            }
        }

        throw new InvalidActionException();
    }

    private static void attackMonster(Hero hero) {
        // Select target
        Monster target = getTargetMonster(hero);
        int damage = HeroFormulae.getDamageWeaponList(hero);

        // If the dodge succeeds
        if (BattleFormulae.isDodged(target)) {
            BattleView.showDodge(target);
        }
        // Otherwise, the damage hits the monster
        else {
            BattleView.showHit(target, damage);
            target.gotAttack(damage);
            killMonster(target);
        }
    }

    private static void killMonster(Monster monster) {
        if (monster.isDead()) {
            getMonsterReward(monster);
            BattleView.showMonsterDead(monster);
            map.removeMonsterPosition(monster.getCurrentPosition());
            MonsterController.removeMonster(monster);
        }
    }

    private static void getMonsterReward(Monster monster) {
        int gold = BattleFormulae.getMonsterGold(monster);
        int exp = BattleFormulae.getMonsterExp(monster);
        BattleView.showDeadMonsterReward(gold, exp);
        // All heros get rewards
        for (Hero hero : heros) {
            hero.setGold(hero.getGold() + gold);
            hero.setExp(hero.getExp() + exp);
        }
    }

    private static void castSpellToMonster(Monster target, Spell spell) {
        // If the dodge succeeds
        if (BattleFormulae.isDodged(target)) {
            BattleView.showDodge(target);
        }
        // Otherwise, the damage hits the monster
        else {
            switch (spell.getType()) {
                case FIRE:
                    target.reduceDamage(spell.getDamage());
                    break;
                case ICE:
                    target.reduceDefence(spell.getDamage());
                    break;
                case LIGHTNING:
                    target.reduceDodgeChance(spell.getDamage());
                    break;
            }
            BattleView.showSpellHit(target, spell);
        }
    }

    private static boolean isManaEnough(Hero hero, Spell spell) {
        return hero.getMP() >= spell.getManaCost();
    }

    private static void castSpell(Hero hero) {
        ArrayList<Inventory> spellList = hero.getInventoryList().get(InventoryType.SPELL);
        if (spellList.size() > 0) {
            SysOut.println("==== Select spell ====");
            HeroView.showSpellInInventoryList(hero);
            int selectedSpellIndex = SelectController.getSelection(0, spellList.size());
            Spell spell = (Spell) spellList.get(selectedSpellIndex);

            // Check if MP is enough
            if (isManaEnough(hero, spell)) {
                // Select target
                Monster target = getTargetMonster(hero);
                SysOut.println(hero.getName() +
                        " uses " + spell.getName() +
                        " on " + target.getName());
                castSpellToMonster(target, spell);
                hero.getInventoryList().get(InventoryType.SPELL).remove(spell);
            } else {
                SysOut.println("Mana is not enough.");
                throw new InvalidActionException();
            }
        } else {
            SysOut.println("No spells in this hero's inventory.");
            throw new InvalidActionException();
        }
    }

    public static void attackHero(Monster monster) {
        int damage = MonsterFormulae.getDamage(monster);

        Hero attackTarget = getTargetHero(monster);
        BattleView.showMonsterAttack(monster, attackTarget);
        if (BattleFormulae.isHeroDodged(attackTarget)) {
            // If the dodge succeeds
            BattleView.showDodge(attackTarget);
        } else {
            // Otherwise, the damage hits the hero
            BattleView.showHit(attackTarget, damage);
            attackTarget.getAttacked(monster.getDamage());
            if (attackTarget.isDead()) {
                BattleView.showHeroDead(attackTarget);
                faintedHeros.add(attackTarget);
            }
        }

    }

    public static void regain() {
        for (Monster monster : monsters) {
            monster.regainHP(REGAIN_RATIO);
        }
        for (Hero hero : heros) {
            if (!faintedHeros.contains(hero)) {
                hero.regainHP(REGAIN_RATIO);
                hero.regainMP(REGAIN_RATIO);
            }
        }
    }

    public static void reviveAllFaintedHeros() {
        for (Hero hero : faintedHeros) {
            hero.revive();
            BattleView.showHeroRespawn(hero);
        }
        faintedHeros.clear();
    }

    private static ArrayList<Monster> getMonstersInRange(Position heroPosition) {
        ArrayList<Monster> result = new ArrayList<>();
        for (Monster monster : monsters) {
            if (MapController.getDistance(monster.getCurrentPosition(), heroPosition) <= ATTACK_RANGE) {
                result.add(monster);
            }
        }
        return result;
    }

    private static ArrayList<Hero> getHerosInRange(Position monsterPosition) {
        Logger.debug(monsterPosition);
        ArrayList<Hero> result = new ArrayList<>();
        for (Hero hero : heros) {
            Logger.debug(hero.toString());
            if (MapController.getDistance(hero.getCurrentPosition(), monsterPosition) <= ATTACK_RANGE) {
                result.add(hero);
            }
        }
        return result;
    }

    public static void setMap(Map map) {
        BattleController.map = map;
    }

    public static void setMonsters(ArrayList<Monster> monsters) {
        BattleController.monsters = monsters;
    }

    public static void setHeros(ArrayList<Hero> heros) {
        BattleController.heros = heros;
    }
}
