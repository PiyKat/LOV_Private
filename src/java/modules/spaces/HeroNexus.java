package src.java.modules.spaces;

public class HeroNexus extends NexusSpace {
    public HeroNexus(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean hasVendor() {
        return true;
    }
}
