package raisinchat;

import raisinchat.command.ByeCommand;
import raisinchat.command.Command;
import raisinchat.command.DeadlineCommand;
import raisinchat.command.DeleteCommand;
import raisinchat.command.EventCommand;
import raisinchat.command.HelpCommand;
import raisinchat.command.ListCommand;
import raisinchat.command.MarkCommand;
import raisinchat.command.TodoCommand;
import raisinchat.command.UnmarkCommand;
import raisinchat.exceptions.RaisinChatException;
import raisinchat.exceptions.UnkownCommandException;

public class Parser {

    public static Command parse(String command) throws RaisinChatException {
        String input = command.trim();
        if (input.isEmpty()) {
            throw new RaisinChatException("Empty command received! Type 'help' to see more commands!");
        }

        String[] commandInput = input.split("\\s+", 2);
        String commandAction = commandInput[0].toLowerCase();
        String args = (commandInput.length == 2) ? commandInput[1] : "";

        switch (commandAction) {
        case "help":
            return new HelpCommand(Command.userCommand.HELP);

        case "list":
            return new ListCommand(Command.userCommand.LIST);

        case "todo":
            return new TodoCommand(Command.userCommand.TODO, args);

        case "event":
            return new EventCommand(Command.userCommand.EVENT, args);

        case "deadline":
            return new DeadlineCommand(Command.userCommand.DEADLINE, args);

        case "delete":
            return new DeleteCommand(Command.userCommand.DELETE, args);

        case "mark":
            return new MarkCommand(Command.userCommand.MARK, args);

        case "unmark":
            return new UnmarkCommand(Command.userCommand.UNMARK, args);

        case "bye":
            return new ByeCommand(Command.userCommand.BYE);

        case "exit":
            return new ByeCommand(Command.userCommand.EXIT);

        default:
            throw new UnkownCommandException(commandAction);
        }
    }
}
