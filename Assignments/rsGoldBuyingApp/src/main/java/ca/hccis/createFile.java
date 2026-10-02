package ca.hccis;

import java.io.File;
import java.io.IOException;

public class createFile {


    public static void main(String[] args) {
        try {
            File users = new File("data_baird_richard.json");
            if(users.createNewFile()){
                System.out.println("File created: " + users.getName());
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) {
            System.out.println("An Error has occured");
            e.printStackTrace();
        }

    }


}
