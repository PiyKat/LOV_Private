package src.java.modules.map;

import src.java.modules.character.RoleType;
import src.java.modules.spaces.*;
import src.java.utils.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Map generates all spaces
 */
public class Map {
    private static final int INACCESSIBLE_WIDTH = 1;
    private static final double PLAIN_RATIO = 0.4;
    private static final int MONSTER_NEXUS_HEIGHT = 1;
    private static final int HERO_NEXUS_HEIGHT = 1;
    private final ISpace[][] spaces;
    private final Lane[] lanes;
    private final int width, height;
    private final ArrayList<Position> heroPositions = new ArrayList<>();
    private final ArrayList<Position> monsterPositions = new ArrayList<>();

    public Map(int laneCount, int height) {
        this.height = height;
        this.width = laneCount * Lane.getWidth() + (laneCount - 1) * INACCESSIBLE_WIDTH;
        this.lanes = new Lane[laneCount];
        this.spaces = new ISpace[height][width];
    }

    public void generateValorMap() {
        int x = 0; // represents the column where lanes will be written
        for (int lane = 0; lane < this.lanes.length; lane++) {
            // Code segment to produce lanes

            // When creating a lane, create a list of new celltypes, shuffle it
            List<String> specialPlainList = new ArrayList<>();

            int emptySpaces = Lane.getWidth() * (this.height - (MONSTER_NEXUS_HEIGHT + HERO_NEXUS_HEIGHT));
            int noOfPlainCells = (int)(emptySpaces*PLAIN_RATIO);
            int noOfSpecialCells = emptySpaces - noOfPlainCells;

            // Include plain cells inside the list
            for (int i = 0; i < noOfPlainCells; i++) {
                specialPlainList.add("PLAIN");
            }

            String[] specialCells = {"BUSH", "CAVE", "KOULOU"};
            int counter = 0;

            while (noOfSpecialCells != 0) {
                specialPlainList.add(specialCells[counter]);
                counter = (counter + 1) % 3;
                noOfSpecialCells -= 1;
            }
            Collections.shuffle(specialPlainList);

            // a separate variable for tracking the specialPlainList array
            int k = 0;

            for (int lane_w = 0; lane_w < Lane.getWidth() && x < this.width; lane_w++) {
                // go till intended lane width and generate cells based on height
                for (int y = 0; y < MONSTER_NEXUS_HEIGHT; y++) {
                    // The Monster Nexus row
                    spaces[y][x] = new MonsterNexus(x, y);
                }
                for (int y = MONSTER_NEXUS_HEIGHT; y < this.height - HERO_NEXUS_HEIGHT; y++) {
                    String cellType = specialPlainList.get(k++);
                    switch (cellType) {
                        case "BUSH":
                            spaces[y][x] = new BushSpace(x, y);
                            break;
                        case "CAVE":
                            spaces[y][x] = new CaveSpace(x, y);
                            break;
                        case "KOULOU":
                            spaces[y][x] = new KoulouSpace(x, y);
                            break;
                        default:
                            spaces[y][x] = new PlainSpace(x, y);
                            break;
                    }
                }
                for (int y = this.height - HERO_NEXUS_HEIGHT; y < this.height; y++) {
                    // The Hero Nexus row
                    spaces[y][x] = new HeroNexus(x, y);
                }
                x++;
            }
            // The inaccessible column
            for (int in_w = 0; in_w < INACCESSIBLE_WIDTH && x < this.width; in_w++) {
                for (int y = 0; y < this.height; y++) {
                    spaces[y][x] = new InaccessibleSpace(x, y);
                }
                x++;
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public ISpace getSpace(int y, int x) {
        return this.spaces[y][x];
    }

    public ISpace getSpace(Position position) {
        return this.getSpace(position.getY(), position.getX());
    }

    public boolean isValidPosition(int heroX, int heroY) {
        if (heroX < 0 || heroX >= this.width) {
            return false;
        }
        if (heroY < 0 || heroY >= this.height) {
            return false;
        }
        // Check if any hero occupying the destination
        if (this.hasHero(heroX, heroY)) {
            return false;
        }
        // Check if monster is beside
        if (this.hasMonster(heroX + 1, heroY + 1)
                || this.hasMonster(heroX, heroY + 1)
                || this.hasMonster(heroX - 1, heroY + 1)) {
            return false;
        }
        return !(this.spaces[heroY][heroX] instanceof InaccessibleSpace);
    }

    public boolean isValidMonsterStep(int x, int y) {
        if (x < 0 || x >= this.width) {
            return false;
        }
        if (y < 0 || y >= this.height) {
            return false;
        }
        // Check if any monster occupying the destination
        if (this.hasMonster(x, y)) {
            return false;
        }
        // Check if hero is beside
        if (this.hasHero(x + 1, y - 1)
                || this.hasHero(x, y - 1)
                || this.hasMonster(x - 1, y - 1)
        ) {
            return false;
        }
        return !(this.spaces[y][x] instanceof InaccessibleSpace);
    }

    public boolean isValidPosition(Position position) {
        return this.isValidPosition(position.getX(), position.getY());
    }

    public boolean isValidMonsterStep(Position position) {
        return this.isValidMonsterStep(position.getX(), position.getY());
    }

    public void addHeroPosition(Position position) {
        this.heroPositions.add(position);
    }

    public void addMonsterPosition(Position position) {
        this.monsterPositions.add(position);
    }

    public void removeMonsterPosition(Position position) {
        this.monsterPositions.remove(position);
    }

    public void resetMap() {
        this.heroPositions.clear();
        this.monsterPositions.clear();
    }

    public boolean hasHero(int x, int y) {
        return hasRole(x, y, RoleType.HERO);
    }

    public boolean hasMonster(int x, int y) {
        return hasRole(x, y, RoleType.MONSTER);
    }

    private boolean hasRole(int x, int y, RoleType type) {
        ArrayList<Position> positions;
        switch (type) {
            case HERO:
                positions = this.heroPositions;
                break;
            case MONSTER:
                positions = this.monsterPositions;
                break;
            default:
                return false;
        }

        for (Position position : positions) {
            if (position.getX() == x && position.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public boolean hasHero(Position heroPosition) {
        return this.hasHero(heroPosition.getX(), heroPosition.getY());
    }

    public int getLaneX(int lane_i) {
        return lane_i * (Lane.getWidth() + INACCESSIBLE_WIDTH);
    }

    public ArrayList<Position> getAvailableTeleports(Position heroPosition) {
        ArrayList<Position> availableDestination = new ArrayList<>();
        for (Position position : this.heroPositions) {
            if (position == heroPosition) {
                continue;
            }
            // DOWN
            Position candidate = new Position(position);
            candidate.shiftDown();
            if (isValidPosition(candidate)) {
                Logger.debug("DOWN");
                availableDestination.add(candidate);
            }
            // LEFT
            candidate = new Position(position);
            candidate.shiftLeft();
            if (isValidPosition(candidate)) {
                Logger.debug("LEFT");
                availableDestination.add(candidate);
            }
            // RIGHT
            candidate = new Position(position);
            candidate.shiftRight();
            if (isValidPosition(candidate)) {
                Logger.debug("RIGHT");
                availableDestination.add(candidate);
            }
        }
        return availableDestination;
    }

    public int getLaneCount() {
        return lanes.length;
    }
}
