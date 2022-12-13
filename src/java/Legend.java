package src.java;

import src.java.controllers.*;
import src.java.exceptions.HerosReach;
import src.java.exceptions.MonstersReach;
import src.java.exceptions.requests.QuitRequest;
import src.java.modules.Player;
import src.java.modules.character.hero.HeroBuilder;
import src.java.modules.map.Map;
import src.java.utils.ConfigParser;
import src.java.utils.Logger;
import src.java.views.GameView;

import src.java.modules.MusicDevice;

/**
 * Class that runs multiple rounds of the Legends of Valor game
 */
public class Legend {
    private final static String CONFIG_PATH = "./config";
    private HeroBuilder heroBuilder;
    private MapController mapController;
    private HeroController heroController;
    private MonsterController monsterController;
    private GameController gameController;

    /**
     * Prepare controllers for the whole game.
     */
    private void initController() {
        this.mapController = new MapController();
        this.heroController = new HeroController(mapController);
        this.monsterController = new MonsterController();
        this.gameController = new GameController(monsterController, heroController);
    }

    /**
     * Set up environments by the config files and resources.
     */
    private void setupConfig() {
        ConfigParser p = new ConfigParser(CONFIG_PATH);

        // Setup logger
        Logger.setLevel(Integer.parseInt(p.getValue("logger_level")));

        // Setup monster spawn delay
        int monsterSpawnDelay = Integer.parseInt(p.getValue("monster_spawn_delay"));
        if (monsterSpawnDelay <= 0) {
            Logger.warning("Monster spawn delay round should be positive.");
        }
        this.gameController.setMonsterSpawnDelay(monsterSpawnDelay);

        // Setup Map with the configured size.
        int lane_count = Integer.parseInt(p.getValue("lane_count"));
        int map_height = Integer.parseInt(p.getValue("map_height"));
        if (lane_count <= 0 || map_height <= 0) {
            Logger.warning("Map setting is too small!");
        }
        Map map = new Map(lane_count, map_height);
        map.generateValorMap();

        // Inject the map
        this.mapController.setMap(map);
        this.heroController.setMap(map);
        this.monsterController.setMap(map);

        // Setup resources
        this.heroBuilder = new HeroBuilder();
    }

    /**
     * Where the legend begins.
     */
    public void start() {
        // Create terminal interface controllers
        initController();
        setupConfig();

        Player player = new Player();
        for (; ; ) {
            try {
                MusicDevice music = new MusicDevice("./data/Skyrim.wav");
                music.play();
                PlayerController.queryAge(player);
                // Select a hero to be either the team_leader or the only player.
                this.heroController.makeTeam(heroBuilder);

                // Start the adventure!!
                this.gameController.start();
            } catch (QuitRequest r) {
                GameView.end();
                break;
            } catch (HerosReach r) {
                this.heroWins();
            } catch (MonstersReach r) {
                this.monsterWins();
            } catch (Exception e) {
                System.out.println(e);
            }
            // Ask to challenge again.
            GameView.restartQuery();
            if (!KeyController.confirmationQuery()) {
                GameView.end();
                break;
            }
        }
    }

    /**
     * Print Hero win message
     */
    private void heroWins() {
        GameView.heroWin();
    }


    /**
     * Print Monster win message
     */
    private void monsterWins() {
        GameView.monsterWin();
    }
}
