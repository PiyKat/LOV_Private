package src.java.controllers;

import src.java.exceptions.EmptySelection;
import src.java.exceptions.EquipFailure;
import src.java.exceptions.InvalidActionException;
import src.java.exceptions.requests.HelpInstructionRequest;
import src.java.exceptions.requests.InformationRequest;
import src.java.exceptions.requests.MapDisplayRequest;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.hero.HeroParty;
import src.java.modules.inputs.EquipInputs;
import src.java.modules.inputs.IInputs;
import src.java.modules.inputs.Inputs;
import src.java.modules.items.*;
import src.java.utils.SysOut;
import src.java.views.*;

import java.util.ArrayList;

/**
 * Control the equipment interaction
 */
public class EquipmentController {

    public static void enterEquipmentMenu(HeroParty heroParty) {
        EquipmentView.heroSelectionQuery();
        SelectionView.showList(heroParty.getHeros(), 0);
        int selectedHeroIndex = SelectController.getSelection(0, heroParty.getHeros().size());
        Hero hero = heroParty.getHeros().get(selectedHeroIndex);
        enterEquipmentMenu(hero);
    }

    public static void enterEquipmentMenu(Hero hero) {
        for (; ; ) {
            EquipmentView.typeSelectionQuery();
            IInputs action = KeyController.getKey(Inputs.Type.EQUIP);
            if (EquipInputs.ARMOR.equals(action)) {
                switchArmor(hero);
                break;
            } else if (EquipInputs.WEAPON.equals(action)) {
                switchWeapon(hero);
                break;
            } else {
                BattleView.showInvalidAction();
            }
        }
    }

    public static void drinkPotion(Hero hero) {
        try {
            EffectPotion potion = selectPotion(hero);
            potion.drink(hero);
            hero.getInventoryList().get(InventoryType.POTION).remove(potion);
        } catch (EmptySelection e) {
            SysOut.println("No potions.");
            throw new InvalidActionException();
        }
    }

    private static EffectPotion selectPotion(Hero hero) throws EmptySelection {
        ArrayList<Inventory> potionList = hero.getInventoryList().get(InventoryType.POTION);
        if (potionList.size() == 0) {
            throw new EmptySelection();
        }
        SysOut.println("==== Select Potion ====");
        HeroView.showPotionInInventoryList(hero);
        int selectIndex = SelectController.getSelection(0, potionList.size());
        return (EffectPotion) potionList.get(selectIndex);
    }

    private static void addWeapon(Hero hero) {
        SysOut.println("==== Select weapon to add ====");
        InventoryList inventoryList = hero.getInventoryList();
        if (inventoryList.getNumWeapons() > 0) {
            HeroView.showWeaponInInventoryList(hero);
            int inventoryIndex = SelectController.getSelection(0,
                    hero.getInventoryList().get(InventoryType.WEAPON).size());
            Weapon weapon = (Weapon) hero.getInventoryList().get(InventoryType.WEAPON).get(inventoryIndex);
            try {
                hero.equipWeapon(weapon);
                SysOut.println("Equipped weapon " + weapon.getName());
                hero.getInventoryList().get(InventoryType.WEAPON).remove(weapon); // Remove from inventory list (bag)
            } catch (EquipFailure equipException) {
                SysOut.println("Fail to equip weapon " + weapon.getName() + ": not enough loading.");
            }
        } else {
            SysOut.println("No weapon to equip.");
            throw new InvalidActionException();
        }
    }

    private static void unequipWeapon(Hero hero) {
        SysOut.println("==== Select weapon to remove ====");
        ArrayList<Weapon> weaponList = hero.getWeaponsOnHand();
        if (weaponList.size() > 0) {
            HeroView.showWeaponList(hero);
            int weaponIndex = SelectController.getSelection(0, weaponList.size());
            Weapon weapon = weaponList.get(weaponIndex);
            hero.unequipWeapon(weaponIndex);
            hero.getInventoryList().get(InventoryType.WEAPON).add(weapon);
            SysOut.println("Unequipped weapon " + weapon.getName());
        }
    }

    private static void switchWeapon(Hero hero) {
        for (; ; ) {
            // Add/remove weapon
            SysOut.println("==== Weapon (A) Add (R) Remove (E) Exit ====");
            IInputs action = KeyController.getKey(Inputs.Type.EQUIP);
            if (EquipInputs.ADD.equals(action)) {
                addWeapon(hero);
            } else if (EquipInputs.REMOVE.equals(action)) {
                unequipWeapon(hero);
            } else if (EquipInputs.EXIT.equals(action)) {
                SysOut.println("==== Exit equipment menu ====");
                break;
            } else {
                BattleView.showInvalidAction();
            }
        }
    }

    private static void addArmor(Hero hero) {
        SysOut.println("==== Select armor to add ====");
        ArrayList<Inventory> armorList = hero.getInventoryList().get(InventoryType.ARMOR);
        if (armorList.size() > 0) {
            HeroView.showArmorInInventoryList(hero);
            int inventoryIndex = SelectController.getSelection(0,
                    armorList.size());
            Armor armor = (Armor) armorList.get(inventoryIndex);
            hero.equipArmor(armor);
            SysOut.println("Equipped armor " + armor.getName());
            hero.getInventoryList().get(InventoryType.ARMOR).remove(armor); // Remove from inventory list (bag)
        } else {
            SysOut.println("No armor to equip.");
            throw new InvalidActionException();
        }
    }

    private static void unequipArmor(Hero hero) {
        if (hero.getEquippedArmor() != null) {
            SysOut.println("Unequipped armor " + hero.getEquippedArmor().getName());
            hero.getInventoryList().get(InventoryType.ARMOR).add(hero.getEquippedArmor());
            hero.unequipArmor();
        } else {
            SysOut.println("No equipped armor.");
            throw new InvalidActionException();
        }
    }

    private static void switchArmor(Hero hero) {
        for (; ; ) {
            // Add/remove weapon
            SysOut.println("==== Armor (A) Add (R) Remove (E) Exit ====");
            try {
                IInputs action = KeyController.getKey(Inputs.Type.EQUIP);
                if (EquipInputs.ADD.equals(action)) {
                    addArmor(hero);
                } else if (EquipInputs.REMOVE.equals(action)) {
                    unequipArmor(hero);
                } else if (EquipInputs.EXIT.equals(action)) {
                    SysOut.println("==== Exit equipment menu ====");
                    break;
                } else {
                    BattleView.showInvalidAction();
                }
            } catch (InformationRequest r) {
                HeroView.show(hero);
                ItemView.show(hero.getInventoryList(), 0, true);
            } catch (MapDisplayRequest r) {
                MapController.showMap();
            } catch (HelpInstructionRequest r) {
                EquipmentView.showHelpInstruction();
            }
        }
    }
}
