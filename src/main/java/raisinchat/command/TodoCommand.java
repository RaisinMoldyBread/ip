package raisinchat.command;

import raisinchat.Storage;
import raisinchat.exceptions.MissingArgException;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;
import raisinchat.ui.Ui;

public class TodoCommand extends Command {

    private String extraArgs;

    public TodoCommand(userCommand command, String extraArgs) {
        super(command);
        this.extraArgs = extraArgs;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MissingArgException {
        if (this.extraArgs.isEmpty()) {
            throw new MissingArgException("todo <taskName>");
        }

        Task newTodo = new Todo(this.extraArgs, false);
        tasks.addTask(newTodo);
        String res = String.format("Got it! I have added this task\n"
                        + "\t%s\n"
                        + "Now you have %d tasks!",
                newTodo.toString(),
                tasks.size());
        ui.showMessage(res);
    }
}
