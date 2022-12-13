package src.java.modules.items;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Module of InventoryList with list operations.
 */
public class InventoryList {
    private final HashMap<InventoryType, ArrayList<Inventory>> list;

    public InventoryList() {
        this.list = new HashMap<>();
        for (InventoryType key : InventoryType.values()) {
            this.list.put(key, new ArrayList<>());
        }
    }

    public int size(boolean includeNotSellable) {
        int result = 0;
        for (InventoryType type : this.list.keySet()) {
            ArrayList<Inventory> sublist = this.list.get(type);
            if (sublist == null) {
                ; // ignore this type of products
            } else if (includeNotSellable) {
                result += sublist.size();
            } else if (type.isSellable()) {
                result += sublist.size();
            }
        }
        return result;
    }

    public int getNumWeapons() {
        if (this.list.containsKey(InventoryType.WEAPON)) {
            return this.list.get(InventoryType.WEAPON).size();
        }
        return 0;
    }

    public ArrayList<Inventory> get(InventoryType key) {
        return this.list.get(key);
    }

    public void set(InventoryType key, ArrayList<Inventory> list) {
        this.list.put(key, list);
    }

    public void add(InventoryType key, Inventory item) {
        this.list.get(key).add(item);
    }

    public void remove(InventoryType key, Inventory item) {
        this.list.get(key).remove(item);
    }

    public boolean has(InventoryType key, Inventory item) {
        return this.list.get(key).contains(item);
    }
}