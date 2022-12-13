package src.java.modules.spaces;

public class MonsterNexus extends NexusSpace {
    public MonsterNexus(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean hasVendor() {
        return false;
    }
}
