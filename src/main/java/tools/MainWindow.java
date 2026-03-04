package tools;

import java.util.Objects;

import galaxy.task.Galaxy;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


/**
 * Controller for the main GUI.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Galaxy gal;
    private Image userImage;
    private Image galImage;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/GaUser.jpg")));
        galImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/GaApp.jpg")));

        dialogContainer.getChildren().add(
                DialogBox.getGalaxyDialog("Hello! I am Galaxy. \n" +
                        "I can save all the tasks in the galaxy.\n" +
                        "How can I help you today?", galImage)
        );
    }

    /** Injects the Galaxy instance */
    public void setGal(Galaxy d) {
        gal = d;

        // Get status update for initialization
        String startupError = gal.getInitResponse();
        if (!Objects.equals(startupError, "")) {
            statusLabel.setText(startupError);
            }
    }

    /**
     *
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
    */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gal.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGalaxyDialog(response, galImage)
        );
        userInput.clear();
    }
}
