package src.java.controllers;

import src.java.exceptions.HerosReach;
import src.java.exceptions.MonstersReach;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.monster.Monster;

/**
 * Handles the Nexus CellType interaction
 * vendor: sell the goods to heroes or buy stuffs from them
 * hero: shoppers or sellers
 * currentMap: current map
 */

public class NexusController {
    public static void enterHeroNexus(Monster monster) {
        throw new MonstersReach();
    }

    public static void enterMonsterNexus(Hero hero) {
        throw new HerosReach();
    }
}
