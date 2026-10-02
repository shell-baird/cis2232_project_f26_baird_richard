package ca.hccis;

import ca.hccis.entity.createUser;

/**
 * Started this a little bit
 * To be added later
 *
 */

public class goldCalculations {

    double goldValue = 0.00000025;

    public goldCalculations() {

        createUser createUser = new createUser();

        createUser.goldPurchaseAmount();

        double purchaseTotal = createUser.goldPurchaseAmount * goldValue;

        System.out.println(purchaseTotal);

    }

}
