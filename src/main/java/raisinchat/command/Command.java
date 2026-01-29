package raisinchat.command;

import raisinchat.Storage;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

public abstract class Command {
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
        UNMARK
    }

    private UserCommand inputCommand;

    public Command(UserCommand command) {
        this.inputCommand = command;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException;

    public abstract boolean isExit();
}
