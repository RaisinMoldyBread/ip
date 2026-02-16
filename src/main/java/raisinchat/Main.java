package raisinchat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import raisinchat.gui.MainWindow;

/**
 * JavaFX application entry point that loads the main window and wires the UI to
 * the {@link RaisinChat} instance.
 */
public class Main extends Application {

    public static final String DATA_LOCATION = "./data/RaisinChatTaskDb.txt";

    private final RaisinChat raisinChat = new RaisinChat(DATA_LOCATION);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRaisinChat(raisinChat); // inject the RaisinChat instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called by the JavaFX runtime during shutdown to perform cleanup, such as
     * persisting application data.
     */
    @Override
    public void stop() {
        // This will save the data regardless of how the user exits
        raisinChat.saveData();
    }
}
