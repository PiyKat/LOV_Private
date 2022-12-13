package src.java.controllers;

import src.java.exceptions.InvalidActionException;
import src.java.modules.character.monster.Monster;
import src.java.modules.character.monster.MonsterFactory;
import src.java.modules.map.Map;
import src.java.modules.map.Position;
import src.java.utils.Logger;
import src.java.views.GameView;
import src.java.views.MapView;
import src.java.views.MonsterView;

import java.util.ArrayList;

/**
 * This controller supports the monsters' initialization.
 */
public class MonsterController {
    private static final int MONSTER_SPAWN_Y = 0;
    private static final ArrayList<Monster> monsters = new ArrayList<>();
    private Map map;

    public static void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

    public void monstersTurn() {
        for (Monster monster : monsters) {
            this.monsterTurn(monster);
        }
        BattleController.setMonsters(monsters);
    }

    private void monsterTurn(Monster monster) {
        int pauseTurnLeft = monster.getPauseTurns();
        if (pauseTurnLeft > 0) {
            GameView.showPause(monster.getName());
            monster.setPauseTurns(pauseTurnLeft - 1);
            KeyController.enterToContinue();
            return;
        }

        try {
            BattleController.attackHero(monster);
            KeyController.enterToContinue();
            return;
        } catch (InvalidActionException ignored) {
            Logger.debug("There is no hero to attack.");
        }
        Position currentPosition = monster.getCurrentPosition();
        if (map.isValidMonsterStep(currentPosition.getX(), currentPosition.getY() + 1)) {
            currentPosition.shiftDown();
            MapView.show(map);
            MonsterView.showMoveForward(monster);
            if (MapController.reachHeroNexus(currentPosition)) {
                NexusController.enterHeroNexus(monster);
            }
            KeyController.enterToContinue();
        }
    }

    public void addMonsters(int maxLevel) {
        // One new monster for each lane
        for (int laneCount = 0; laneCount < this.map.getLaneCount(); laneCount++) {
            Monster monster = MonsterFactory.getRandomMonster();
            monster.setSpawnPosition(this.map.getLaneX(laneCount), MONSTER_SPAWN_Y);
            if (!this.map.isValidMonsterStep(monster.getSpawnPosition())) {
                monster.getSpawnPosition().shiftRight();
            }
            monster.setCurrentPosition(monster.getSpawnPosition());
            this.map.addMonsterPosition(monster.getCurrentPosition());
            monster.updateLevelAndHP(maxLevel);
            monsters.add(monster);
            // Display welcome messages from the newborn monster
            monster.sayWelcome();
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
