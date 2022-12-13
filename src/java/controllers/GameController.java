package src.java.controllers;

import src.java.views.GameView;

/**
 * Control the game flow.
 */
public class GameController {

    private final MonsterController monsterController;
    private final HeroController heroController;
    private int monsterSpawnDelay;

    public GameController(MonsterController monsterController, HeroController heroController) {
        this.monsterController = monsterController;
        this.heroController = heroController;
    }

    public void start() {
        GameView.welcome();

        for (int round = 0; ; round = (round + 1) % this.monsterSpawnDelay) {
            if (round == 0) {
                this.monsterController.addMonsters(this.heroController.getHeroPartyMaxLevel());
            }
            BattleController.reviveAllFaintedHeros();
            this.heroController.herosTurn();
            this.monsterController.monstersTurn();
            BattleController.regain();
        }
    }

    public void setMonsterSpawnDelay(int monsterSpawnDelay) {
        this.monsterSpawnDelay = monsterSpawnDelay;
    }
}
