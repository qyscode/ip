package tools;

import java.io.IOException;

import galaxy.task.Galaxy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Galaxy using FXML.
 * <p>
 * This class serves as the main entry point for the JavaFX application,
 * responsible for initializing the Galaxy logic engine and setting up the
 * primary stage with the FXML-defined layout.
 */
public class Main extends Application {

    /**
     * The Galaxy instance that handles task logic and data persistence.
     * It is initialized with the local CSV data file path.
     */
    private Galaxy gal = new Galaxy("src/main/data/app-data.csv");

    /**
     * Starts the JavaFX application by loading the FXML layout and injecting dependencies.
     * <p>
     * This method loads {@code MainWindow.fxml}, creates the primary scene, and
     * passes the {@code Galaxy} logic instance to the controller to facilitate
     * communication between the UI and the backend.
     *
     * @param stage The primary stage for this application, onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setGal(gal);  // inject the Galaxy instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
