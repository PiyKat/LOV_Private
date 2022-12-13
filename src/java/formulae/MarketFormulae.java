package src.java.formulae;

public class MarketFormulae {

    /**
     * Calculate selling price when a hero sells a stuff to a market
     *
     * @param originalPrice the price of the brand new product
     * @return the final price for the second hand product.
     */
    public static int getSecondHandPrice(int originalPrice) {
        return originalPrice / 2;
    }
}
