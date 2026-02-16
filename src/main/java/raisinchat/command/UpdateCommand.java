package raisinchat.command;

import java.time.LocalDateTime;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Deadline;
import raisinchat.task.Event;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;
import raisinchat.util.DateTimeParser;

/**
 * Abstraction of the Update command for the application, triggered by the "update" command
 */
public class UpdateCommand extends Command {
    private final int taskIndex;
    private final String newBy;
    private final String newFrom;
    private final String newTo;

    /**
     * Creates the Update Command object
     *
     * @param command   Actual enum of the command
     * @param extraArgs The supposed keyword to use for searching
     */
    public UpdateCommand(UserCommand command, String extraArgs) throws RaisinChatException {
        super(command);
        assert extraArgs != null;

        if (extraArgs.isBlank()) {
            throw new MissingArgException("update <index> [options]. Look at 'help' for more information!");
        }

        String[] parts = extraArgs.trim().split("\\s+", 2);

        try {
            this.taskIndex = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new RaisinChatException("First argument must be a task index");
        }

        String options = parts.length > 1 ? parts[1] : "";

        this.newBy = extractOption(options, "/by");
        this.newFrom = extractOption(options, "/from");
        this.newTo = extractOption(options, "/to");
    }

    /**
     * Extracts options of the command based on preset options in Deadline and Events
     *
     * @param input of the command
     * @param flag  options that were preset
     * @return String to store in the tasks
     */
    private String extractOption(String input, String flag) {
        int start = input.indexOf(flag);
        if (start == -1) {
            return null;
        }
        return input.substring(start + flag.length()).trim();
    }

    /**
     * Update tasks based on given index and datetime values
     *
     * @param tasks   Actual task list to process search on
     * @param ui      Ui class to execute user interaction methods
     * @param storage Storage class object to work on
     * @throws RaisinChatException if command is not used as update [keyword]
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException {
        assert tasks != null && ui != null;

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new RaisinChatException("Invalid task index");
        }

        Task task = tasks.getTask(taskIndex);

        if (task instanceof Deadline deadline) {
            if (newBy == null) {
                throw new MissingArgException("update <index> /by <yyyy-MM-dd hh:mm AM/PM>");
            }

            LocalDateTime by = DateTimeParser.parse(newBy);
            deadline.setBy(by);

            return "Updated deadline:\n  " + task;

        } else if (task instanceof Event event) {
            if (newFrom == null && newTo == null) {
                throw new MissingArgException("update <index> /from <yyyy-MM-dd hh:mm AM/PM> AND/OR "
                        + "/to <yyyy-MM-dd hh:mm AM/PM>");
            }

            LocalDateTime updatedFrom = event.getFrom();
            LocalDateTime updatedTo = event.getTo();

            if (newFrom != null) {
                updatedFrom = DateTimeParser.parse(newFrom);
            }

            if (newTo != null) {
                updatedTo = DateTimeParser.parse(newTo);
            }

            if (updatedFrom.isAfter(updatedTo)) {
                throw new RaisinChatException("Event start time cannot be after end time");
            }

            event.setFrom(updatedFrom);
            event.setTo(updatedTo);

            return "Updated event:\n  " + event;

        } else {
            throw new RaisinChatException("Todo tasks cannot be updated");
        }
    }

}
