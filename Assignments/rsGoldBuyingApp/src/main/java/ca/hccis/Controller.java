import ca.hccis.createFile;
import ca.hccis.entity.createUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.HashMap;


/**
 * @Author RB
 * OSRS buying app
 * @date 25/09/2026
 *CIS2232
 */

public class Controller{

    createFile orders = new createFile();
    private static Gson gson = new Gson();
    public static final String PATH_NAME = "data_baird_richard.json";



}

public static void main(String[] args) throws IOException {


    String mainMenu = "Welcome to the OSRS Gold purchasing tool\n" +
            "Options: \n" +
            "A - Add a new order\n" +
            "V - View an existing order\n" +
            "X - eXit application\n";

    System.out.println(mainMenu);

    String menuOption = "";
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter your choice: ");
    menuOption = scanner.nextLine();



    HashMap<String, createUser> User = new HashMap<>();

    switch(menuOption){

        case "A": {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            File file = new File("data_baird_richard.json");

            Type type = new TypeToken<HashMap<String, createUser>>() {
            }.getType();

            HashMap<String, createUser> users;


            if (file.exists()) {

                FileReader reader = new FileReader(file);

                users = gson.fromJson(reader, type);

                reader.close();

                if (users == null) {
                    users = new HashMap<>();
                }

            } else {

                users = new HashMap<>();
            }

            createUser addUser = new createUser();
            addUser.getInformation();


            users.put(addUser.getUserName(), addUser);
            FileWriter writer = new FileWriter(file);

            gson.toJson(users, writer);

            writer.close();

            System.out.println("User successfully created!");

            break;
        }


        case "V":  {

            Gson gson = new Gson();

            File file = new File("data_baird_richard.json");

            if (!file.exists()) {
                System.out.println("There are no users yet.");
                break;
            }

            Type type = new TypeToken<HashMap<String, createUser>>() {
            }.getType();

            FileReader reader = new FileReader(file);

            HashMap<String, createUser> users =
                    gson.fromJson(reader, type);

            reader.close();

            if (users == null || users.isEmpty()) {
                System.out.println("There are no users yet.");
                break;
            }

            for (createUser user : users.values()) {

                System.out.println("----------------------");

                System.out.println("Username: " + user.getUserName());
                System.out.println("Character: " + user.getCharacterName());
                System.out.println("Email: " + user.getUserEmail());
                System.out.println("Address: " + user.getUserAddress());
                System.out.println("Card: " + user.getCardNumber());
                System.out.println("Card Expiry: " + user.getCardExpInfo());

                System.out.println("----------------------");
            }

            break;
        }
    }

}

