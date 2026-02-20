package raisinchat.command;

import java.time.LocalDateTime;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Event;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;
import raisinchat.util.DateTimeParser;

/**
 * Abstraction of the Event command for the application, triggered by "event" command
 * Executing this command will create the Event task
 */
public class EventCommand extends Command {

    private static final String HOW_TO_COMMAND = "event <taskName> /from <yyyy-MM-dd hh:mm AM/PM> "
            + "/to <yyyy-MM-dd hh:mm AM/PM>";
    private final String extraArgs;

    /**
     * Creation of the Event command class object
     *
     * @param command   The actual enum command that was used by the user
     * @param extraArgs The additional arguments needed to create the actual Event task
     *                  which includes the task name, start datetime and end datetime
     */
    public EventCommand(UserCommand command, String extraArgs) {
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
     * @throws MissingArgException if command is not used as event [taskName] /from [yyyy-MM-dd hh:mm AM/PM]
     *                             /to [yyyy-MM-dd hh:mm AM/PM]
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgException {
        String[] splitArgs = this.extraArgs.split("/from", 2);
        // We split using /from first to get task name
        if (splitArgs.length < 2) {
            throw new MissingArgException(HOW_TO_COMMAND);
        }
        String nameTask = splitArgs[0].trim().replace("|", "-");
        String timingString = splitArgs[1].trim();
        // We split again using /to to get the actual start and end times
        String[] timingParts = timingString.split("/to", 2);
        if (timingParts.length < 2) {
            throw new MissingArgException(HOW_TO_COMMAND);
        }
        String startTime = timingParts[0].trim();
        String endTime = timingParts[1].trim();
        if (nameTask.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new MissingArgException(HOW_TO_COMMAND);
        }
        try {
            LocalDateTime parsedStartTime = DateTimeParser.parse(startTime);
            LocalDateTime parsedEndTime = DateTimeParser.parse(endTime);
            if (!parsedEndTime.isAfter(parsedStartTime)) {
                System.out.println("End time must be after start time.");
                throw new MissingArgException(HOW_TO_COMMAND);
            }
            Task eventTask = new Event(nameTask, false, parsedStartTime, parsedEndTime);
            tasks.addTask(eventTask);
            String res = String.format("Got it! I have added this task\n"
                            + "\t%s\n"
                            + "Now you have %d tasks!",
                    eventTask,
                    tasks.size());
            return res;
        } catch (RaisinChatException e) {
            return e.getMessage();
        }
    }
}
