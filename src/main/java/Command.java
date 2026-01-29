public abstract class Command {
    protected enum userCommand {
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
