package src.java.views;

import src.java.modules.items.*;
import src.java.utils.SysOut;

import java.util.ArrayList;
import java.util.Arrays;

import static src.java.views.Messages.NO_ITEM;

/**
 * Define the format to display inventory and items.
 */
public class ItemView {
    public static String toString(Weapon weapon) {
        return String.format("\uD83D\uDDE1️%-16s $%-5d %-20s | Level required:%2d | Damage: %d",
                "[Weapon]", weapon.getPrice(), weapon.getName(), weapon.getLevel(), weapon.getDamage()
        );
    }

    public static String toString(Armor armor) {
        return String.format("\uD83D\uDEE1️%-16s $%-5d %-20s | Level required:%2d | Defence: %d",
                "[Armor]", armor.getPrice(), armor.getName(), armor.getLevel(), armor.getDefense()
        );
    }

    public static String toString(Spell spell) {
        return String.format("\uD83D\uDCDC%-16s $%-5d %-20s | Level required:%2d | Damage:%-4d | Mana cost:%d",
                spell.getType().getName(), spell.getPrice(), spell.getName(), spell.getLevel(),
                spell.getDamage(), spell.getManaCost()
        );
    }

    public static String toString(EffectPotion potion) {
        return String.format("\uD83C\uDF7C%-16s $%-5d %-20s | Level required:%2d | Effect value:%3d | Effect on: " +
                        Arrays.toString(potion.getTypes()), "[Potion]", potion.getPrice(), potion.getName(),
                potion.getLevel(), potion.getEffect()
        );
    }

    public static void show(InventoryList a, int init_i, boolean includeSpells) {
        if (a == null) {
            return;
        }

        int shift_i = 0;
        for (InventoryType type : InventoryType.values()) {
            SelectionView.showList(a.get(type), shift_i + init_i);
            shift_i += shiftSize(a.get(type));
        }

        if (shift_i == 0) {
            SysOut.println(NO_ITEM);
        }
    }

    private static <T> int shiftSize(ArrayList<T> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
