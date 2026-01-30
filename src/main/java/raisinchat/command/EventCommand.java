package raisinchat.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.task.Event;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Abstraction of the Event command for the application, triggered by "event" command
 * Executing this command will create the Event task
 */
public class EventCommand extends Command {

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a",
            Locale.ENGLISH);
    private String extraArgs;

    /**
     * Creation of the Event command class object
     *
     * @param command The actual enum command that was used by the user
     * @param extraArgs The additional arguments needed to create the actual Event task
     *                  which includes the task name, start datetime and end datetime
     */
    public EventCommand(UserCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Method that triggers the execution of the command inputted for event
     * Checks for relevant arguments
     *
     * @param tasks   Actual task list to process on
     * @param ui      Ui class to execute user interaction methods
     * @param storage Storage class object to work on
     * @throws MissingArgException if command is not used as event [taskName] /from [yyyy-MM-dd hh:mm AM/PM]
     *                              /to [yyyy-MM-dd hh:mm AM/PM]
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgException {
        String[] splitArgs = this.extraArgs.split("/from", 2);
        // We split using /from first to get task name
        if (splitArgs.length < 2) {
            throw new MissingArgException("event <taskName> /from <yyyy-MM-dd hh:mm AM/PM> "
                                            + "/to <yyyy-MM-dd hh:mm AM/PM>");
        }
        String nameTask = splitArgs[0].trim();
        String getFullTiming = splitArgs[1].trim();
        // We split again using /to to get the actual start and end times
        String[] getActualTiming = getFullTiming.split("/to", 2);
        if (getActualTiming.length < 2) {
            throw new MissingArgException("event <taskName> /from <yyyy-MM-dd hh:mm AM/PM> "
                                            + "/to <yyyy-mm-dd hh:mm AM/PM>");
        }
        String startTime = getActualTiming[0].trim();
        String endTime = getActualTiming[1].trim();
        if (nameTask.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            throw new MissingArgException("event <taskName> /from <yyyy-MM-dd hh:mm AM/PM> "
                                            + "/to <yyyy-MM-dd hh:mm AM/PM>");
        }

        try {
            LocalDateTime parsedStartTime = LocalDateTime.parse(startTime, DATEFORMATTER);
            LocalDateTime parsedEndTime = LocalDateTime.parse(endTime, DATEFORMATTER);
            if (!parsedEndTime.isAfter(parsedStartTime)) {
                System.out.println("End time must be after start time.");
                throw new MissingArgException("event <taskName> /from <yyyy-MM-dd hh:mm AM/PM> "
                                                + "/to <yyyy-MM-dd hh:mm AM/PM>");
            }
            Task eventTask = new Event(nameTask, false, parsedStartTime, parsedEndTime);
            tasks.addTask(eventTask);
            String res = String.format("Got it! I have added this task\n"
                                        + "\t%s\n"
                                        + "Now you have %d tasks!",
                    eventTask.toString(),
                    tasks.size());
            ui.showMessage(res);

        } catch (DateTimeParseException e) {
            throw new MissingArgException("event <taskName> /from <yyyy-MM-dd hh:mm AM/PM> "
                                            + "/to <yyyy-MM-dd hh:mm AM/PM>");
        }
    }
}
