package src.java.views;

/**
 * All string constants.
 */
public class Messages {
    public static final String WELCOME = "\uD83E\uDE84 Welcome to the magic legend world ⚔️";
    public static final String GAME_INTRO = "Take advantage of the terrain, coordinate actions between heroes, and use items to outwit and outfight the invading waves of monsters.\n" +
            "Can you destroy the monsters’ Nexus and stop the monster invasion? Or will the monsters overrun your own fortress?";
    public static final String AGE_QUERY = "Are you over 18 years old? (yes/no) ";
    public static final String SELECT_HERO = "Which hero do you want to add?";
    public static final String LANE_QUERY = "Which lane do you want to spawn? (%d to %d)";
    public static final String ACTION_QUERY = "%s, which action do you want to take?";
    public static final String TELEPORT_QUERY = "Which destination do you want to teleport?";
    public static final String PAUSE_STATUS = "%s is still in cool time from the last action.";

    // TODO: Split single string into "(KEY) HINT" of each input query.
    public static final String MAP_QUERY =
            "(Q) Quit   (W) Up⬆️       (R) Recall           (T) Teleport       (I) Information      (P) Show map\n" +
                    "  (A) Left⬅️ (S) Down⬇️     (D) Right➡️          (H) Help \n" +
                    "    (Z) Attack (X) Cast Spell (C) Change Equipment (V) Drink Potion (M) Enter Market";
    public static final String FAREWELL = "See you. Bye \uD83D\uDC4B";
    public static final String GLOBAL_HINT = "Feel free to type:\n" +
            "`(Q) Quit` whenever you need an emergency exit. `(P) Map` to see the legend world. \n" +
            "`(I) Information` to know more about your team. `(H) Help` to know the things to do.";
    public static final String INFO_HINT = "Type `Info` to see details of your characters.";

    public static final String HERO_WIN = "\uD83C\uDF89 Congrats to defeat the monster in their Nexus. \uD83C\uDF89";
    public static final String MONSTER_WIN = "\uD83D\uDC80 Monsters take your valor. Game over. \uD83D\uDC80";
    public static final String RESTART_QUERY = "Do you dare to start over again? (yes/no) ";

    // Battle Usage
    public static final String MONSTER_BORN = "A monster is born at %s. Be prepared for a fight!";
    public static final String BATTLE_WELCOME = "A monster is coming to you. Be prepared for a fight!";
    public static final String ATTACK_QUERY = "%s, which direction do you want to attack?";
    public static final String RESULT_DODGE = "\uD83D\uDCA8 %s shows its perfect dodge ability.";
    public static final String RESULT_HIT = "\uD83D\uDCA5 %s gets hit by %d damage.";
    public static final String MONSTER_DEAD = "\uD83D\uDE35 %s is dead.";
    public static final String HERO_DEAD = "\uD83D\uDE35 %s is fainted and waited for a new round to respawn.";
    public static final String HERO_RESPAWN = "\uD83D\uDCAB %s is respawn where the hero was born.";
    public static final String RESULT_ICE_SPELL_HIT = "\uD83D\uDCA5 %s gets hit by a ice spell. Its defense is reduced by %d points.";
    public static final String RESULT_FIRE_SPELL_HIT = "\uD83D\uDCA5 %s gets hit by a fire spell. Its damage is reduced by %d points.";
    public static final String RESULT_LIGHTING_SPELL_HIT = "\uD83D\uDCA5 %s gets hit by a lighting spell. Its dodge chance is reduced by %d points.";

    public static final String BATTLE_END = "The scary fight comes to an end.";
    public static final String MONSTER_GREETING = "ARRRRRRRRRRR";
    public static final String MONSTER_FAREWELL = "NOOOOOOOOOOO";

    // Market Usage
    public static final String VENDOR_GREETING = "\uD83D\uDE0E Nice to meet you. I'm sure we've never met before!";
    public static final String TRADE_QUERY = "\uD83D\uDE0E Which item do you want to buy or sell? Selling is in half price. ";

    public static final String MARKET_HELP = "Type in the number in a listed item or " +
            "(Q) Exist this market (P) Show the map (I) Show hero's information (H) Get help instruction.";
    public static final String BUYER_QUERY = "Who is the one buying this? ";
    public static final String VENDOR_FAREWELL = "\uD83D\uDE0E Bring my bless to my twins and hope you buy more there!";
    public static final String BUY_CONFIRM = "Hi, %s. Are you sure you want to buy %s for $%d? (yes/no) ";
    public static final String SELL_CONFIRM = "Hi, %s. Are you sure you want to sell %s for $%d? (yes/no) ";

    // Errors or warnings
    public static final String INVALID_LANE = "This lane is fully occupied. Please select another one.";
    public static final String INVALID_CONFIRM = "This is NOT a valid response. Please type `Yes` or `No`.";
    public static final String INVALID_ACTION = "This is NOT a valid action.";
    public static final String NO_HERO = "There's no recorded hero.";
    public static final String NO_ITEM = "Your bag is empty.";
    public static final String INVALID_BUY = "Sorry. You cannot buy this.";
    public static final String INVALID_SELL = "Sorry. You cannot sell this.";
    public static final String ADULT_ONLY = "Sorry. This game is only provided to adults.";
    public static final String INVALID_MARKET = "Here is NO market or vendor to visit.";

    // Formatting
    public static final String MONSTERS_INFO = "================ Here is monster team information ======================";
    public static final String SHOW_SELECTED_HERO = "====================== You added this hero ============================";
    public static final String HERO_TEAM_INFO = "=================  Here is your team information  ======================";
    public static final String SEPARATOR_LINE = "========================================================================";
    public static final String EMPTY_STRING = "";

    // Input Confirm
    public static final String GET_INPUT_FORMAT = "You've request to (%s).";
}