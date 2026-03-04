package tools;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * <p>
 * This class extends {@code HBox} to provide a horizontal layout for chat bubbles
 * in the Galaxy task management application. // Written with AI
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Initializes a new DialogBox with the specified text and image.
     * <p>
     * This constructor loads the FXML layout for the dialog box, sets the controller
     * and root to itself, and configures the text wrapping and width constraints
     * to ensure the dialog content remains within the visible bounds of the UI.
     *
     * @param text The message string to be displayed in the dialog.
     * @param img  The image to be used as the speaker's avatar.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        dialog.setWrapText(true);
        dialog.setMinWidth(50);
        dialog.setMaxWidth(280);
        this.setMinWidth(Region.USE_PREF_SIZE);
        this.setMaxWidth(Double.MAX_VALUE);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * <p>
     * This method reverses the order of children in the {@code HBox} and updates
     * the alignment to {@code Pos.TOP_LEFT}, typically used for the system's
     * (Galaxy's) responses.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a DialogBox instance representing a user's message.
     *
     * @param text The user's input text.
     * @param img  The user's avatar image.
     * @return A {@code DialogBox} configured for the user perspective.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a DialogBox instance representing the system's response.
     * <p>
     * The resulting dialog box is flipped so that the avatar appears on the left side
     * of the text, distinguishing it from user messages.
     *
     * @param text The response text from the Galaxy application.
     * @param img  The application's avatar image.
     * @return A {@code DialogBox} configured for the system perspective.
     */
    public static DialogBox getGalaxyDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
