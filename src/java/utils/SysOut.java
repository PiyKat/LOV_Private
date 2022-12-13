package src.java.utils;

/**
 * Wrap the standard output for players in case the game is going to output to a GUI.
 */
public class SysOut {
    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void printf(String s) {
        System.out.printf(s);
    }
}
