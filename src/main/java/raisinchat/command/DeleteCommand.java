package raisinchat.command;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Abstraction of the delete command for the application, triggered by "delete" command
 * Executing this command will delete said task based on input index
 */
public class DeleteCommand extends Command {

    private final String extraArgs;

    /**
     * Creation of the Delete command class object
     *
     * @param command   The actual enum command that was used by the user
     * @param extraArgs The additional arguments needed to delete the task from the list
     *                  in a form of an Integer
     */
    public DeleteCommand(UserCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    /**
     * Deletes specified task based on index given
     *
     * @param tasks   Actual task list to process delete on
     * @param ui      raisinchat.ui.Ui class to execute user interaction methods
     * @param storage raisinchat.Storage class object to work on
     * @return String result of executing command
     * @throws MissingArgException if command is not used as delete [indexOfTask]
     * @throws RaisinChatException if index of task does NOT exist or index is NOT a number
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException {
        if (this.extraArgs.isBlank()) { // Checks if input contains an index
            throw new MissingArgException("delete <indexOfTask>");
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
            // Index is valid, proceed to delete task
            Task task = tasks.getTask(index - 1);
            tasks.deleteTask(task);
            String res = String.format("Noted. I have removed this task:\n"
                            + "\t%s\n"
                            + "You now have %d tasks in the list!",
                    task.toString(),
                    tasks.size());
            return res;
        }
    }
}
