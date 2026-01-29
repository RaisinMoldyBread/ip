package raisinchat.exceptions;

public class UnkownCommandException extends RaisinChatException{

    public UnkownCommandException(String command) {
        super(String.format("Hmmm, we do not have such command %s", command));
    }
}
