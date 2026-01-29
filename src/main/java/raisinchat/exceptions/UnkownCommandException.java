package raisinchat.exceptions;

/**
 * Exception that is triggered when the application detects that such given command does not exist in the program's
 * enum as depicted in Command.java file
 */
public class UnkownCommandException extends RaisinChatException {

    public UnkownCommandException(String command) {
        super(String.format("Hmmm, we do not have such command %s", command));
    }
}
