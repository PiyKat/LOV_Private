package src.java.modules.character.monster;

import src.java.utils.ResourceParser;

import java.util.Random;

/**
 * Load initial data from setting files and provide a monster generator.
 */
public class MonsterFactory {
    private final static String DRAGONS_SETTING = "./data/monsters/Dragons.txt";
    private final static String EXOSKELETONS_SETTING = "./data/monsters/Exoskeletons.txt";
    private final static String SPIRITS_SETTING = "./data/monsters/Spirits.txt";

    private final static int NUM_TYPES_OF_MONSTERS = 3;

    private static final String NAME_KEY = "Name";
    private static final String LEVEL_KEY = "level";
    private static final String DAMAGE_KEY = "damage";
    private static final String DEFENSE_KEY = "defense";
    private static final String DODGE_KEY = "dodge chance";

    public static Dragon makeDragon() {
        ResourceParser parser = new ResourceParser(DRAGONS_SETTING);
        int typeIndex = (int) (Math.random() * parser.getRowCount());
        return new Dragon(
                parser.getRowValue(typeIndex, NAME_KEY),
                Integer.parseInt(parser.getRowValue(typeIndex, LEVEL_KEY)),
                Integer.parseInt(parser.getRowValue(typeIndex, DAMAGE_KEY)),
                Integer.parseInt(parser.getRowValue(typeIndex, DEFENSE_KEY)),
                Integer.parseInt(parser.getRowValue(typeIndex, DODGE_KEY)));
    }

    public static Exoskeleton makeExoskeleton() {
        ResourceParser parser = new ResourceParser(EXOSKELETONS_SETTING);
        int typeIndex = (int) (Math.random() * parser.getRowCount());
        return new Exoskeleton(
                parser.getRowValue(typeIndex, NAME_KEY),
                Integer.parseInt(parser.getRowValue(typeIndex, LEVEL_KEY)),
                Integer.parseInt(parser.getRowValue(typeIndex, DAMAGE_KEY)),
                Integer.parseInt(parser.getRowValue(typeIndex, DEFENSE_KEY)),
                Integer.parseInt(parser.getRowValue(typeIndex, DODGE_KEY)));
    }

    public static Spirit makeSpirit() {
        ResourceParser parser = new ResourceParser(SPIRITS_SETTING);
        int spiritIndex = (int) (Math.random() * parser.getRowCount());
        return new Spirit(
                parser.getRowValue(spiritIndex, NAME_KEY),
                Integer.parseInt(parser.getRowValue(spiritIndex, LEVEL_KEY)),
                Integer.parseInt(parser.getRowValue(spiritIndex, DAMAGE_KEY)),
                Integer.parseInt(parser.getRowValue(spiritIndex, DEFENSE_KEY)),
                Integer.parseInt(parser.getRowValue(spiritIndex, DODGE_KEY)));
    }

    /**
     * Whenever the common space get into a battlefield, generate a new monster by random.
     *
     * @return the randomly picked monster.
     */
    public static Monster getRandomMonster() {
        Random rand = new Random();
        int randomNum = rand.nextInt(NUM_TYPES_OF_MONSTERS);
        Monster monster;
        switch (randomNum) {
            case 1:
                monster = makeExoskeleton();
                break;
            case 2:
                monster = makeSpirit();
                break;
            case 0:
            default:
                monster = makeDragon();
                break;
        }
        return monster;
    }
}
