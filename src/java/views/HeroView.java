package src.java.views;

import src.java.controllers.KeyController;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.hero.HeroParty;
import src.java.modules.inputs.IInputs;
import src.java.modules.items.InventoryType;
import src.java.modules.items.Weapon;
import src.java.utils.Logger;
import src.java.utils.SysOut;

import static src.java.views.Messages.*;

/**
 * Define the format to display hero and its actions.
 */
public class HeroView {
    public static String toString(Hero hero) {
        return String.format(
                "%-20s Lv.%-2d | STR %3d | DEF %1d | AGI %3d | DEX %3d | EXP %2d | HP %2d | MP %4d | \uD83D\uDCB0%5d",
                hero.getName(), hero.getLevel(), hero.getStrength(), hero.getDefense(), hero.getAgility(),
                hero.getDexterity(), hero.getExp(), hero.getHP(), hero.getMP(), hero.getGold()
        );
    }

    public static void showHeroPosition(Hero hero) {
        MapView.showPosition(hero);
    }

    public static void showActionQuery(Hero hero) {
        SysOut.println(String.format(ACTION_QUERY, hero.getName()));
    }

    public static void showHelpInstruction() {
        SysOut.println(GAME_INTRO);
        SysOut.println(GLOBAL_HINT);
    }

    public static void showMove(Hero hero, IInputs key) {
        SysOut.println(String.format("%s takes the move (%s).", hero.getName(), key));
    }

    public static void showBuy(Hero hero, int item_i) {
        SysOut.println(String.format("%s selects (%d) to buy.", hero.getName(), item_i));
    }

    public static void showSell(Hero hero, String item_name) {
        SysOut.println(String.format("%s selects (%s) to sell.", hero.getName(), item_name));
    }

    public static void show(HeroParty heroParty) {
        if (!heroParty.hasHero()) {
            Logger.info(NO_HERO);
        }

        SysOut.println(HERO_TEAM_INFO);
        for (Hero hero : heroParty.getHeros()) {
            show(hero);
        }
        SysOut.println(SEPARATOR_LINE);
    }

    public static void show(Hero hero) {
        SysOut.println(hero);
        HeroView.showHeroPosition(hero);
    }

    public static void showSelectedHero(Hero hero) {
        SysOut.println(SHOW_SELECTED_HERO);
        HeroView.show(hero);
        SysOut.println(SEPARATOR_LINE);
    }

    public static void showWeaponInInventoryList(Hero hero) {
        SelectionView.showList(hero.getInventoryList().get(InventoryType.WEAPON), 0);
    }

    public static void showArmorInInventoryList(Hero hero) {
        SelectionView.showList(hero.getInventoryList().get(InventoryType.ARMOR), 0);
    }

    public static void showSpellInInventoryList(Hero hero) {
        SelectionView.showList(hero.getInventoryList().get(InventoryType.SPELL), 0);
    }

    public static void showPotionInInventoryList(Hero hero) {
        SelectionView.showList(hero.getInventoryList().get(InventoryType.POTION), 0);
    }


    public static void showWeaponList(Hero hero) {
        SysOut.println("--- Equipped weapons ---");
        for (int i = 0; i < hero.getWeaponsOnHand().size(); i++) {
            Weapon weapon = hero.getWeaponsOnHand().get(i);
            SysOut.println("(" + i + ") " + ItemView.toString(weapon));
        }
    }

    public static void showArmor(Hero hero) {
        SysOut.println("--- Equipped armors ---");
        if (hero.getEquippedArmor() != null) {
            SysOut.println(hero.getEquippedArmor());
        }
    }

    public static void showHeros(HeroParty heroParty) {
        for (Hero hero : heroParty.getHeros()) {
            SysOut.println(hero);
            HeroView.showWeaponList(hero);
            HeroView.showArmor(hero);
            SysOut.println("--- Inventory ---");
            ItemView.show(hero.getInventoryList(), 0, true);
        }
        SysOut.println(SEPARATOR_LINE);
    }

    public static void showTeleportQuery() {
        SysOut.println(TELEPORT_QUERY);
    }

    public static void endOfTurn() {
        KeyController.enterToContinue();
    }
}
