package src.java.controllers;

import src.java.exceptions.requests.HelpInstructionRequest;
import src.java.exceptions.requests.InformationRequest;
import src.java.exceptions.requests.MapDisplayRequest;
import src.java.exceptions.requests.QuitRequest;
import src.java.modules.inputs.GlobalInputs;
import src.java.views.InputsView;

/**
 * Handling global inputs
 */
public class GlobalRequestHandler {
    /**
     * @param s the user input.
     * @throws QuitRequest if the input is a quit request.
     */
    private static void preCheckQuit(String s) throws QuitRequest {
        if (s.startsWith(GlobalInputs.QUIT.toString())) {
            InputsView.show(GlobalInputs.QUIT);
            throw new QuitRequest();
        }
    }

    /**
     * @param s the user input
     * @throws InformationRequest if the input is an information request.
     */
    private static void preCheckInformation(String s) throws InformationRequest {
        if (s.startsWith(GlobalInputs.INFORMATION.toString())) {
            InputsView.show(GlobalInputs.INFORMATION);
            throw new InformationRequest();
        }
    }

    /**
     * Pre-check the user input before any further actions.
     *
     * @param s the user input
     * @throws QuitRequest            if the input is a quit request.
     * @throws InformationRequest     if the input is an information request.
     * @throws MapDisplayRequest      if the input is a map display request.
     * @throws HelpInstructionRequest if the input is a help request.
     */
    public static void preCheckRequest(String s) throws QuitRequest, InformationRequest, MapDisplayRequest, HelpInstructionRequest {
        s = s.toUpperCase();
        preCheckQuit(s);
        preCheckInformation(s);
        preCheckMapDisplay(s);
        preCheckHelpDisplay(s);
    }

    private static void preCheckMapDisplay(String s) throws MapDisplayRequest {
        if (s.startsWith(GlobalInputs.MAP.toString())) {
            InputsView.show(GlobalInputs.MAP);
            throw new MapDisplayRequest();
        }
    }

    private static void preCheckHelpDisplay(String s) throws HelpInstructionRequest {
        if (s.startsWith(GlobalInputs.HELP.toString())) {
            InputsView.show(GlobalInputs.HELP);
            throw new HelpInstructionRequest();
        }
    }
}
