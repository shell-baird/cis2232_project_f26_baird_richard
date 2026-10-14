
package ca.hccis.entity;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class createUser {

    private String userName;
    private String characterName;
    private String userEmail;
    private String userAddress;
    private String cardNumber;
    private String cardExpInfo;
    private double goldPurchaseAmount;

    // Default constructor
    public createUser() {
    }


    public createUser(String userName, String characterName,
                      String userEmail, String userAddress,
                      String cardNumber, String cardExpInfo,
                      double goldPurchaseAmount) {

        this.userName = userName;
        this.characterName = characterName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.cardNumber = cardNumber;
        this.cardExpInfo = cardExpInfo;
        this.goldPurchaseAmount = goldPurchaseAmount;
    }

    // Console Information grabbing
    public boolean getInformationConsole(Scanner scanner) {

        System.out.println("Please enter your username:");
        userName = scanner.nextLine();

        System.out.println("Please enter your character name:");
        characterName = scanner.nextLine();

        System.out.println("Please enter your email:");
        userEmail = scanner.nextLine();

        System.out.println("Please enter your address:");
        userAddress = scanner.nextLine();

        System.out.println("Enter your card number:");
        cardNumber = scanner.nextLine();

        System.out.println("Enter your card expiry information:");
        cardExpInfo = scanner.nextLine();

        System.out.println("How much gold would you like to purchase?");

        try {
            goldPurchaseAmount = Double.parseDouble(scanner.nextLine());

            if (!Double.isFinite(goldPurchaseAmount)
                    || goldPurchaseAmount <= 0) {
                System.out.println("Enter a positive gold amount.");
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return false;
        }
    }

    // Joption panel grabbin information
    public boolean getInformationDialog() {

        userName = JOptionPane.showInputDialog(
                null, "Please enter your username");
        if (userName == null) return false;

        characterName = JOptionPane.showInputDialog(
                null, "Please enter your character name");
        if (characterName == null) return false;

        userEmail = JOptionPane.showInputDialog(
                null, "Please enter your email");
        if (userEmail == null) return false;

        userAddress = JOptionPane.showInputDialog(
                null, "Please enter your address");
        if (userAddress == null) return false;

        cardNumber = JOptionPane.showInputDialog(
                null, "Enter your card number");
        if (cardNumber == null) return false;

        cardExpInfo = JOptionPane.showInputDialog(
                null, "Enter your card expiry information");
        if (cardExpInfo == null) return false;

        String amount = JOptionPane.showInputDialog(
                null, "How much gold would you like to purchase?");

        if (amount == null) return false;

        try {
            goldPurchaseAmount = Double.parseDouble(amount);

            if (!Double.isFinite(goldPurchaseAmount)
                    || goldPurchaseAmount <= 0) {
                JOptionPane.showMessageDialog(
                        null, "Enter a positive gold amount.");
                return false;
            }

            return true;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null, "Please enter a valid number.");
            return false;
        }
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpInfo() {
        return cardExpInfo;
    }

    public double getGoldPurchaseAmount() {
        return goldPurchaseAmount;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardExpInfo(String cardExpInfo) {
        this.cardExpInfo = cardExpInfo;
    }

    public void setGoldPurchaseAmount(double goldPurchaseAmount) {
        this.goldPurchaseAmount = goldPurchaseAmount;
    }
}
