package dev.lockbox.screen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.Objects;


/**
 * The type Error screen.
 */
public class ErrorScreen extends Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ErrorScreen.fxml")));
        primaryStage.setTitle("Error");
        primaryStage.setScene(new Scene(root));

        // get a reference to the errorBox TextArea
        TextArea errorBox = (TextArea) root.lookup("#errorBox");
        String errorLog = "This is an error log. Please see the log file for more details.\n";
        errorBox.setText(errorLog);

        primaryStage.show();
        primaryStage.setResizable(false);

        Button okayButton = (Button) root.lookup("#okayButton");
        okayButton.setOnAction(event -> {
            // Close the window
            primaryStage.close();
        });
    }
}