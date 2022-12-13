package src.java.views;

import src.java.modules.character.monster.Monster;
import src.java.modules.map.Position;
import src.java.utils.SysOut;

import static src.java.views.Messages.*;

/**
 * This view aggregates all display related to monsters.
 */
public class MonsterView {
    public static void displayWelcome(Position position) {
        SysOut.println(String.format("`" + MONSTER_GREETING + "` " + MONSTER_BORN, position));
    }

    public static void displayBye() {
        SysOut.println("`" + MONSTER_FAREWELL + "` " + BATTLE_END);
    }

    public static String toString(Monster monster) {
        return String.format("%-18s HP %-3d Lv.%-2d | DMG %3d | DEF %1d | DOG %3d",
                monster.getName(), monster.getHP(), monster.getLevel(), monster.getDamage(),
                monster.getDefense(), monster.getDodgeRatio()
        );
    }

    public static void showMoveForward(Monster monster) {
        SysOut.print(monster.getName() + " moves forward." + SpaceViewType.MONSTER);
        MapView.showPosition(monster);
    }
}
