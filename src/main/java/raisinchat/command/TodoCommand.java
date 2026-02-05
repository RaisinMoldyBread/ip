package raisinchat.command;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;
import raisinchat.ui.Ui;

/**
 * Abstraction of the Todo command for the application, triggered by "todo" command
 * Executing this command will create the Event task
 */
public class TodoCommand extends Command {

    private String extraArgs;

    /**
     * Creation of the Todo command class object
     *
     * @param command   The actual enum command that was used by the user
     * @param extraArgs The additional arguments needed to create the actual Todo task
     *                  which includes the task name
     */
    public TodoCommand(UserCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    /**
     * Method that triggers the execution of the command inputted for event
     * Checks for relevant arguments
     *
     * @param tasks   Actual task list to process on
     * @param ui      Ui class to execute user interaction methods
     * @param storage Storage class object to work on
     * @throws MissingArgException if command is not used as todo [taskName]
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgException {
        if (this.extraArgs.isEmpty()) {
            throw new MissingArgException("todo <taskName>");
        }

        Task newTodo = new Todo(this.extraArgs, false);
        tasks.addTask(newTodo);
        return String.format("Got it! I have added this task\n"
                        + "\t%s\n"
                        + "Now you have %d tasks!",
                newTodo.toString(),
                tasks.size());
    }
}
