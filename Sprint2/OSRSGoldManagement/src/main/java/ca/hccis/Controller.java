
package ca.hccis;

import ca.hccis.entity.createUser;
import ca.hccis.BO.calculateGold;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

public class Controller {

    createFile orders = new createFile();

    private static Gson gson = new Gson();

    public static final String PATH_NAME =
            "data/data_baird_richard.json";

    public static void main(String[] args) throws IOException {

        String mainMenu =
                "Welcome to the OSRS Gold purchasing tool\n" +
                        "Options:\n" +
                        "A - Add a new order\n" +
                        "V - View an existing order\n" +
                        "X - Exit application\n";

        System.out.println(mainMenu);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your choice:");
        String menuOption = scanner.nextLine();

        HashMap<String, createUser> users = new HashMap<>();

        switch (menuOption.toUpperCase()) {

            case "A": {

                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();

                File file = new File(PATH_NAME);

                file.getParentFile().mkdirs();

                Type type = new TypeToken<HashMap<String, createUser>>() {
                }.getType();

                if (file.exists()) {

                    try (FileReader reader = new FileReader(file)) {
                        users = gson.fromJson(reader, type);
                    }

                    if (users == null) {
                        users = new HashMap<>();
                    }
                }

                createUser addUser = new createUser();

                addUser.getInformation();

                users.put(addUser.getUserName(), addUser);

                try (FileWriter writer = new FileWriter(file)) {
                    gson.toJson(users, writer);
                }

                System.out.println("User successfully created!");

                break;
            }

            case "B": {

                calculateGold calculateGold = new calculateGold();

                break;
            }

            case "V": {

                File file = new File(PATH_NAME);

                if (!file.exists()) {
                    System.out.println("There are no users yet.");
                    break;
                }

                Type type = new TypeToken<HashMap<String, createUser>>() {
                }.getType();

                try (FileReader reader = new FileReader(file)) {

                    users = gson.fromJson(reader, type);
                }

                if (users == null || users.isEmpty()) {
                    System.out.println("There are no users yet.");
                    break;
                }

                for (createUser user : users.values()) {

                    System.out.println("----------------------");

                    System.out.println("Username: " + user.getUserName());
                    System.out.println("Character: " + user.getCharacterName());
                    System.out.println("Email: " + user.getUserEmail());
                    System.out.println("Gold Purchased: " +
                            user.getGoldPurchaseAmount());

                    System.out.println("----------------------");
                }

                break;
            }

            case "X": {

                System.out.println("Exiting application.");

                break;
            }

            default:

                System.out.println("Invalid menu option.");
        }
    }
}
