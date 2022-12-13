package src.java.views;

import src.java.utils.SysOut;

import static src.java.views.Messages.INFO_HINT;

/**
 * Define the format to display hero equipment information
 */
public class EquipmentView {
    public static void heroSelectionQuery() {
        SysOut.println("Which hero wants to enter the equipment menu?");
    }

    public static void typeSelectionQuery() {
        SysOut.println("Select type of equipment to change: (M) Armor (N) Weapon");
    }

    public static void showHelpInstruction() {
        SysOut.println(INFO_HINT);
    }
}
