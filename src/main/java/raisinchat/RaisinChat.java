package raisinchat;

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
        this.ui = new Ui();
        this.storage = new Storage(fileDb);

        try {
            this.tasks = new TaskList(this.storage.load());

        } catch (RaisinChatException e) {
            this.ui.showLoadingError();
            this.ui.showMessage(e.getMessage());
            this.tasks = new TaskList(null);
        }

    }

    /**
     * Runs the program and loops forever till shouldExit returns false, triggered by running
     * [Command Object].isExit() -> This checks if the command is an Exit or Bye command
     * Saves the current list of tasks into a text file at ./data/RaisinChatTaskDb.txt
     */
    public void run() {
        ui.showWelcome();
        boolean shouldExit = false;
        while (!shouldExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                shouldExit = c.isExit();
            } catch (RaisinChatException e) {
                ui.showMessage(e.getMessage());
            }
        }

        boolean isSaved = this.storage.save(this.tasks);
        if (!isSaved) {
            System.out.println("Save failed");
        } else {
            System.out.println("Saved tasks successfully!");
        }

    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


}
