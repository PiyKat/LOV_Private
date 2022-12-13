package src.java.modules;

import src.java.modules.character.hero.Hero;

/**
 * Player includes player's information e.g. name, age, email, password, etc.
 */
public class Player {
    private Hero character;
    private boolean isAdult;

    public Player() {
    }

    public Hero getCharacter() {
        return character;
    }

    public void setCharacter(Hero character) {
        this.character = character;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }
}
