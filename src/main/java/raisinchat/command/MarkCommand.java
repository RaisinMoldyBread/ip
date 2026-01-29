package raisinchat.command;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Abstraction of the Mark command for the application, triggered by "mark" command
 * Executing this command will mark a given task as done based on index given in extraArgs
 */
public class MarkCommand extends Command {

    private String extraArgs;

    /**
     * Creation of the Mark command class object
     *
     * @param command The actual enum command that was used by the user
     * @param extraArgs The additional arguments needed to mark the task based on index number of the current list as
     *                  done
     */
    public MarkCommand(UserCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Marks task as done as specified by index given in the user input
     *
     * @param tasks   Actual task list to process on
     * @param ui      raisinchat.ui.Ui class to execute user interaction methods
     * @param storage raisinchat.Storage class object to work on
     * @throws MissingArgException if command is not used as delete [indexOfTask]
     * @throws RaisinChatException if index of task does NOT exist or index is NOT a number
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException {
        if (this.extraArgs.isBlank()) { // Checks if input contains an index
            throw new MissingArgException("mark <indexOfTask>");
        }

        int index = -1;
        try { // If second part of input is not number, throw error message to user
            index = Integer.parseInt(this.extraArgs);

        } catch (NumberFormatException e) {
            throw new RaisinChatException("Task index must be a number!");
        }

        // If index supplied is not valid, throw error to user
        if (index <= 0 || index > tasks.size()) {
            throw new RaisinChatException("Such task index does not exist! Please check the list again!");
        } else {
            // Index is valid, proceed to mark task
            Task task = tasks.getTasks(index - 1);
            ui.showMessage(task.markDone());
        }
    }
}
