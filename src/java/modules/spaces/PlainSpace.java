package src.java.modules.spaces;

import src.java.views.SpaceViewType;

public class PlainSpace extends CommonSpace {
    public PlainSpace(int x, int y) {
        super(x, y);
    }

    @Override
    public SpaceViewType getType() {
        return SpaceViewType.PLAIN;
    }
}
