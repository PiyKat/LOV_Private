package src.java.controllers;

import src.java.exceptions.InvalidActionException;
import src.java.exceptions.InvalidMoveException;
import src.java.exceptions.requests.EnterMarketRequest;
import src.java.exceptions.requests.HelpInstructionRequest;
import src.java.exceptions.requests.InformationRequest;
import src.java.exceptions.requests.MapDisplayRequest;
import src.java.modules.character.hero.Hero;
import src.java.modules.character.hero.HeroBuilder;
import src.java.modules.character.hero.HeroParty;
import src.java.modules.inputs.BattleActionInputs;
import src.java.modules.inputs.DirectionInputs;
import src.java.modules.inputs.IInputs;
import src.java.modules.map.Map;
import src.java.utils.Logger;
import src.java.utils.SysOut;
import src.java.views.BattleView;
import src.java.views.GameView;
import src.java.views.HeroView;
import src.java.views.MapView;

import static src.java.views.Messages.*;

/**
 * This controller supports the heroes' initialization.
 */
public class HeroController {

    private final static int MAX_PARTY_SIZE = 3;
    private final MapController mapController;
    private Map map;
    private HeroParty heroParty;

    public HeroController(MapController mapController) {
        this.mapController = mapController;
    }

    public void makeTeam(HeroBuilder heroBuilder) {
        this.map.resetMap();
        this.heroParty = new HeroParty();
        for (int i = 0; i < MAX_PARTY_SIZE; i++) {
            Hero selectedHero;
            for (; ; ) {
                try {
                    selectedHero = heroBuilder.selectPlayer(EMPTY_STRING);
                    heroParty.addHero(selectedHero);
                    break;
                } catch (InformationRequest r) {
                    HeroView.show(heroParty);
                } catch (HelpInstructionRequest r) {
                    HeroView.showHelpInstruction();
                } catch (MapDisplayRequest r) {
                    MapView.show(map);
                }
                KeyController.enterToContinue();
            }
            // Select lane to spawn
            for (; ; ) {
                try {
                    this.setHeroSpawnPosition(selectedHero, this.map.getLaneCount());
                    HeroView.showSelectedHero(selectedHero);
                    break;
                } catch (InformationRequest r) {
                    HeroView.show(heroParty);
                } catch (HelpInstructionRequest | MapDisplayRequest r) {
                    MapView.show(map);
                }
                KeyController.enterToContinue();
            }
            KeyController.enterToContinue();
        }
        BattleController.setHeros(this.heroParty.getHeros());
        BattleController.setMap(map);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void herosTurn() {
        for (Hero hero : this.heroParty.getHeros()) {
            MapController.showMap();
            this.heroTurn(hero);
            HeroView.endOfTurn();
        }
        Logger.debug("The end of hero's turn.");
    }

    private void heroTurn(Hero hero) {
        int pauseTurnLeft = hero.getPauseTurns();
        if (pauseTurnLeft > 0) {
            GameView.showPause(hero.getName());
            hero.setPauseTurns(pauseTurnLeft - 1);
            return;
        }

        for (boolean endTurn = false; !endTurn; ) {
            // Show all selections.
            HeroView.showHeroPosition(hero);
            HeroView.showActionQuery(hero);
            MapView.showKeyQuery();
            try {
                endTurn = this.heroMove(hero);
            } catch (InvalidMoveException | InvalidActionException e) {
                Logger.warning(INVALID_ACTION);
            } catch (EnterMarketRequest r) {
                this.mapController.enterMarket(hero);
            } catch (InformationRequest r) {
                HeroView.show(heroParty);
            } catch (HelpInstructionRequest r) {
                HeroView.showHelpInstruction();
            } catch (MapDisplayRequest r) {
                MapController.showMap();
            }
        }
    }

    private boolean heroMove(Hero hero) {
        // Get user input
        IInputs action = KeyController.getKey();
        if (action instanceof DirectionInputs) {
            this.mapController.heroMoveTo(action, hero);
            return true;
        } else if (action instanceof BattleActionInputs) {
            BattleController.battle(hero, (BattleActionInputs) action);
            return true;
        } else {
            BattleView.showInvalidAction();
            return false;
        }
    }

    // Select the lane to place the hero
    private void setHeroSpawnPosition(Hero hero, int laneCount) {
        int heroX, heroY = this.map.getHeight() - 1;
        for (; ; ) {
            SysOut.printf(String.format(LANE_QUERY, 0, laneCount - 1));
            int lane_i = SelectController.getSelection(0, laneCount);
            heroX = this.map.getLaneX(lane_i);
            if (this.map.hasHero(heroX, heroY)) {
                Logger.warning(INVALID_LANE);
                continue;
            }
            break;
        }
        hero.setSpawnPosition(heroX, heroY);
        hero.setCurrentPosition(heroX, heroY);
        this.map.addHeroPosition(hero.getCurrentPosition());
    }

    private int getHeroPartySize() {
        return this.heroParty.getHeros().size();
    }

    public int getHeroPartyMaxLevel() {
        int maxLevel = 1;
        for (int i = 0; i < this.getHeroPartySize(); i++) {
            maxLevel = Math.max(heroParty.getHeros().get(i).getLevel(), maxLevel);
        }
        return maxLevel;
    }
}
