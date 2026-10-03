package ca.hccis.BO;

import ca.hccis.entity.createUser;

import java.util.Scanner;


/**
 * The calculation is to
 *
 */

public class calculateGold {

    private final double goldValue = 0.00000025;

    public calculateGold() {
        calculateGold gold = new calculateGold();
        double purchaseAmount = 0;
        double totalPrice;

        Scanner scanner = new Scanner(System.in);

        System.out.println("How much gold are you wanting to buy? ");
        purchaseAmount = scanner.nextDouble();

        totalPrice = gold.calculateGoldPrice(purchaseAmount);
        System.out.println("Gold amount: " + purchaseAmount);
        System.out.println("Total price: $" + totalPrice);



//            createUser createUser = new createUser();
//
//            double purchaseTotal = createUser.getGoldPurchaseAmount() * goldValue;
//
//            System.out.println(purchaseTotal);


    }

    public double calculateGoldPrice(double purchaseAmount) {
    return purchaseAmount * goldValue;}
}


