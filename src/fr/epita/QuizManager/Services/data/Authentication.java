package fr.epita.QuizManager.Services.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Authentication {

    public static String username;
    public static Boolean isAuthenticated() {
        Scanner scanner = new Scanner(System.in);
        Properties properties = getProperties();

        System.out.println("Welcome");
        System.out.println("username: ");
        username = scanner.nextLine();
        System.out.println("password: ");
        String pwd = scanner.nextLine();

        String password = properties.getProperty("username." + username.toString());
        Boolean isValid = password != null && password.equals(pwd.toString());
        properties.clear();
        if (isValid) {
            return isValid;
        } else {
            properties.clear();
            return isValid;
        }

    }

    private static Properties getProperties() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("credentials.properties"));
        } catch (IOException e) {
            System.out.println("sorry, the program is not finding the required files, check your setup, authentication is not possible");
            return null;
        }
        return properties;
    }
}
