package raisinchat.command;

import raisinchat.Storage;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Command class that contains the abstract methods ALL command classes should have
 * Enums of possible commands users can use
 */
public abstract class Command {

    private UserCommand inputCommand;

    public Command(UserCommand command) {
        this.inputCommand = command;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException;

    /**
     * Returns if the program should exit after given this command
     *
     * @return true if and only if command given is "bye" OR "exit"
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Enum of commands that are available in this application
     */
    public enum UserCommand {
        HELP,
        LIST,
        EXIT,
        BYE,
        TODO,
        EVENT,
        DEADLINE,
        DELETE,
        MARK,
        UNMARK,
        FIND
    }
}
