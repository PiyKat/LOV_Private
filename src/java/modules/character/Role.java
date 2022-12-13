package src.java.modules.character;

import src.java.modules.map.Position;

/**
 * Module of Role as a category parent class.
 */
public class Role {
    private final Position spawnPosition;
    private final Position currentPosition;

    public Role() {
        this.spawnPosition = new Position(-1, -1);
        this.currentPosition = new Position(-1, -1);
    }

    public Position getSpawnPosition() {
        return spawnPosition;
    }

    public void setSpawnPosition(int x, int y) {
        this.spawnPosition.setX(x);
        this.spawnPosition.setY(y);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position p) {
        this.currentPosition.setX(p.getX());
        this.currentPosition.setY(p.getY());
    }

    public void setCurrentPosition(int x, int y) {
        this.currentPosition.setX(x);
        this.currentPosition.setY(y);
    }
}
