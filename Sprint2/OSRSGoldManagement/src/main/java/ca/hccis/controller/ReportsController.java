
package ca.hccis.controller;

import ca.hccis.entity.createUser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class ReportsController {

    private static final String PATH_NAME =
            "data/data_baird_richard.json";

    private final Gson gson = new Gson();

    // Read customer records from JSON
    private HashMap<String, createUser> readUsers() {

        File file = new File(PATH_NAME);

        System.out.println("Reading JSON from: " +
                file.getAbsolutePath());

        System.out.println("File exists: " + file.exists());

        if (!file.exists()) {
            return new HashMap<>();
        }

        Type type = new TypeToken<HashMap<String, createUser>>() {
        }.getType();

        try (FileReader reader = new FileReader(file)) {

            HashMap<String, createUser> users =
                    gson.fromJson(reader, type);

            return users == null ? new HashMap<>() : users;

        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to read customer data."
            );
        }
    }

    // GET: Retrieve all customers
    @GetMapping("/users")
    public List<UserReport> getUsers() {

        HashMap<String, createUser> users = readUsers();

        List<UserReport> reports = new ArrayList<>();

        for (createUser user : users.values()) {
            reports.add(new UserReport(user));
        }

        return reports;
    }

    // GET: Retrieve one customer
    @GetMapping("/users/{username}")
    public UserReport getUser(@PathVariable String username) {

        HashMap<String, createUser> users = readUsers();

        createUser user = users.get(username);

        if (user == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Customer not found."
            );
        }

        return new UserReport(user);
    }

    // GET: Retrieve sales summary
    @GetMapping("/reports/summary")
    public SalesSummary getSummary() {

        HashMap<String, createUser> users = readUsers();

        double totalGold = 0;

        for (createUser user : users.values()) {
            totalGold += user.getGoldPurchaseAmount();
        }

        int totalCustomers = users.size();

        double goldValue = 0.00000025;

        double totalSales = totalGold * goldValue;

        double averageGold = totalCustomers > 0
                ? totalGold / totalCustomers
                : 0;

        return new SalesSummary(
                totalCustomers,
                totalGold,
                totalSales,
                averageGold
        );
    }

    // Customer report
    public static class UserReport {

        public String userName;
        public String characterName;
        public String userEmail;
        public double goldPurchaseAmount;

        public UserReport(createUser user) {

            this.userName = user.getUserName();
            this.characterName = user.getCharacterName();
            this.userEmail = user.getUserEmail();
            this.goldPurchaseAmount =
                    user.getGoldPurchaseAmount();
        }
    }

    // Sales summary report
    public static class SalesSummary {

        public int totalCustomers;
        public double totalGold;
        public double totalSales;
        public double averageGold;

        public SalesSummary(
                int totalCustomers,
                double totalGold,
                double totalSales,
                double averageGold) {

            this.totalCustomers = totalCustomers;
            this.totalGold = totalGold;
            this.totalSales = totalSales;
            this.averageGold = averageGold;
        }
    }

    @PostMapping("/users")
    public UserReport createCustomer(@RequestBody CreateUserRequest request) {

        // Validate required information
        if (request.userName == null || request.userName.isBlank()
                || request.characterName == null || request.characterName.isBlank()
                || request.userEmail == null || request.userEmail.isBlank()
                || request.userAddress == null || request.userAddress.isBlank()
                || request.goldPurchaseAmount < 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Please provide valid customer information."
            );
        }

        HashMap<String, createUser> users = readUsers();

        // Prevent duplicate usernames
        if (users.containsKey(request.userName)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "That username already exists."
            );
        }

        // Create customer
        createUser newUser = new createUser(
                request.userName,
                request.characterName,
                request.userEmail,
                request.userAddress,
                "",
                "",
                request.goldPurchaseAmount
        );

        users.put(request.userName, newUser);

        // Save updated JSON
        File file = new File(PATH_NAME);

        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (java.io.FileWriter writer = new java.io.FileWriter(file)) {

            gson.toJson(users, writer);

        } catch (Exception e) {

            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to save customer."
            );
        }

        return new UserReport(newUser);
    }


    // Data received from the HTML form
    public static class CreateUserRequest {

        public String userName;
        public String characterName;
        public String userEmail;
        public String userAddress;
        public double goldPurchaseAmount;
    }

}

