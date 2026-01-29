package raisinchat.exceptions;

/**
 * Exception that is triggered when the application detects that there are errors that are not normally caught
 * by current runtime exceptions or standard exceptions
 */
public class RaisinChatException extends Exception {

    public RaisinChatException(String message) {
        super(message);
    }


}
