package ca.hccis;

import ca.hccis.entity.createUser;
import ca.hccis.BO.calculateGold;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {

    public static final String PATH_NAME =
            "data_baird_richard.json";

    private static final Gson gson =
            new GsonBuilder().setPrettyPrinting().create();

    private static final Type USER_MAP_TYPE =
            new TypeToken<HashMap<String, createUser>>() {
            }.getType();

    private static final Object FILE_LOCK = new Object();

    public static void main(String[] args) {


        Thread dialogStarter = new Thread(
                () -> SwingUtilities.invokeLater(
                        Controller::runDialogMenu),
                "JOptionPane-Interface");

        dialogStarter.start();
        runConsoleMenu();
    }

    private static void runConsoleMenu() {

        try (Scanner scanner = new Scanner(System.in)) {

            boolean running = true;

            while (running) {

                System.out.println("\nOSRS Gold Purchasing Tool");
                System.out.println("A - Add a new order");
                System.out.println("B - Calculate gold");
                System.out.println("V - View existing orders");
                System.out.println("X - Exit console");

                System.out.print("Choose an option: ");

                String choice = scanner.nextLine()
                        .trim().toUpperCase();

                switch (choice) {

                    case "A":
                        createConsoleOrder(scanner);
                        break;

                    case "B":
                        calculateConsoleGold(scanner);
                        break;

                    case "V":
                        viewConsoleOrders();
                        break;

                    case "X":
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        }

        System.out.println("Console interface closed.");
    }

    private static void runDialogMenu() {

        String[] options = {
                "Add a new order",
                "Calculate gold",
                "View existing orders",
                "Exit dialog interface"
        };

        boolean running = true;

        while (running) {

            String choice = (String) JOptionPane.showInputDialog(
                    null,
                    "Welcome to the OSRS Gold purchasing tool",
                    "OSRS Gold Purchasing Tool",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == null
                    || choice.equals("Exit dialog interface")) {
                running = false;
                continue;
            }

            switch (choice) {

                case "Add a new order":
                    createDialogOrder();
                    break;

                case "Calculate gold":
                    calculateDialogGold();
                    break;

                case "View existing orders":
                    viewDialogOrders();
                    break;
            }
        }
    }

    private static void createConsoleOrder(Scanner scanner) {

        createUser user = new createUser();

        if (!user.getInformationConsole(scanner)) {
            System.out.println("Order cancelled or invalid.");
            return;
        }

        try {
            int count = saveOrder(user);

            System.out.println("Order saved successfully.");
            System.out.println("Combined total orders: " + count);

        } catch (IOException e) {
            System.out.println("Unable to save order: "
                    + e.getMessage());
        }
    }

    private static void createDialogOrder() {

        createUser user = new createUser();

        if (!user.getInformationDialog()) {
            return;
        }

        try {
            int count = saveOrder(user);

            JOptionPane.showMessageDialog(
                    null,
                    "Order saved successfully.\n"
                            + "Combined total orders: " + count);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Unable to save order: " + e.getMessage(),
                    "File error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    private static int saveOrder(createUser user)
            throws IOException {

        synchronized (FILE_LOCK) {

            File file = new File(PATH_NAME);

            HashMap<String, createUser> users = readOrders();

            String key = user.getUserName();

            int suffix = 2;
            while (users.containsKey(key)) {
                key = user.getUserName() + "_" + suffix++;
            }

            users.put(key, user);

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(users, writer);
            }

            return users.size();
        }
    }

    private static HashMap<String, createUser> readOrders()
            throws IOException {

        synchronized (FILE_LOCK) {

            File file = new File(PATH_NAME);

            if (!file.exists()) {
                return new HashMap<>();
            }

            try (FileReader reader = new FileReader(file)) {

                HashMap<String, createUser> users =
                        gson.fromJson(reader, USER_MAP_TYPE);

                return users == null
                        ? new HashMap<>()
                        : users;
            }
        }
    }

    private static void viewConsoleOrders() {

        try {
            HashMap<String, createUser> users = readOrders();

            if (users.isEmpty()) {
                System.out.println("There are no orders.");
                return;
            }

            displayConsoleOrders(users);

        } catch (IOException e) {
            System.out.println("Unable to read orders: "
                    + e.getMessage());
        }
    }

    private static void displayConsoleOrders(
            HashMap<String, createUser> users) {

        for (Map.Entry<String, createUser> entry
                : users.entrySet()) {

            createUser user = entry.getValue();

            System.out.println("------------------------");
            System.out.println("Username: " + user.getUserName());
            System.out.println("Character: "
                    + user.getCharacterName());
            System.out.println("Email: " + user.getUserEmail());
            System.out.println("Gold purchased: "
                    + user.getGoldPurchaseAmount());
        }

        System.out.println("------------------------");
        System.out.println("Combined total orders: " + users.size());
    }

    private static void viewDialogOrders() {

        try {
            HashMap<String, createUser> users = readOrders();

            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(
                        null, "There are no orders.");
                return;
            }

            StringBuilder output = new StringBuilder();

            for (createUser user : users.values()) {

                output.append("------------------------\n");
                output.append("Username: ")
                        .append(user.getUserName()).append("\n");
                output.append("Character: ")
                        .append(user.getCharacterName()).append("\n");
                output.append("Email: ")
                        .append(user.getUserEmail()).append("\n");
                output.append("Gold purchased: ")
                        .append(user.getGoldPurchaseAmount())
                        .append("\n");
            }

            output.append("------------------------\n");
            output.append("Combined total orders: ")
                    .append(users.size());

            JOptionPane.showMessageDialog(
                    null, output.toString(),
                    "Saved Orders",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Unable to read orders: " + e.getMessage(),
                    "File error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void calculateConsoleGold(Scanner scanner) {

        System.out.print("How much gold would you like to buy? ");

        try {
            double amount = Double.parseDouble(scanner.nextLine());

            if (!Double.isFinite(amount) || amount <= 0) {
                System.out.println(
                        "Enter an amount greater than zero.");
                return;
            }

            calculateGold calculator = new calculateGold();
            double total = calculator.calculateGoldPrice(amount);

            System.out.println("Gold amount: " + amount);
            System.out.printf("Total price: $%.2f%n", total);

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static void calculateDialogGold() {

        String input = JOptionPane.showInputDialog(
                null, "How much gold would you like to buy?");

        if (input == null) {
            return;
        }

        try {
            double amount = Double.parseDouble(input);

            if (!Double.isFinite(amount) || amount <= 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Enter an amount greater than zero.");
                return;
            }

            calculateGold calculator = new calculateGold();
            double total = calculator.calculateGoldPrice(amount);

            JOptionPane.showMessageDialog(
                    null,
                    "Gold amount: " + amount
                            + "\nTotal price: $"
                            + String.format("%.2f", total));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null, "Please enter a valid number.");
        }
    }
}

