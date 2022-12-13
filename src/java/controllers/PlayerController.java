package src.java.controllers;

import src.java.exceptions.requests.HelpInstructionRequest;
import src.java.exceptions.requests.InformationRequest;
import src.java.exceptions.requests.MapDisplayRequest;
import src.java.exceptions.requests.QuitRequest;
import src.java.modules.Player;
import src.java.utils.Logger;
import src.java.views.PlayerView;

import static src.java.views.Messages.*;
/**
 * Get confirmation from the user that who plays the game is over 18.
 */
public class PlayerController {
    /**
     * @param player the player object to record this response.
     */
    public static void queryAge(Player player) {
        PlayerView.showAgeQuery();
        for (; ; ) {
            try {
                if (KeyController.confirmationQuery()) {
                    player.setAdult(true);
                    return;
                } else {
                    Logger.warning(ADULT_ONLY);
                    throw new QuitRequest();
                }
            } catch (HelpInstructionRequest | InformationRequest | MapDisplayRequest r) {
                PlayerView.showAgeQuery();
            }
        }
    }
}
