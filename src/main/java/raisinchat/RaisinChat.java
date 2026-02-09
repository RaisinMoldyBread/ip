package raisinchat;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import raisinchat.command.Command;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Main Application class
 */
public class RaisinChat {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates instance of the application
     *
     * @param fileDb Database of past saved tasks to load into the program
     */
    public RaisinChat(String fileDb) {
        assert fileDb != null : "File path for storage must not be null";
        this.ui = new Ui();
        this.storage = new Storage(fileDb);

        try {
            this.tasks = new TaskList(this.storage.load());

        } catch (RaisinChatException e) {
            this.ui.showLoadingError();
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
        assert this.tasks != null : "TaskList should never be null after initialization";

    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        assert input != null : "UI passed null input to RaisinChat";
        try {
            Command c = Parser.parse(input);
            assert c != null : "Parser must never return null Command";
            assert tasks != null && ui != null && storage != null
                    : "Execution dependencies must not be null";
            String result = c.execute(tasks, ui, storage);

            // Check if this command signals termination
            if (c.isExit()) {
                // 1. Save data immediately
                this.storage.save(this.tasks);

                // 2. Delay the close so the user sees the "Bye" message
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
            }

            return result;
        } catch (RaisinChatException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the welcome message to be displayed in the GUI.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Saves the current task list to the storage file.
     */
    public void saveData() {
        if (this.storage != null && this.tasks != null) {
            boolean isSaved = this.storage.save(this.tasks);
            assert isSaved : "Something went wrong while saving data, check Storage.java";
        }
    }


}
