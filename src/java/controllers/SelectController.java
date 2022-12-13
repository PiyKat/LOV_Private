package src.java.controllers;

import src.java.exceptions.requests.QuitRequest;
import src.java.utils.Logger;

import java.util.Scanner;

/**
 * Handle alpahabet-based selection inputs
 */
public class SelectController {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @param lower_bound min index (included) to enter.
     * @param upper_bound max index (not included) to enter.
     * @return the index of user's selection.
     * @throws QuitRequest if the user wants to quit.
     */
    public static int getSelection(int lower_bound, int upper_bound) throws QuitRequest {
        if (upper_bound <= 0) {
            return 0;
        }

        for (; ; ) {
            String s = scanner.nextLine();
            GlobalRequestHandler.preCheckRequest(s);
            try {
                int keyIn = Integer.parseInt(s.trim());
                if (keyIn < upper_bound && keyIn >= lower_bound) {
                    return keyIn;
                } else {
                    Logger.warning("Please enter a number from " + lower_bound + " to " + (upper_bound - 1) + ".");
                }
            } catch (NumberFormatException e) {
                Logger.warning("Please enter a number from " + lower_bound + " to " + (upper_bound - 1) + ".");
            }
        }
    }
}
