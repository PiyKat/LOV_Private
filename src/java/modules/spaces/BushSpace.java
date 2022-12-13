package src.java.modules.spaces;

import src.java.views.SpaceViewType;


public class BushSpace extends CommonSpace {
    private static final BuffType BONUS = BuffType.DEXTERITY;

    //public static double REWARD = 1.1;
    public BushSpace(int x, int y) {
        super(x, y);
    }

    @Override
    public SpaceViewType getType() {
        return SpaceViewType.BUSH;
    }

    @Override
    public BuffType getBuff() {
        return BONUS;
    }
}
