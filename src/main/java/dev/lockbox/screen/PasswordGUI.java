package dev.lockbox.screen;

import dev.lockbox.Main;
import dev.lockbox.generator.PasswordGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Objects;

public class PasswordGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/PasswordGUI.fxml")));
        primaryStage.setTitle("Password Configuration GUI");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);

        Button generateButton = (Button) root.lookup("#generateButton");
        generateButton.setOnAction(event -> {
            // Get the user inputs
            CheckBox specialCharactersCheckBox = (CheckBox) root.lookup("#specialBox");
            TextField lengthInput = (TextField) root.lookup("#lengthInput");
            int length = Integer.parseInt(lengthInput.getText());
            boolean useSpecialCharacters = specialCharactersCheckBox.isSelected();

            // Generate the password
            try {
                String password = PasswordGenerator.generatePassword(length, useSpecialCharacters);
                PasswordGenerator.setLastPassword(password); // store the generated password

                // Display the generated password in the passwordBox field
                TextField passwordBox = (TextField) root.lookup("#passwordBox");
                passwordBox.setText(password);
            } catch (IllegalArgumentException e) {
                System.out.println("Failed to generate password: " + e.getMessage());
                e.printStackTrace();
                throw new IllegalArgumentException("Failed to generate password: " + e.getMessage());
            }
        });

        Button cancelButton = (Button) root.lookup("#cancelButton");
        cancelButton.setOnAction(event -> {
            // Close the window
            primaryStage.close();
            System.exit(0);
        });

        Button copyButton = (Button) root.lookup("#copyButton");
        copyButton.setOnAction(event -> {
            StringSelection stringSelection = new StringSelection(Main.LAST_PASSWORD);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            System.out.println("Copied password to clipboard!");
        });

        CheckBox specialCharactersCheckBox = (CheckBox) root.lookup("#specialBox");
        specialCharactersCheckBox.setOnAction(event -> {
            if (specialCharactersCheckBox.isSelected()) {
                System.out.println("Set use Special Characters to true.");
            } else {
                System.out.println("Set use Special Characters to false.");
            }
        });

        TextField lengthInput = (TextField) root.lookup("#lengthInput");
        lengthInput.setOnAction(event -> {
            String input = lengthInput.getText();
            if (input.matches("[0-9]+")) {
                System.out.println(lengthInput.getText());

                int length = Integer.parseInt(lengthInput.getText());
                if (length < Main.MIN_PASSWORD_LENGTH) {
                    System.out.println("Please enter a value greater than or equal to " + Main.MIN_PASSWORD_LENGTH);
                    lengthInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 5px ;");
                    throw new IllegalArgumentException("Length must be greater than or equal to " + Main.MIN_PASSWORD_LENGTH);
                }

                if (length > Main.MAX_PASSWORD_LENGTH) {
                    System.out.println("Please enter a value less than or equal to " + Main.MAX_PASSWORD_LENGTH);
                    lengthInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 5px ;");
                    throw new IllegalArgumentException("Length must be less than or equal to " + Main.MAX_PASSWORD_LENGTH);
                }

                lengthInput.setStyle(""); // reset border color
            } else {
                System.out.println("Please enter a number");
                lengthInput.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 5px ;");
                throw new IllegalArgumentException("Please enter a number");
            }
        });
    }
}