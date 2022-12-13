package src.java.views;

import src.java.modules.inputs.IInputs;
import src.java.utils.Logger;

import static src.java.views.Messages.GET_INPUT_FORMAT;

/**
 * Define the format to display user input.
 */
public class InputsView {
    public static void show(IInputs input) {
        Logger.info(String.format(GET_INPUT_FORMAT, input));
    }
}
