package src.java.modules.spaces;

import src.java.views.SpaceViewType;

/**
 * Module of InaccessibleSpace with its properties/attributes getter and setter.
 */
public class InaccessibleSpace implements ISpace, IInAccessible {
    private final int x, y;

    public InaccessibleSpace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // For testing
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Override
    public SpaceViewType getType() {
        return SpaceViewType.INACCESSIBLE;
    }

    @Override
    public boolean hasVendor() {
        return false;
    }
}