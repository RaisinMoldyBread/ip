public class MissingArgException extends RaisinChatException {

    public MissingArgException(String usage) {
        super(String.format("Hmm, you are doing it wrong! Use command like this: %s", usage));
    }
}
