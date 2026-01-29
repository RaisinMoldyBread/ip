public class MarkCommand extends Command {

    private String extraArgs;

    public MarkCommand(userCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Marks task as done as specified by index given in the user input
     *
     * @param tasks Actual task list to process on
     * @param ui Ui class to execute user interaction methods
     * @param storage Storage class object to work on
     * @throws MissingArgException if command is not used as delete <indexOfTask>
     * @throws RaisinChatException if index of task does NOT exist or index is NOT a number
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RaisinChatException {
        if (this.extraArgs.isBlank()) { // Checks if input contains an index
            throw new MissingArgException("mark <indexOfTask>");
        }

        int index = -1;
        try { // If second part of input is not number, throw error message to user
            index = Integer.parseInt(this.extraArgs);

        } catch (NumberFormatException e) {
            throw new RaisinChatException("Task index must be a number!");
        }

        // If index supplied is not valid, throw error to user
        if (index <= 0 || index > tasks.size()) {
            throw new RaisinChatException("Such task index does not exist! Please check the list again!");
        } else {
            // Index is valid, proceed to mark task
            Task task = tasks.getTasks(index - 1);
            ui.showMessage(task.markDone());
        }
    }
}
