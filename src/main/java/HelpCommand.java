public class HelpCommand extends Command {

    private static final String HELPSTRING = """
            list - List all available tasks
            help - List all commands available to chatbot
            todo [name of task] - Creates a todo task
            deadline [name of task] /by [yyyy-MM-dd hh:mm AM/PM] - Creates task with deadline specified
            event [name of task] /from [yyyy-MM-dd hh:mm AM/PM] /to [yyyy-MM-dd hh:mm AM/PM] - Creates event task with start time and end time
            delete [Task index] - Deletes a task in the list
            mark [Task index] - Marks task at index specified to be done
            unmark [Task index] - Marks a task as not completed
            bye/exit - Exit Chatbot :(""";

    public HelpCommand(userCommand command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(HELPSTRING);
    }
}
