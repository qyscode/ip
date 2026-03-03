package tools;

import java.io.IOException;
import galaxy.task.Galaxy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

/**
 * A GUI for Galaxy using FXML.
 */
public class Ui {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage;
    private Image galImage;


    public Ui() {
        //does nothing
    }




    // For "cannot read file" issues
    public void showLoadingError() {
        // to implement error as shown on the UI
    }
    // "file exists but data is corrupted"
    public void showFormatError() {
    }
}