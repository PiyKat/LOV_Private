package src.java.views;

import src.java.formulae.MarketFormulae;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.hero.HeroParty;
import src.java.modules.character.vendor.Vendor;
import src.java.modules.items.ISellable;
import src.java.utils.Logger;
import src.java.utils.SysOut;

import static src.java.views.Messages.*;

/**
 * Define the format to display market and transaction stages.
 */
public class MarketView {
    public static void promptInputHint() {
        SysOut.println(MARKET_HELP);
        SysOut.print(TRADE_QUERY);
    }

    public static void promptBuyerHint(HeroParty heroParty) {
        SelectionView.showList(heroParty.getHeros(), 0);
        SysOut.print(BUYER_QUERY);
    }

    public static void promptInvalidBuy(Hero buyer) {
        SysOut.println(INVALID_BUY);
        Logger.info(HeroView.toString(buyer));
    }

    public static void showAllSelections(Vendor vendor, HeroParty heroParty) {
        SysOut.println("================Vendor's Table======================");
        ItemView.show(vendor.getInventoryList(), 0, true);
        int selection_shift = vendor.getInventoryList().size(true);

        for (Hero hero : heroParty.getHeros()) {
            SysOut.println("=================== " + hero.getName() + "'s Bag========================");
            ItemView.show(hero.getInventoryList(), selection_shift, true);
            selection_shift += hero.getInventoryList().size(true);
        }
        SysOut.println(SEPARATOR_LINE);
    }

    public static void showAllSelections(Vendor vendor, Hero hero) {
        SysOut.println("================Vendor's Table======================");
        ItemView.show(vendor.getInventoryList(), 0, true);
        int selection_shift = vendor.getInventoryList().size(true);

        SysOut.println("=================== " + hero.getName() + "'s Bag========================");
        ItemView.show(hero.getInventoryList(), selection_shift, true);
        SysOut.println(SEPARATOR_LINE);
    }

    public static void showBuyConfirmationQuery(String name, ISellable item) {
        SysOut.print(String.format(BUY_CONFIRM, name, item.getName(), item.getPrice()));
    }

    public static void showSellConfirmationQuery(String hero_name, ISellable item) {
        SysOut.print(String.format(SELL_CONFIRM, hero_name, item.getName(),
                MarketFormulae.getSecondHandPrice(item.getPrice())));
    }
}
