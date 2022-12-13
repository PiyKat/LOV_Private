package src.java.modules.spaces;

import src.java.views.SpaceViewType;

public abstract class NexusSpace implements ISpace {
    private final int x, y;

    public NexusSpace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // For testing
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Override
    public SpaceViewType getType() {
        return SpaceViewType.NEXUS;
    }
}
