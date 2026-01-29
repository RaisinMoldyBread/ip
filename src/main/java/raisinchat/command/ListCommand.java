package raisinchat.command;

import raisinchat.Storage;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(userCommand command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.toString());
    }
}
