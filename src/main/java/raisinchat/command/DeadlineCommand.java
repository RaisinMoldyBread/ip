package raisinchat.command;

import java.time.LocalDateTime;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Deadline;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;
import raisinchat.util.DateTimeParser;

/**
 * Abstraction of the Deadline command for the application, triggered by "deadline" command
 */
public class DeadlineCommand extends Command {

    private static final String HOW_TO_COMMAND = "deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>";
    private final String extraArgs;

    /**
     * Creation of the Deadline command class object
     *
     * @param command   The actual enum command that was used by the user
     * @param extraArgs The additional arguments needed to construct the actual Deadline task
     *                  which includes the task name and the deadline
     */
    public DeadlineCommand(UserCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    /**
     * Method that triggers the execution of the command inputted for deadline
     * Checks for relevant arguments
     *
     * @param tasks   Actual task list to process on
     * @param ui      Ui class to execute user interaction methods
     * @param storage Storage class object to work on
     * @throws MissingArgException if command is not used as deadline [taskName] /by [yyyy-MM-dd hh:mm AM/PM]
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgException {
        String[] getDeadline = this.extraArgs.split("/by", 2);
        // We split using /by so that we can extract deadline time
        if (getDeadline.length < 2) {
            throw new MissingArgException(HOW_TO_COMMAND);
        }
        String nameTask = getDeadline[0].trim();
        String by = getDeadline[1].trim();
        if (nameTask.isEmpty() || by.isEmpty()) {
            throw new MissingArgException(HOW_TO_COMMAND);
        }
        try {
            LocalDateTime parsedDeadline = DateTimeParser.parse(by);
            Task deadlineTask = new Deadline(nameTask, false, parsedDeadline);
            tasks.addTask(deadlineTask);
            return String.format("Got it! I have added this task\n"
                            + "\t%s\n"
                            + "Now you have %d tasks!",
                    deadlineTask,
                    tasks.size());

        } catch (RaisinChatException e) {
            return e.getMessage();
        }
    }
}
