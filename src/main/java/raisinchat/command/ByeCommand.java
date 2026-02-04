package raisinchat.command;

import raisinchat.Storage;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Abstraction of the exit command for the application, triggered by "bye" or "exit" command
 */
public class ByeCommand extends Command {

    public ByeCommand(UserCommand command) {
        super(command);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye :( Please come back again soon :\")");
    }
}
