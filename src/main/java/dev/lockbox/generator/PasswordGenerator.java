package dev.lockbox.generator;

import dev.lockbox.Main;

import java.security.SecureRandom;

/**
 * The type Password generator.
 */
public class PasswordGenerator {
    // Define constant String variables for different character types
    private static final String LOWER_CASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS_CHARACTERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-=[]{}|;:,.<>/?";

    /**
     * The constant lastPassword.
     */
// Define a public static variable to hold the last generated password
    public static String lastPassword = "";

    /**
     * Generate password string.
     *
     * @param length     the length
     * @param useSpecial the use special
     * @return the string
     */
// This method generates a random password string with the specified length and options.
    public static String generatePassword(int length, boolean useSpecial) {
        // Check if the length parameter is within the allowed range of Main.MIN_PASSWORD_LENGTH and Main.MAX_PASSWORD_LENGTH
        if (length < Main.MIN_PASSWORD_LENGTH || length > Main.MAX_PASSWORD_LENGTH) {
            // If not, print an error message and throw an IllegalArgumentException
            System.out.println("Password length must be between " + Main.MIN_PASSWORD_LENGTH + " and " + Main.MAX_PASSWORD_LENGTH);
            throw new IllegalArgumentException("Password length must be between " + Main.MIN_PASSWORD_LENGTH + " and " + Main.MAX_PASSWORD_LENGTH);
        }
        // Create a new instance of SecureRandom to generate random numbers for the password
        SecureRandom random = new SecureRandom();
        // Create a new StringBuilder object with the specified length
        StringBuilder sb = new StringBuilder(length);
        // Create a new StringBuilder object to store the characters that will be used for the password
        StringBuilder charactersBuilder = new StringBuilder();
        // Append lowercase, uppercase, and digit characters to the charactersBuilder
        charactersBuilder.append(LOWER_CASE_CHARACTERS)
                .append(UPPER_CASE_CHARACTERS)
                .append(DIGITS_CHARACTERS);
        // If the useSpecial parameter is true, append special characters to the charactersBuilder
        if (useSpecial) {
            charactersBuilder.append(SPECIAL_CHARACTERS);
        }
        // Convert the charactersBuilder to a String
        String characters = charactersBuilder.toString();
        // Generate a random index within the range of the characters String for each character in the password
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            // Append the character at the random index to the password StringBuilder
            sb.append(characters.charAt(index));
        }
        // Convert the password StringBuilder to a String and return it
        return sb.toString();
    }

    /**
     * Sets the value of the static variable 'lastPassword' to the given password.
     *
     * @param password the password to set as the last password
     */
    public static void setLastPassword(String password) {
        lastPassword = password;
    }
}