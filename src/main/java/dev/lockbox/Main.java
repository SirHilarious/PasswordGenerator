package dev.lockbox;

import dev.lockbox.screen.PasswordGUI;

public class Main {
    public static final int MIN_PASSWORD_LENGTH = 4;
    public static final int MAX_PASSWORD_LENGTH = 100;
    public static String LAST_PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("Starting the Configuration GUI...");
        PasswordGUI.main(args);
        System.out.println("Finished loading the Configuration GUI");
    }
}