package src.java.controllers;

import src.java.exceptions.requests.HelpInstructionRequest;
import src.java.exceptions.requests.InformationRequest;
import src.java.exceptions.requests.MapDisplayRequest;
import src.java.exceptions.requests.QuitRequest;
import src.java.modules.inputs.ConfirmationInputs;
import src.java.modules.inputs.GlobalInputs;
import src.java.modules.inputs.IInputs;
import src.java.modules.inputs.Inputs;
import src.java.utils.Logger;
import src.java.utils.SysOut;

import java.util.Scanner;

import static src.java.views.Messages.INVALID_CONFIRM;

/**
 * Process users' key inputs
 */


public class KeyController {
    /**
     * @param Scanner scanner: for reading the stdin
     */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Detect keyboard inputs
     *
     * @param type the input type of interest. This is to prevent key collision
     * @return a detected key input
     */
    public static IInputs getKey(Inputs.Type type) {
        String input = scanner.nextLine();
        input = input.toUpperCase();
        IInputs result = matchKey(input, type);

        // Check global keys
        if (result == null) {
            result = matchKey(input, Inputs.Type.GLOBAL);
            if (result instanceof GlobalInputs) {
                if (GlobalInputs.QUIT.equals(result)) {
                    throw new QuitRequest();
                }
                if (GlobalInputs.HELP.equals(result)) {
                    throw new HelpInstructionRequest();
                }
                if (GlobalInputs.INFORMATION.equals(result)) {
                    throw new InformationRequest();
                }
                if (GlobalInputs.MAP.equals(result)) {
                    throw new MapDisplayRequest();
                }
            }
        }
        return result;
    }

    public static IInputs getKey() {
        for (; ; ) {
            String input = scanner.nextLine();
            input = input.toUpperCase().trim();
            if (input.length() == 0) {
                Logger.warning("Please enter a valid key.");
                continue;
            }
            GlobalRequestHandler.preCheckRequest(input);
            for (Inputs.Type type : Inputs.Type.values()) {
                IInputs result = matchKey(input, type);
                if (result != null) {
                    return result;
                }
            }
            Logger.warning("Please enter a valid key.");
        }
    }

    private static IInputs matchKey(String input, Inputs.Type type) {
        for (IInputs key : Inputs.get(type)) {
            if (input.startsWith(key.toString())) {
                return key;
            }
        }
        return null;
    }

    public static void enterToContinue() {
        SysOut.print("Type <Enter> to continue.");
        scanner.nextLine();
    }

    public static boolean confirmationQuery() {
        for (; ; ) {
            IInputs confirmation = KeyController.getKey(Inputs.Type.CONFIRMATION);
            if (ConfirmationInputs.YES.equals(confirmation)) {
                return true;
            } else if (ConfirmationInputs.NO.equals(confirmation)) {
                return false;
            } else {
                Logger.warning(INVALID_CONFIRM);
            }
        }
    }
}
