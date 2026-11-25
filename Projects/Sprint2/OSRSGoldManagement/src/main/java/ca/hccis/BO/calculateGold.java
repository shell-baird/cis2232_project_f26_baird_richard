package ca.hccis.BO;

import ca.hccis.entity.createUser;

import java.util.Scanner;


/**
 * The calculation is to
 *
 */

public class calculateGold {

    double goldValue = 0.00000025;

    public calculateGold() {

        double purchaseAmount = 0;
        double totalPrice = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("How much gold are you wanting to buy? ");
        purchaseAmount = scanner.nextDouble();

        totalPrice = purchaseAmount * goldValue;


//            createUser createUser = new createUser();
//
//            double purchaseTotal = createUser.getGoldPurchaseAmount() * goldValue;
//
//            System.out.println(purchaseTotal);


    }

}