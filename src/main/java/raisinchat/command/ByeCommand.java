package raisinchat.command;

import raisinchat.Storage;
import raisinchat.ui.Ui;
import raisinchat.task.TaskList;

public class ByeCommand extends Command {

    public ByeCommand(userCommand command) {
        super(command);
    }

    public boolean isExit() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye :( Please come back again soon :\")");
    }
}
