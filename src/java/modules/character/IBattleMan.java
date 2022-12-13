package src.java.modules.character;

/**
 * Interface for pausing character move after some particular moves
 */
public interface IBattleMan {
    void revive();

    int getPauseTurns();

    void setPauseTurns(int pauseTurns);
}
