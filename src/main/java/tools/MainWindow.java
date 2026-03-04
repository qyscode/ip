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

    /**
     * Initializes the controller after its root element has been completely processed.
     * <p>
     * This method binds the scroll pane's vertical value to the dialog container's
     * height to enable automatic scrolling. It also loads the user and application
     * images and displays the initial welcome message from Galaxy.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/GaUser.jpg")));
        galImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/GaApp.jpg")));

        dialogContainer.getChildren().add(
                DialogBox.getGalaxyDialog("Hello! I am Galaxy.\n" +
                        "I can save all the tasks in the galaxy.\n" +
                        "How can I help you today?", galImage)
        );
    }

    /**
     * Injects the Galaxy logic instance into this controller.
     * <p>
     * After injection, it checks for any initialization responses or startup
     * errors from the Galaxy engine and displays them in the {@code statusLabel}.
     *
     * @param d The Galaxy instance to be used by this controller.
     */
    public void setGal(Galaxy d) {
        gal = d;

        // Get status update for initialization
        String startupError = gal.getInitResponse();
        if (!Objects.equals(startupError, "")) {
            statusLabel.setText(startupError);
            }
    }

    /**
     * Handles the user input event triggered by the send button or pressing enter.
     * <p>
     * This method retrieves the user's input, gets a response from Galaxy,
     * and appends two dialog boxes (user and Galaxy) to the
     * dialog container. The user input field is cleared after processing.
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
