package src.java.views;

import src.java.modules.character.Role;
import src.java.modules.map.Map;
import src.java.modules.spaces.IInAccessible;
import src.java.modules.spaces.ISpace;
import src.java.utils.SysOut;

import static src.java.views.Messages.MAP_QUERY;
import static src.java.views.SpaceViewType.*;

/**
 * Define the format to display map and moving stages.
 */
public class MapView {
    private static final int ELEMENTS_IN_CELL = 3;

    public static void showKeyQuery() {
        SysOut.println(MAP_QUERY);
    }

    private static String getFigureCaption() {
        return HERO + ": hero positions.   " + MONSTER + ": monster positions. \n" +
                NEXUS + ": Nexus.   " + INACCESSIBLE + ": inaccessible forests. \n" +
                BUSH + ": bush.   " + CAVE + ": cave.   " + KOULOU + ": Koulou. \n";
    }

    private static String getRowIndex(Map map) {
        StringBuilder s = new StringBuilder();
        String SPACES = "   ";
        s.append(SPACES);
        for (int col = 0; col < map.getWidth(); col++, s.append(SPACES)) {
            s.append(SPACES);
            s.append(col);
        }
        s.append("\n");
        return s.toString();
    }

    private static String getRowLine(Map map) {
        StringBuilder s = new StringBuilder();
        s.append("  +"); // left borderline
        StringBuilder cellBorder = new StringBuilder();
        for (int i = 0; i < EMPTY.toString().length() * ELEMENTS_IN_CELL; i++) {
            cellBorder.append("-");
        }
        for (int col = 0; col < map.getWidth(); col++, s.append("+")) {
            s.append(cellBorder);
        }
        s.append("\n");
        return s.toString();
    }

    private static String getRowSpaces(int row, Map map) {
        StringBuilder s = new StringBuilder();
        s.append(getRowIndexFormat(row));
        s.append("|"); // left borderline
        for (int col = 0; col < map.getWidth(); col++, s.append("|")) {
            ISpace cell = map.getSpace(row, col);
            if (cell == null) {
                s.append(EMPTY);
            } else {
                if (map.hasHero(col, row)) {
                    s.append(HERO);
                } else if (cell instanceof IInAccessible) {
                    s.append(INACCESSIBLE);
                } else {
                    s.append("  ");
                }

                s.append(cell.getType());

                if (map.hasMonster(col, row)) {
                    s.append(MONSTER);
                } else if (cell instanceof IInAccessible) {
                    s.append(INACCESSIBLE);
                } else {
                    s.append("  ");
                }

            }
        }
        s.append("\n");
        return s.toString();
    }

    public static void show(Map map) {
        if (map == null) {
            throw new RuntimeException("Please create a map first");
        }
        StringBuilder s = new StringBuilder();
        s.append("\n");
        s.append(getFigureCaption());
        s.append(getRowLine(map)); // Top borderline
        for (int i = 0; i < map.getHeight(); i++, s.append(getRowLine(map))) {
            s.append(getRowSpaces(i, map));
        }
        s.append(getRowIndex(map));
        SysOut.println(s);
    }

    public static void showPosition(Role role) {
        SysOut.println(
                String.format("Current position: [%02d][%d], Spawn position: [%02d][%d]",
                        role.getCurrentPosition().getY(), role.getCurrentPosition().getX(),
                        role.getSpawnPosition().getY(), role.getSpawnPosition().getX()
                )
        );
    }

    public static String getRowIndexFormat(int row) {
        return String.format("%02d", row);
    }
}
