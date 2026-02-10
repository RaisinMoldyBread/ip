package raisinchat;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import raisinchat.gui.MainWindow;

/**
 * A GUI for RaisinChat using FXML.
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
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     */
    @Override
    public void stop() {
        // This will save the data regardless of how the user exits
        raisinChat.saveData();
    }
}
