package src.java.modules.character.vendor;

import src.java.modules.character.INonPlayerCharacter;
import src.java.modules.character.ITradable;
import src.java.modules.items.InventoryList;
import src.java.utils.SysOut;

import static src.java.views.Messages.VENDOR_FAREWELL;
import static src.java.views.Messages.VENDOR_GREETING;

/**
 * Module of Vendor with its properties/attributes getter and setter.
 */
public class Vendor implements ITradable, INonPlayerCharacter {
    /**
     * Handles selling/buying transaction in markets
     */
    private final InventoryList inventoryList;

    public Vendor(InventoryList fullStock) {
        this.inventoryList = fullStock;
    }

    public InventoryList getInventoryList() {
        return inventoryList;
    }

    /**
     * Display welcome message
     */
    @Override
    public void sayWelcome() {
        SysOut.println(VENDOR_GREETING);
    }

    /**
     * Display farewell message
     */
    @Override
    public void sayBye() {
        SysOut.println(VENDOR_FAREWELL);
    }
}
