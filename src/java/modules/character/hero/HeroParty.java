package src.java.modules.character.hero;

import java.util.ArrayList;

/**
 * Module of HeroParty with its getter and setter.
 */
public class HeroParty {

    /* Heros in the part */
    private final ArrayList<Hero> heros;

    private int leaderIndex;

    public HeroParty() {
        this.heros = new ArrayList<>();
        this.leaderIndex = -1;
    }

    public void addHero(Hero hero) {
        this.heros.add(hero);
    }

    public boolean hasHero() {
        return this.heros.size() > 0;
    }

    public Hero getLeader() {
        return this.heros.get(this.leaderIndex);
    }

    public void setLeader(int index) {
        this.leaderIndex = index;
    }

    public ArrayList<Hero> getHeros() {
        return this.heros;
    }
}
