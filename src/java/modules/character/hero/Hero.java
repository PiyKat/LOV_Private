package src.java.modules.character.hero;

import src.java.exceptions.EquipFailure;
import src.java.formulae.HeroFormulae;
import src.java.modules.character.IBattleMan;
import src.java.modules.character.ITradable;
import src.java.modules.character.Role;
import src.java.modules.items.Armor;
import src.java.modules.items.InventoryList;
import src.java.modules.items.Weapon;
import src.java.utils.SysOut;
import src.java.views.HeroView;

import java.util.ArrayList;

/**
 * Module of Hero with its properties/attributes getter and setter.
 */
public class Hero extends Role implements ITradable, IBattleMan {
    private final static int MIN_DEFENSE = 0;
    private final static int INIT_LEVEL = 1;
    private final String name;
    // Maximum loading of equipment
    private final int maxLoad;
    private final ArrayList<Weapon> weaponsOnHand;
    private final InventoryList inventoryList;
    private int level;
    private int exp;
    private int maxHP;
    private int maxMP;
    private int strength;
    private int defense;
    private int dexterity;
    private int agility;
    private int gold;
    private int HP;
    private int MP;
    private Armor equippedArmor;
    private int loading;
    private int pauseTurns;

    public Hero(String name, int exp, int mp, int strength, int dexterity, int agility, int gold, int maxLoad) {
        this.name = name;
        this.exp = exp;
        this.maxMP = this.MP = mp;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.gold = gold;
        this.maxLoad = maxLoad;
        this.defense = MIN_DEFENSE; // Base defense
        this.level = INIT_LEVEL; // Initial level of each hero.
        this.maxHP = this.HP = HeroFormulae.levelUpHP(this.level);
        this.inventoryList = new InventoryList();
        this.weaponsOnHand = new ArrayList<Weapon>();
        this.loading = 0;
    }

    @Override
    public String toString() {
        return HeroView.toString(this);
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = Math.min(HP, this.maxHP);
    }

    public void regainHP(double ratio) {
        this.setHP((int) (this.HP * ratio));
    }

    public void regainMP(double ratio) {
        this.setMP((int) (this.MP * ratio));
    }

    public void revive() {
        this.setHP(this.maxHP);
        this.setMP(this.maxMP);
        this.setCurrentPosition(this.getSpawnPosition());
    }

    public void getAttacked(int damage) {
        if (this.equippedArmor != null)
            damage = Math.max(damage - this.equippedArmor.getDefense(), 0);
        this.setHP(Math.max(this.HP - damage, 0));
    }

    public boolean isDead() {
        return this.HP == 0;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        this.level += 1;
        this.strength = (int) (this.strength * 1.05);
        this.defense = (int) (this.defense * 1.05);
        this.dexterity = (int) (this.dexterity * 1.05);
        this.agility = (int) (this.agility * 1.05);
        this.maxHP = this.HP = HeroFormulae.levelUpHP(this.level);
        this.maxMP = this.MP = HeroFormulae.levelUpMP(this.maxMP);
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = Math.min(MP, this.maxMP);
    }

    public int getAgility() {
        return agility;
    }

    public void addAgility(int value) {
        this.agility += value;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void addDexterity(int value) {
        this.dexterity += value;
    }

    public int getStrength() {
        return strength;
    }

    public void addStrength(int value) {
        this.strength += value;
    }

    public int getDefense() {
        return defense;
    }

    public void addDefense(int value) {
        this.defense += value;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        if (this.exp >= HeroFormulae.getExpToLevelUp(this.level)) {
            SysOut.println(this.name + "'s level up (" + this.level + ")");
            this.exp = Math.max(HeroFormulae.getExpToLevelUp(this.level) - this.exp, 0);
            this.levelUp();
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addGold(int amount) {
        this.gold += amount;
    }

    public InventoryList getInventoryList() {
        return inventoryList;
    }

    public ArrayList<Weapon> getWeaponsOnHand() {
        return weaponsOnHand;
    }

    public void equipWeapon(Weapon weapon) throws EquipFailure {
        int afterLoading = this.loading + weapon.getOccupancy();
        if (afterLoading > this.maxLoad) {
            throw new EquipFailure();
        }
        this.weaponsOnHand.add(weapon);
        this.loading = afterLoading;
    }

    public void unequipWeapon(int weaponIndex) {
        this.weaponsOnHand.remove(weaponIndex);
    }

    public void equipArmor(Armor armor) throws EquipFailure {
        this.equippedArmor = armor;
    }

    public void unequipArmor() {
        this.equippedArmor = null;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void recallToSpawn() {
        this.getCurrentPosition().setX(this.getSpawnPosition().getX());
        this.getCurrentPosition().setY(this.getSpawnPosition().getY());
    }

    public int getPauseTurns() {
        return pauseTurns;
    }

    public void setPauseTurns(int pauseTurns) {
        this.pauseTurns = pauseTurns;
    }
}