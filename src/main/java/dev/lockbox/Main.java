package dev.lockbox;

import dev.lockbox.screen.PasswordGUI;

/**
 * The type Main.
 */
public class Main {
    /**
     * The constant MIN_PASSWORD_LENGTH.
     */
    public static final int MIN_PASSWORD_LENGTH = 4;
    /**
     * The constant MAX_PASSWORD_LENGTH.
     */
    public static final int MAX_PASSWORD_LENGTH = 100;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting the Configuration GUI...");
        PasswordGUI.main(args);
        System.out.println("Finished loading the Configuration GUI");
    }
}