package src.java.views;

import src.java.utils.SysOut;

import java.util.ArrayList;

/**
 * Define how to display any kind of list, either array or ArrayList.
 */
public class SelectionView {
    public static <T> void showList(ArrayList<T> list) {
        showList(list, 0);
    }

    /**
     * @param list to display in the selections.
     * @param <T>  any type of ArrayList
     */
    public static <T> void showList(ArrayList<T> list, int starting_number) {
        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            SysOut.println(String.format("%2d) ", (starting_number + i)) + list.get(i));
        }
    }

    /**
     * @param list to display in the selections.
     */
    public static <T> void showList(T[] list, int starting_number) {
        for (int i = 0; i < list.length; i++) {
            SysOut.println(String.format("%2d) ", (starting_number + i)) + list[i]);
        }
    }
}
