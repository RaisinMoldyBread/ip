package raisinchat.exceptions;

/**
 * Exception that is triggered when the application detects that there are missing arguments for certain
 * given commands
 */
public class MissingArgException extends RaisinChatException {

    public MissingArgException(String usage) {
        super(String.format("Hmm, you are doing it wrong! Use command like this: %s", usage));
    }
}
