package raisinchat.command;

import raisinchat.exceptions.RaisinChatException;
import raisinchat.Storage;
import raisinchat.ui.Ui;
import raisinchat.task.TaskList;

public abstract class Command {
    public enum userCommand {
        HELP,
        LIST,
        EXIT,
        BYE,
        TODO,
        EVENT,
        DEADLINE,
        DELETE,
        MARK,
        UNMARK
    }

    private userCommand inputCommand;

    public Command(userCommand command) {
        this.inputCommand = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException;
    public abstract boolean isExit();
}
