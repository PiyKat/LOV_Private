package src.java.modules.spaces;

/**
 * Module of CommonSpace with its properties/attributes getter and setter.
 */
public abstract class CommonSpace implements ISpace {
    private final int x, y;

    public CommonSpace(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BuffType getBuff() {
        return null;
    }

    @Override
    public boolean hasVendor() {
        return false;
    }
}
