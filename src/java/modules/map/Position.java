package src.java.modules.map;

import src.java.views.MapView;

/**
 * Class of position for any character to use.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void shiftRight() {
        this.x = this.x + 1;
    }

    public void shiftLeft() {
        this.x = this.x - 1;
    }

    public void shiftUp() {
        this.y = this.y - 1;
    }

    public void shiftDown() {
        this.y = this.y + 1;
    }

    @Override
    public String toString() {
        return String.format("[%s][%d]", MapView.getRowIndexFormat(this.y), this.x);
    }
}
