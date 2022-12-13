package src.java.modules.character.monster;

import src.java.formulae.MonsterFormulae;
import src.java.modules.character.IBattleMan;
import src.java.modules.character.INonPlayerCharacter;
import src.java.modules.character.Role;
import src.java.views.MonsterView;

/**
 * Module of Monster with its properties/attributes getter and setter.
 */
public abstract class Monster extends Role implements INonPlayerCharacter, IBattleMan {
    private static final int REVIVE_DELAY = 1;
    private final String name;
    private int level;
    private int damage;
    private int defense;
    private int dodgeRatio;
    private int HP;
    private int pauseTurns;

    public Monster(String name, int level, int damage, int defense, int dodgeRatio) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodgeRatio = dodgeRatio;
        this.HP = MonsterFormulae.getHP(level);
    }

    public void updateLevelAndHP(int level) {
        this.level = level;
        this.HP = MonsterFormulae.getHP(level);
    }

    public void gotAttack(int damage) {
        this.HP -= damage;
        this.HP = Math.max(this.HP, 0);
    }

    public void reduceDamage(int amount) {
        this.damage = Math.max(this.damage - amount, 0);
    }

    public void reduceDefence(int amount) {
        this.defense = Math.max(this.defense - amount, 0);
    }

    public void reduceDodgeChance(int amount) {
        this.dodgeRatio = Math.max(this.dodgeRatio - amount, 0);
    }

    public boolean isDead() {
        return this.HP <= 0;
    }

    public int getHP() {
        return this.HP;
    }

    public void regainHP(double ratio) {
        this.HP = (int) Math.min(MonsterFormulae.getHP(level), this.HP * ratio);
    }

    @Override
    public void sayWelcome() {
        MonsterView.displayWelcome(this.getCurrentPosition());
    }

    @Override
    public void sayBye() {
        MonsterView.displayBye();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getDodgeRatio() {
        return dodgeRatio;
    }

    @Override
    public String toString() {
        return MonsterView.toString(this);
    }

    @Override
    public void revive() {
        this.HP = MonsterFormulae.getHP(level);
        this.setCurrentPosition(this.getSpawnPosition());
        this.setPauseTurns(REVIVE_DELAY);
    }

    @Override
    public int getPauseTurns() {
        return pauseTurns;
    }

    @Override
    public void setPauseTurns(int pauseTurns) {
        this.pauseTurns = pauseTurns;
    }
}