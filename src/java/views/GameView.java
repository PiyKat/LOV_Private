package src.java.views;

import src.java.utils.SysOut;

import static src.java.views.Messages.*;

/**
 * Class that prints various messages in gameplay
 */
public class GameView {
    public static void welcome() {
        SysOut.println(WELCOME);
        HeroView.showHelpInstruction();
    }

    public static void end() {
        SysOut.println(FAREWELL);
    }

    public static void restartQuery() {
        SysOut.println(RESTART_QUERY);
    }

    public static void showPause(String name) {
        SysOut.println(String.format(PAUSE_STATUS, name));
    }

    public static void heroWin() {
        SysOut.println(HERO_WIN);
    }

    public static void monsterWin() {
        SysOut.println(MONSTER_WIN);
    }
}
