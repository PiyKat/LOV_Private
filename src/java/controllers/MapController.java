package src.java.controllers;

import src.java.exceptions.InvalidMoveException;
import src.java.exceptions.requests.EnterMarketRequest;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.vendor.Vendor;
import src.java.modules.character.vendor.VendorFactory;
import src.java.modules.inputs.DirectionInputs;
import src.java.modules.inputs.IInputs;
import src.java.modules.map.Map;
import src.java.modules.map.Position;
import src.java.modules.spaces.ISpace;
import src.java.utils.Logger;
import src.java.views.HeroView;
import src.java.views.InputsView;
import src.java.views.MapView;
import src.java.views.SelectionView;

import java.util.ArrayList;

import static src.java.views.Messages.INVALID_MARKET;

/**
 * MapController is an entry point of markets, battles, and equipment menu, consisting of other controllers.
 * Handle the interaction between heroes, monssters, and markets.
 * marketController: to control the market activity.
 * battleController: to handle the battle on the map.
 * equipmentController: to handle the equipment switching.
 * map: to describe the world map.
 * heroParty: group of heroes that the player controls
 * spaceFormula: to determine the monster appearing ratio.
 * mapView: visualize the map.
 */
public class MapController {
    private static final int RECALL_DELAY = 1;
    private static final int TELEPORT_DELAY = 1;
    private static Map map;
    /**
     * Handle the interaction between heroes, monssters, and markets.
     * marketController: to control the market activity.
     * battleController: to handle the battle on the map.
     * equipmentController: to handle the equipment switching.
     * map: to describe the world map.
     * heroParty: group of heroes that the player controls
     * spaceFormula: to determine the monster appearing ratio.
     * mapView: visualize the map.
     */
    private final VendorController vendorController = new VendorController(); // Changes made here

    public static boolean reachHeroNexus(Position position) {
        return position.getY() == map.getHeight() - 1;
    }

    public static double getDistance(Position a, Position b) {
        return Math.sqrt(Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getX() - b.getX(), 2));
    }

    public static void showMap() {
        MapView.show(map);
    }

    public void heroMoveTo(IInputs direction, Hero hero) {
        InputsView.show(direction);
        ISpace original_space = map.getSpace(hero.getCurrentPosition());
        if (DirectionInputs.RECALL.equals(direction)) {
            if (!map.isValidPosition(hero.getSpawnPosition())) {
                throw new InvalidMoveException();
            }
            hero.recallToSpawn();
            hero.setPauseTurns(RECALL_DELAY);
        } else if (DirectionInputs.TELEPORT.equals(direction)) {
            // Display all available position selection (right, left, down of others).
            ArrayList<Position> possibleDestinations = map.getAvailableTeleports(hero.getCurrentPosition());
            if (possibleDestinations.size() == 0) {
                throw new InvalidMoveException();
            }
            HeroView.showTeleportQuery();
            SelectionView.showList(possibleDestinations);
            int i = SelectController.getSelection(0, possibleDestinations.size());
            hero.setCurrentPosition(possibleDestinations.get(i));
            hero.setPauseTurns(TELEPORT_DELAY);
        } else if (DirectionInputs.MARKET.equals(direction)) {
            throw new EnterMarketRequest();
        } else if (DirectionInputs.UP.equals(direction)) {
            Position heroPosition = hero.getCurrentPosition();
            if (!map.isValidPosition(heroPosition.getX(), heroPosition.getY() - 1)) {
                throw new InvalidMoveException();
            }
            if (!map.isValidPosition(heroPosition.getX(), heroPosition.getY() - 1)) {
                throw new InvalidMoveException();
            }
            heroPosition.shiftUp();
            if (this.reachMonsterNexus(heroPosition)) {
                NexusController.enterMonsterNexus(hero);
            }
        } else if (DirectionInputs.DOWN.equals(direction)) {
            Position heroPosition = hero.getCurrentPosition();
            if (!map.isValidPosition(heroPosition.getX(), heroPosition.getY() + 1)) {
                throw new InvalidMoveException();
            }
            heroPosition.shiftDown();
        } else if (DirectionInputs.LEFT.equals(direction)) {
            Position heroPosition = hero.getCurrentPosition();
            if (!map.isValidPosition(heroPosition.getX() - 1, heroPosition.getY())) {
                throw new InvalidMoveException();
            }
            heroPosition.shiftLeft();
        } else if (DirectionInputs.RIGHT.equals(direction)) {
            Position heroPosition = hero.getCurrentPosition();
            if (!map.isValidPosition(heroPosition.getX() + 1, heroPosition.getY())) {
                throw new InvalidMoveException();
            }
            heroPosition.shiftRight();
        } else {
            throw new InvalidMoveException();
        }
        SpaceController.leaveSpace(original_space, hero);
        SpaceController.enterSpace(map.getSpace(hero.getCurrentPosition()), hero);
        HeroView.showMove(hero, direction);
    }

    public void enterMarket(Hero hero) {
        if (map.getSpace(hero.getCurrentPosition()).hasVendor()) {
            Vendor vendor = VendorFactory.generateNewVendor();
            this.vendorController.setVendor(vendor);
            this.vendorController.setHero(hero);
            this.vendorController.enter(map);
            MapController.showMap();
        } else {
            Logger.warning(INVALID_MARKET);
        }
    }

    private boolean reachMonsterNexus(Position position) {
        return position.getY() == 0;
    }

    public void setMap(Map map) {
        MapController.map = map;
    }
}
