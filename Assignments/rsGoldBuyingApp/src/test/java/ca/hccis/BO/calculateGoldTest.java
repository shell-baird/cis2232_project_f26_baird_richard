package ca.hccis.BO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Ensured through testing that each section of the calculation works
 * AI tested to confirm everything works, calculation isn't very difficult, so there wasn't a ton of testing to perform.
 *
 */

class calculateGoldTest {

    double goldValue = 0.00000025;

    @Test
    public void calculateGold() {

        calculateGold gold = new calculateGold();

        double purchaseAmount = 10000;
        double totalPrice = gold.calculateGoldPrice(purchaseAmount);

        assertEquals(0.0025, totalPrice, 0.000000001);
        assertTrue(totalPrice > 0);

    }
}