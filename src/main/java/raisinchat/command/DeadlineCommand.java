package raisinchat.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.task.Deadline;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public class DeadlineCommand extends Command {

    private static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a",
            Locale.ENGLISH);
    private String extraArgs;

    public DeadlineCommand(UserCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgException {
        String[] getDeadline = this.extraArgs.split("/by", 2);
        // We split using /by so that we can extract deadline time
        if (getDeadline.length < 2) {
            throw new MissingArgException("deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>");
        }
        String nameTask = getDeadline[0].trim();
        String by = getDeadline[1].trim();
        if (nameTask.isEmpty() || by.isEmpty()) {
            throw new MissingArgException("deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>");
        }
        try {
            LocalDateTime parsedDeadline = LocalDateTime.parse(by, DATEFORMATTER);
            Task deadlineTask = new Deadline(nameTask, false, parsedDeadline);
            tasks.addTask(deadlineTask);
            String res = String.format("Got it! I have added this task\n"
                            + "\t%s\n"
                            + "Now you have %d tasks!",
                    deadlineTask.toString(),
                    tasks.size());
            ui.showMessage(res);

        } catch (DateTimeParseException e) {
            throw new MissingArgException("deadline <taskName> /by <yyyy-MM-dd hh:mm AM/PM>");
        }
    }
}
