package raisinchat.command;

import raisinchat.Storage;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Abstraction of the list command for the application, triggered by "list" command
 * Executing this command will print the current list of tasks
 */
public class ListCommand extends Command {

    public ListCommand(UserCommand command) {
        super(command);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }
}
