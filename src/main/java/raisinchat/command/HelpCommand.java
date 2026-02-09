package raisinchat.command;

import raisinchat.Storage;
import raisinchat.task.TaskList;
import raisinchat.ui.Ui;

/**
 * Abstraction of the Help command for the application, triggered by "help" command
 * Executing this command will print the help string
 */
public class HelpCommand extends Command {

    private static final String HELP_STRING = """
            This is our command list:
            list
                - List all available tasks

            help
                - List all commands available to chatbot

            todo [name of task]
                - Creates a todo task

            deadline [name of task] /by [yyyy-MM-dd hh:mm AM/PM]
                - Creates task with deadline specified

            event [name of task] /from [yyyy-MM-dd hh:mm AM/PM] /to [yyyy-MM-dd hh:mm AM/PM]
                - Creates event task with start and end time

            delete [task index]
                - Deletes a task in the list

            find [keyword]
                - Finds tasks that contains provided keyword

            mark [task index]
                - Marks task at index specified as done

            unmark [task index]
                - Marks task as not completed

            bye / exit - Exit Chatbot :(
            """;

    public HelpCommand(UserCommand command) {
        super(command);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return HELP_STRING;
    }
}
