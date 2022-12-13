package src.java.formulae;

public class SpaceFormulae {
    /**
     * Formulas to calculate the space allocation on the map
     */
    private static final int RATIO_UPPER_BOUND = 1;
    private static final int RATIO_LOWER_BOUND = 0;
    private double marketSpaceRatio;
    private double inaccessibleSpaceRatio;
    private double monsterAppearRatio;

    public SpaceFormulae(double marketSpaceRatio, double inaccessibleSpaceRatio) {
        this.setMarketSpaceRatio(marketSpaceRatio);
        this.setInaccessibleSpaceRatio(inaccessibleSpaceRatio);
    }

    private boolean isInRatioRange(double number) {
        return (number >= RATIO_LOWER_BOUND && number <= RATIO_UPPER_BOUND);
    }

    /**
     * Get market space raito
     *
     * @return ratio
     */
    public double getMarketSpaceRatio() {
        return marketSpaceRatio;
    }

    /**
     * Set market space raito
     *
     * @param marketSpaceRatio
     */
    public void setMarketSpaceRatio(double marketSpaceRatio) {
        if (isInRatioRange(marketSpaceRatio) &&
                marketSpaceRatio + this.inaccessibleSpaceRatio <= RATIO_UPPER_BOUND) {
            this.marketSpaceRatio = marketSpaceRatio;
        }
    }

    /**
     * Get inaccessible space ratio
     *
     * @return ratio
     */
    public double getInaccessibleSpaceRatio() {
        return inaccessibleSpaceRatio;
    }

    /**
     * Set inaccessible space ratio
     *
     * @param inaccessibleSpaceRatio
     */
    public void setInaccessibleSpaceRatio(double inaccessibleSpaceRatio) {
        if (isInRatioRange(inaccessibleSpaceRatio) &&
                inaccessibleSpaceRatio + this.marketSpaceRatio <= RATIO_UPPER_BOUND) {
            this.inaccessibleSpaceRatio = inaccessibleSpaceRatio;
        }
    }

    /**
     * Get monster appear ratio
     *
     * @return monster appearing ratio
     */
    public double getMonsterAppearRatio() {
        return monsterAppearRatio;
    }

    /**
     * Set monster appearing ratio
     *
     * @param monsterAppearRatio
     */
    public void setMonsterAppearRatio(double monsterAppearRatio) {
        this.monsterAppearRatio = monsterAppearRatio;
    }
}
