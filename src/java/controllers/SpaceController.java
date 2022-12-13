package src.java.controllers;

import src.java.modules.character.hero.Hero;
import src.java.modules.spaces.*;
import src.java.utils.Logger;

/**
 * Add or remove the CellType buffs on heroes
 */
public class SpaceController {

    private static final double REWARD_RATIO = 1.1;

    public static void enterSpace(ISpace space, Hero hero) {
        Logger.debug(hero.getName() + " gets into " + space.getType());
        BuffType buff = getSpaceBuff(space);
        if (buff == null) {
            return;
        }

        switch (buff) {
            case AGILITY:
                hero.addAgility(getIncrement(hero.getAgility()));
                break;
            case DEXTERITY:
                hero.addDexterity(getIncrement(hero.getDexterity()));
                break;
            case STRENGTH:
                hero.addStrength(getIncrement(hero.getStrength()));
                break;
        }
    }

    public static void leaveSpace(ISpace space, Hero hero) {
        Logger.debug(hero.getName() + " leaves " + space.getType());
        BuffType buff = getSpaceBuff(space);
        if (buff == null) {
            return;
        }

        switch (buff) {
            case AGILITY:
                hero.addAgility(reverseIncrement(hero.getAgility()));
                break;
            case DEXTERITY:
                hero.addDexterity(reverseIncrement(hero.getDexterity()));
                break;
            case STRENGTH:
                hero.addStrength(reverseIncrement(hero.getStrength()));
                break;
        }
    }

    private static int getIncrement(int original_value) {
        return (int) (original_value * (REWARD_RATIO - 1));
    }

    private static int reverseIncrement(int new_value) {
        return (int) ((new_value / REWARD_RATIO) - new_value);
    }

    private static BuffType getSpaceBuff(ISpace space) {
        if (space instanceof BushSpace) {
            return ((BushSpace) space).getBuff();
        } else if (space instanceof CaveSpace) {
            return ((CaveSpace) space).getBuff();
        } else if (space instanceof KoulouSpace) {
            return ((KoulouSpace) space).getBuff();
        }
        return null;
    }
}
