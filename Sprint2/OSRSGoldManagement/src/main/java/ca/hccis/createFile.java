package ca.hccis;

import java.io.File;
import java.io.IOException;

public class createFile {

    public static void main(String[] args) {
        try {
            // Create a folder
            File folder = new File("data");
            if (folder.mkdirs()) {
                System.out.println("Folder created: " + folder.getName());
            } else if (folder.exists()) {
                System.out.println("Folder already exists");
            }

            // Create the file inside the folder
            File users = new File(folder, "data_baird_richard.json");

            if (users.createNewFile()) {
                System.out.println("File created: " + users.getPath());
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) {
            System.out.println("An error has occurred");
            e.printStackTrace();
        }
    }
}
