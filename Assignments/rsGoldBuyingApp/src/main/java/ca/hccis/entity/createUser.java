package ca.hccis.entity;

import java.util.Scanner;

public class createUser {

    String userName;
    String characterName;
    String userEmail;
    String userAddress;
    String cardNumber;
    String cardExpInfo;
    public double goldPurchaseAmount;
//    int orderID;

    public void getInformation() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter your userName");
        userName = scanner.nextLine();

        System.out.println("Please Enter your character name");
        characterName = scanner.nextLine();

        System.out.println("userEmail");
        userEmail = scanner.nextLine();

        System.out.println("Enter your address");
        userAddress = scanner.nextLine();

        System.out.println("Enter your card number");
        cardNumber = scanner.nextLine();

        System.out.println("Confirm the expiry info on your card");
        cardExpInfo = scanner.nextLine();

        System.out.println("How much gold are you looking to purchase");
        goldPurchaseAmount = scanner.nextDouble();

    }
    public createUser(){

    }



    public createUser(
            String userName,
            String characterName,
            String userEmail,
            String userAddress,
            String cardNumber,
            String cardExpInfo,
            double goldPurchaseAmount)
    {
        this.userName = userName;
        this.characterName = characterName;
        this.userEmail = userEmail;
        this.userAddress = userAddress;
        this.cardNumber = cardNumber;
        this.cardExpInfo = cardExpInfo;
        this.goldPurchaseAmount = goldPurchaseAmount;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpInfo() {
        return cardExpInfo;
    }

    public void setCardExpInfo(String cardExpInfo) {
        this.cardExpInfo = cardExpInfo;
    }


    public void goldPurchaseAmount() {
    }
    public void editAccountInfo(){
        String editUsername = "";
        String editCharacterName = "";
        String editUserEmail = "";
        String editUserAddress = "";
        String editCardNumber = "";
        String editCardExpInfo = "";

/**
 * Edits user information
 */
        System.out.println("Edit Username");
        setUserName(editUsername);
        System.out.println(("Edit Character Name"));
        setCharacterName(editCharacterName);
        System.out.println("Enter new email address");
        setUserEmail(editUserEmail);
        System.out.println("Updated address");
        setUserAddress(editUserAddress);
        System.out.println("Change card number on file");
        setCardNumber(editCardNumber);
        System.out.println("Edit card Expiry information");
        setCardExpInfo(editCardExpInfo);

    }
}

