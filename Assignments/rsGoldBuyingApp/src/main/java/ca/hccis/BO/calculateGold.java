package ca.hccis.BO;

public class calculateGold {

    private final double goldValue = 0.00000025;

    public calculateGold() {
    }

    public double calculateGoldPrice(double purchaseAmount) {
        return purchaseAmount * goldValue;
    }
}

