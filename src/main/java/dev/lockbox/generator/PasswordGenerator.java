package dev.lockbox.generator;

import dev.lockbox.Main;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String LOWER_CASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS_CHARACTERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-=[]{}|;:,.<>/?";

    public static String generatePassword(int length, boolean useSpecial) {
        if (length < Main.MIN_PASSWORD_LENGTH || length > Main.MAX_PASSWORD_LENGTH) {
            System.out.println("Password length must be between " + Main.MIN_PASSWORD_LENGTH + " and " + Main.MAX_PASSWORD_LENGTH);
            throw new IllegalArgumentException("Password length must be between " + Main.MIN_PASSWORD_LENGTH + " and " + Main.MAX_PASSWORD_LENGTH);
        }
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);

        StringBuilder charactersBuilder = new StringBuilder();
        charactersBuilder.append(LOWER_CASE_CHARACTERS)
                .append(UPPER_CASE_CHARACTERS)
                .append(DIGITS_CHARACTERS);
        if (useSpecial) {
            charactersBuilder.append(SPECIAL_CHARACTERS);
        }
        String characters = charactersBuilder.toString();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        System.out.println("Generating password with length: " + length);
        System.out.println("Using special characters: " + useSpecial);
        System.out.println("Generated password: " + sb);
        System.out.println("Setting the last generated password to: " + sb);

        return sb.toString();
    }

    public static void setLastPassword(String password) {
        Main.LAST_PASSWORD = password;
    }
}