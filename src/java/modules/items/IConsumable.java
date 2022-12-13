package src.java.modules.items;

import src.java.modules.character.hero.Hero;

/**
 * Interface of consumable items with common public functions.
 */
public interface IConsumable {
    void drink(Hero target);
}