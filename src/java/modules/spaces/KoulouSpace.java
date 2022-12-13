package src.java.modules.spaces;

import src.java.views.SpaceViewType;

import static src.java.modules.spaces.BuffType.STRENGTH;

public class KoulouSpace extends CommonSpace {
    private final static BuffType BONUS = STRENGTH;

    public KoulouSpace(int x, int y) {
        super(x, y);
    }

    @Override
    public SpaceViewType getType() {
        return SpaceViewType.KOULOU;
    }

    @Override
    public BuffType getBuff() {
        return BONUS;
    }
}
