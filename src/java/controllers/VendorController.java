package src.java.controllers;

import src.java.exceptions.requests.HelpInstructionRequest;
import src.java.exceptions.requests.InformationRequest;
import src.java.exceptions.requests.MapDisplayRequest;
import src.java.exceptions.requests.QuitRequest;
import src.java.formulae.MarketFormulae;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.vendor.Vendor;
import src.java.modules.inputs.IInputs;
import src.java.modules.inputs.Inputs;
import src.java.modules.inputs.TradeConfirmationInputs;
import src.java.modules.items.ISellable;
import src.java.modules.items.Inventory;
import src.java.modules.items.InventoryType;
import src.java.modules.map.Map;
import src.java.utils.Logger;
import src.java.views.HeroView;
import src.java.views.ItemView;
import src.java.views.MapView;
import src.java.views.MarketView;

import java.util.ArrayList;

import static src.java.views.Messages.INVALID_CONFIRM;


public class VendorController {
    /**
     * Handles the market interaction
     * selectController: to handle user inputs
     * vendor: sell the goods to heroes or buy stuffs from them
     * heroParty: shoppers or sellers
     * map: current map
     */
    private Vendor vendor;
    private Hero hero;

    private Map currentMap;

    /**
     * Start interaction with the vendors
     *
     * @param map: current map (for displaying map upon user requests)
     */
    public void enter(Map map) {
        this.currentMap = map;
        vendor.sayWelcome();
        for (; ; ) {
            try {
                this.trade();
            } catch (MapDisplayRequest r) {
                MapView.show(currentMap);
                KeyController.enterToContinue();
            } catch (QuitRequest r) {
                vendor.sayBye();
                return;
            }
        }
    }

    private void trade() {
        // Show all selections.
        MarketView.showAllSelections(this.vendor, this.hero);
        // Get item selection
        MarketView.promptInputHint();

        int vendorSize = this.vendor.getInventoryList().size(true);
        int item_i;
        for (; ; ) {
            try {
                item_i = SelectController.getSelection(0, vendorSize + this.getHerosInventorySize());
                break;
            } catch (InformationRequest r) {
                HeroView.show(this.hero);
                ItemView.show(hero.getInventoryList(), 0, true);
                KeyController.enterToContinue();
            } catch (HelpInstructionRequest r) {
                // Nothing;
            }
            MarketView.showAllSelections(this.vendor, this.hero);
            MarketView.promptInputHint();
        }

        // Exchange the gold and product.
        if (item_i < vendorSize) {
            this.buy(item_i);
        } else {
            this.sell(item_i - vendorSize);
        }
    }

    private int getHerosInventorySize() {
        return this.hero.getInventoryList().size(true);
    }

    private int addItemToTargetList(InventoryType type, Vendor vendor, Hero buyer, int item_i) {
        if (item_i < 0) {
            return (-1);
        }
        ArrayList<Inventory> list = vendor.getInventoryList().get(type);
        if (list.size() > 0 && item_i <= list.size()) {
            Inventory item = list.get(item_i);
            if (item instanceof ISellable && this.transaction((ISellable) item, buyer)) {
                buyer.getInventoryList().add(type, item);
                return (-1);
            }
        }
        return (item_i - list.size());
    }

    private void buy(int selected_item_index) {
        // Get who is the buyer.
        Hero buyer = this.hero;
        HeroView.showBuy(buyer, selected_item_index);

        selected_item_index = addItemToTargetList(InventoryType.WEAPON, vendor, buyer, selected_item_index);
        selected_item_index = addItemToTargetList(InventoryType.ARMOR, vendor, buyer, selected_item_index);
        selected_item_index = addItemToTargetList(InventoryType.SPELL, vendor, buyer, selected_item_index);
        addItemToTargetList(InventoryType.POTION, vendor, buyer, selected_item_index);
    }

    private boolean transaction(ISellable item, Hero buyer) {
        if (item == null || buyer.getGold() < item.getPrice()) {
            MarketView.promptInvalidBuy(buyer);
            return false;
        }

        for (; ; ) {
            MarketView.showBuyConfirmationQuery(buyer.getName(), item);
            try {
                IInputs confirmation = KeyController.getKey(Inputs.Type.TRADE);
                if (TradeConfirmationInputs.NO.equals(confirmation)) {
                    return false;
                } else if (TradeConfirmationInputs.YES.equals(confirmation)) {
                    break;
                } else {
                    Logger.warning(INVALID_CONFIRM);
                }
            } catch (InformationRequest r) {
                HeroView.show(this.hero);
                ItemView.show(hero.getInventoryList(), 0, true);
            } catch (MapDisplayRequest r) {
                MapView.show(currentMap);
            } catch (HelpInstructionRequest r) {
                MarketView.showBuyConfirmationQuery(buyer.getName(), item);
            }
        }
        buyer.setGold(buyer.getGold() - item.getPrice());
        return true;
    }

    private int removeItemFromTargetList(InventoryType type, Hero seller, int item_i) {
        if (item_i < 0) {
            return (-1);
        }

        ArrayList<Inventory> list = seller.getInventoryList().get(type);
        if (list.size() > 0 && item_i < list.size()) {
            Inventory item = list.get(item_i);
            if (item instanceof ISellable) {
                HeroView.showSell(seller, ((ISellable) item).getName());
                for (; ; ) {
                    MarketView.showSellConfirmationQuery(seller.getName(), (ISellable) item);
                    try {
                        IInputs confirmation = KeyController.getKey(Inputs.Type.TRADE);
                        if (TradeConfirmationInputs.NO.equals(confirmation)) {
                            return (-1);
                        } else if (TradeConfirmationInputs.YES.equals(confirmation)) {
                            break;
                        } else {
                            Logger.warning(INVALID_CONFIRM);
                        }
                    } catch (InformationRequest r) {
                        HeroView.show(this.hero);
                        ItemView.show(hero.getInventoryList(), 0, true);
                    } catch (MapDisplayRequest r) {
                        MapView.show(currentMap);
                    } catch (HelpInstructionRequest r) {
                        HeroView.showSell(seller, ((ISellable) item).getName());
                    }
                }
                seller.getInventoryList().remove(type, item);
                int secondHandPrice = MarketFormulae.getSecondHandPrice(((ISellable) item).getPrice());
                seller.addGold(secondHandPrice);
                vendor.getInventoryList().add(type, item);
            }
        }
        return (item_i - list.size());
    }

    private void sell(int selected_item_index) {
        Hero seller = hero;
        selected_item_index = removeItemFromTargetList(InventoryType.WEAPON, seller, selected_item_index);
        selected_item_index = removeItemFromTargetList(InventoryType.ARMOR, seller, selected_item_index);
        selected_item_index = removeItemFromTargetList(InventoryType.SPELL, seller, selected_item_index);
        removeItemFromTargetList(InventoryType.POTION, seller, selected_item_index);
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
