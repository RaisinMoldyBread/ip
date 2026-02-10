package raisinchat;

import raisinchat.command.ByeCommand;
import raisinchat.command.Command;
import raisinchat.command.DeadlineCommand;
import raisinchat.command.DeleteCommand;
import raisinchat.command.EventCommand;
import raisinchat.command.FindCommand;
import raisinchat.command.HelpCommand;
import raisinchat.command.ListCommand;
import raisinchat.command.MarkCommand;
import raisinchat.command.TodoCommand;
import raisinchat.command.UnmarkCommand;
import raisinchat.command.UpdateCommand;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.exceptions.UnkownCommandException;

/**
 * Abstraction of parsing inputs given by user
 */
public class Parser {

    /**
     * Parses the full string command from the Ui class that reads the user input
     *
     * @param command The actual command given by the user to parse
     * @return Command object that correctly abstracts the command given, allowing for further finer execution
     * @throws RaisinChatException if given command is empty
     */
    public static Command parse(String command) throws RaisinChatException {
        assert command != null : "Command to parse is null, please check logic at RaisinChat.java";
        String input = command.trim();
        if (input.isEmpty()) {
            throw new RaisinChatException("Empty command received! Type 'help' to see more commands!");
        }

        String[] commandInput = input.split("\\s+", 2);
        String commandAction = commandInput[0].toLowerCase();
        String args = (commandInput.length == 2) ? commandInput[1] : "";

        switch (commandAction) {
        case "help":
            return new HelpCommand(Command.UserCommand.HELP);

        case "list":
            return new ListCommand(Command.UserCommand.LIST);

        case "todo":
            return new TodoCommand(Command.UserCommand.TODO, args);

        case "event":
            return new EventCommand(Command.UserCommand.EVENT, args);

        case "deadline":
            return new DeadlineCommand(Command.UserCommand.DEADLINE, args);

        case "update":
            return new UpdateCommand(Command.UserCommand.UPDATE, args);

        case "delete":
            return new DeleteCommand(Command.UserCommand.DELETE, args);

        case "mark":
            return new MarkCommand(Command.UserCommand.MARK, args);

        case "unmark":
            return new UnmarkCommand(Command.UserCommand.UNMARK, args);

        case "find":
            return new FindCommand(Command.UserCommand.FIND, args);

        case "bye":
            return new ByeCommand(Command.UserCommand.BYE);

        case "exit":
            return new ByeCommand(Command.UserCommand.EXIT);

        default:
            throw new UnkownCommandException(commandAction);
        }
    }
}
