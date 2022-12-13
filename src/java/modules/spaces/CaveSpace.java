package src.java.modules.spaces;

import src.java.views.SpaceViewType;


public class CaveSpace extends CommonSpace {
    private static final BuffType BONUS = BuffType.AGILITY;

    //public static double REWARD = 1.1;
    public CaveSpace(int x, int y) {
        super(x, y);
    }

    @Override
    public SpaceViewType getType() {
        return SpaceViewType.CAVE;
    }

    @Override
    public BuffType getBuff() {
        return BONUS;
    }
}
