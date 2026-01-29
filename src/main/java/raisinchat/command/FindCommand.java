package raisinchat.command;

import java.util.ArrayList;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Abstraction of the Find command triggered by the "find" command
 * Executing it will find all tasks that matches the keyword given by the user
 */
public class FindCommand extends Command {
    private String extraArgs;

    /**
     * Creates the Find Command object
     *
     * @param command Actual enum of the command
     * @param extraArgs The supposed keyword to use for searching
     */
    public FindCommand(UserCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Finds tasks based on given keyword
     *
     * @param tasks   Actual task list to process search on
     * @param ui      Ui class to execute user interaction methods
     * @param storage Storage class object to work on
     * @throws MissingArgException if command is not used as find [keyword]
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException {
        if (this.extraArgs.isBlank()) { // Checks if input contains an index
            throw new MissingArgException("find <keyword>");
        }

        ArrayList<Task> displayList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task getTask = tasks.getTasks(i);
            if (getTask.toString().contains(this.extraArgs.trim())) {
                displayList.add(getTask);
            }
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list right now:\n");
        for (int i = 0; i < displayList.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(displayList.get(i).toString())
                    .append("\n");
        }
        ui.showMessage(sb.toString());
    }
}
