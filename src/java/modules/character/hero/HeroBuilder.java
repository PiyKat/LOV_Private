package src.java.modules.character.hero;

import src.java.controllers.SelectController;
import src.java.exceptions.requests.QuitRequest;
import src.java.utils.Logger;
import src.java.utils.ResourceParser;
import src.java.utils.SysOut;
import src.java.views.SelectionView;

import java.util.ArrayList;

import static src.java.views.Messages.NO_HERO;
import static src.java.views.Messages.SELECT_HERO;

/**
 * Load initial data from setting files for heros.
 */
public class HeroBuilder {
    private final static String PALADINS_SETTING = "./data/heros/Paladins.txt";
    private final static String SORCERERS_SETTING = "./data/heros/Sorcerers.txt";
    private final static String WARRIORS_SETTING = "./data/heros/Warriors.txt";

    private static final String NAME_KEY = "Name";
    private static final String MANA_KEY = "mana";
    private static final String STR_KEY = "strength";
    private static final String AGI_KEY = "agility";
    private static final String DEX_KEY = "dexterity";
    private static final String MONEY_KEY = "starting money";
    private static final String EXP_KEY = "starting experience";
    private static final ArrayList<Hero> heroList = new ArrayList<>();

    public HeroBuilder() {
        this.loadHeros();
    }

    private void loadHeros() {
        // Load Paladins
        ResourceParser parser = new ResourceParser(HeroBuilder.PALADINS_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            heroList.add(new Paladin(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, EXP_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MANA_KEY)),
                    Integer.parseInt(parser.getRowValue(i, STR_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DEX_KEY)),
                    Integer.parseInt(parser.getRowValue(i, AGI_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MONEY_KEY))
            ));
        }
        // Load Sorcerers
        parser = new ResourceParser(HeroBuilder.SORCERERS_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            heroList.add(new Sorcerer(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, EXP_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MANA_KEY)),
                    Integer.parseInt(parser.getRowValue(i, STR_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DEX_KEY)),
                    Integer.parseInt(parser.getRowValue(i, AGI_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MONEY_KEY))
            ));
        }
        // Load Warriors
        parser = new ResourceParser(HeroBuilder.WARRIORS_SETTING);
        for (int i = 0; i < parser.getRowCount(); i++) {
            heroList.add(new Warrior(
                    parser.getRowValue(i, NAME_KEY),
                    Integer.parseInt(parser.getRowValue(i, EXP_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MANA_KEY)),
                    Integer.parseInt(parser.getRowValue(i, STR_KEY)),
                    Integer.parseInt(parser.getRowValue(i, DEX_KEY)),
                    Integer.parseInt(parser.getRowValue(i, AGI_KEY)),
                    Integer.parseInt(parser.getRowValue(i, MONEY_KEY))
            ));
        }
    }

    /**
     * @return the selected hero for the player
     * @throws QuitRequest if the user wants to quit.
     */
    public Hero selectPlayer(String hint) throws QuitRequest {
        if (heroList.size() == 0) {
            Logger.warning(NO_HERO);
            return null;
        }
        SysOut.println(hint);
        SysOut.println(SELECT_HERO);
        SelectionView.showList(heroList, 0);

        int hero_i = SelectController.getSelection(0, heroList.size());
        Hero selectedHero = heroList.get(hero_i);
        heroList.remove(hero_i);
        return selectedHero;
    }
}
