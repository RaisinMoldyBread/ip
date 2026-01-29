public class ListCommand extends Command {

    public ListCommand(userCommand command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(tasks.toString());
    }
}
